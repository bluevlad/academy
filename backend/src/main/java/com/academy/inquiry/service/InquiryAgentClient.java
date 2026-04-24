package com.academy.inquiry.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.Map;

/**
 * academy-agent (:9011) HTTP 클라이언트.
 *
 * <p>docker-compose 네트워크에서 {@code http://academy-agent:9011} 로 접근.
 * 외부에선 {@code http://localhost:9011} (개발). 환경변수 {@code ACADEMY_AGENT_URL}
 * 로 오버라이드.
 *
 * <p>실패 시 예외 전파 — 호출부에서 fallback 결정.
 */
@Component
public class InquiryAgentClient {

    private static final Logger log = LoggerFactory.getLogger(InquiryAgentClient.class);

    /**
     * HTTP/1.1 명시 — FastAPI/uvicorn 은 HTTP/2 미지원. 기본값으로 두면
     * "Unsupported upgrade request" 로 agent 가 body 받지 못해 422 반환.
     */
    private final HttpClient http = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_1_1)
        .connectTimeout(Duration.ofSeconds(3))
        .build();
    private final ObjectMapper mapper = new ObjectMapper();

    private final String baseUrl;

    public InquiryAgentClient(@Value("${academy.agent.url:http://academy-agent:9011}") String baseUrl) {
        this.baseUrl = baseUrl.replaceAll("/+$", "");
    }

    public ClassifyResponse classify(String title, String body) throws AgentException {
        return post("/classify",
            Map.of("title", title == null ? "" : title,
                   "body",  body  == null ? "" : body),
            ClassifyResponse.class);
    }

    public SuggestResponse suggestRelated(String draftBody, Integer topK) throws AgentException {
        return post("/suggest-related",
            Map.of("draft_body", draftBody == null ? "" : draftBody,
                   "top_k", topK == null ? 3 : topK),
            SuggestResponse.class);
    }

    public void recordFeedback(long csSeq, String fromCategory, String toCategory,
                               String toUser, String changedBy, String reason, boolean aiError)
        throws AgentException {
        post("/route-feedback",
            Map.of("cs_seq", csSeq,
                   "from_category", fromCategory == null ? "" : fromCategory,
                   "to_category", toCategory,
                   "to_user", toUser,
                   "changed_by", changedBy,
                   "reason", reason == null ? "" : reason,
                   "is_ai_error", aiError),
            Map.class);
    }

    private <T> T post(String path, Object payload, Class<T> cls) throws AgentException {
        try {
            String json = mapper.writeValueAsString(payload);
            HttpRequest req = HttpRequest.newBuilder(URI.create(baseUrl + path))
                .timeout(Duration.ofSeconds(30))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
            HttpResponse<String> res = http.send(req, HttpResponse.BodyHandlers.ofString());
            if (res.statusCode() >= 400) {
                throw new AgentException("agent " + path + " http " + res.statusCode() + ": " + res.body());
            }
            return mapper.readValue(res.body(), cls);
        } catch (Exception e) {
            log.warn("agent {} 호출 실패: {}", path, e.getMessage());
            throw new AgentException("agent 호출 실패: " + e.getMessage(), e);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record ClassifyResponse(
        String category,
        BigDecimal confidence,
        String reasoning,
        String model,
        int latency_ms,
        boolean used_fallback
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record RelatedItem(
        long cs_seq,
        String title,
        String answer_excerpt,
        BigDecimal similarity,
        String category
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record SuggestResponse(List<RelatedItem> items, int query_embedding_dim, String model) {}

    public static class AgentException extends Exception {
        public AgentException(String message) { super(message); }
        public AgentException(String message, Throwable cause) { super(message, cause); }
    }
}
