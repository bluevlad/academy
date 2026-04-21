package com.academy.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;

@Component
public class ApiLoggingInterceptor implements HandlerInterceptor {

    @Value("${log.path}")
    private String logPath;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull Object handler)
            throws Exception {

        // OPTIONS 요청은 로깅 제외
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        String logContent = buildLogContent(request);
        writeLog(logContent);

        return true;
    }

    private String buildLogContent(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        String timestamp = LocalDateTime.now().format(DATETIME_FORMATTER);

        sb.append("================================================================================\n");
        sb.append("[").append(timestamp).append("]\n");
        sb.append("--------------------------------------------------------------------------------\n");

        // Request Mapping (API 주소)
        sb.append("RequestMapping : ").append(request.getMethod()).append(" ").append(request.getRequestURI());
        if (request.getQueryString() != null) {
            sb.append("?").append(request.getQueryString());
        }
        sb.append("\n");

        // 요청자 IP
        String clientIp = getClientIpAddress(request);
        sb.append("요청 Address : ").append(clientIp).append("\n");

        // 요청 정보 (파라미터)
        sb.append("요청 정보 :\n");

        // Request Parameters
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap != null && !parameterMap.isEmpty()) {
            sb.append("  [Parameters]\n");
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                sb.append("    ").append(entry.getKey()).append(" = ");
                String[] values = entry.getValue();
                if (values.length == 1) {
                    sb.append(maskSensitiveData(entry.getKey(), values[0]));
                } else {
                    sb.append("[");
                    for (int i = 0; i < values.length; i++) {
                        if (i > 0)
                            sb.append(", ");
                        sb.append(maskSensitiveData(entry.getKey(), values[i]));
                    }
                    sb.append("]");
                }
                sb.append("\n");
            }
        } else {
            sb.append("  [Parameters] (없음)\n");
        }

        // Request Headers (주요 헤더만)
        sb.append("  [Headers]\n");
        sb.append("    Content-Type : ").append(request.getContentType()).append("\n");
        sb.append("    User-Agent : ").append(request.getHeader("User-Agent")).append("\n");
        sb.append("    Referer : ").append(request.getHeader("Referer")).append("\n");

        sb.append("================================================================================\n\n");

        return sb.toString();
    }

    private String getClientIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        // 여러 IP가 있는 경우 첫 번째 IP 반환
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }

        return ip;
    }

    private String maskSensitiveData(String key, String value) {
        // 비밀번호 등 민감한 정보 마스킹
        String lowerKey = key.toLowerCase();
        if (lowerKey.contains("password") || lowerKey.contains("pwd") ||
                lowerKey.contains("secret") || lowerKey.contains("token")) {
            return "********";
        }
        return value;
    }

    private synchronized void writeLog(String content) {
        String fileName = "api_log_" + LocalDateTime.now().format(DATE_FORMATTER) + ".log";
        File logDir = new File(logPath);

        // 로그 디렉토리 생성
        if (!logDir.exists()) {
            logDir.mkdirs();
        }

        File logFile = new File(logDir, fileName);

        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(logFile, true)))) {
            writer.print(content);
        } catch (IOException e) {
            System.err.println("API 로그 파일 작성 실패: " + e.getMessage());
            e.printStackTrace();
        }
    }
}