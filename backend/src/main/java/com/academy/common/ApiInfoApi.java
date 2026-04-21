package com.academy.common;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "API Info", description = "API 정보 조회")
@RestController
@RequestMapping("/api/info")
public class ApiInfoApi extends CORSFilter {

    private final RequestMappingHandlerMapping requestMappingHandlerMapping;

    public ApiInfoApi(RequestMappingHandlerMapping requestMappingHandlerMapping) {
        this.requestMappingHandlerMapping = requestMappingHandlerMapping;
    }

    @Operation(summary = "전체 API 목록 조회", description = "서비스 중인 모든 API 목록을 조회합니다. module 파라미터로 특정 모듈만 필터링 가능합니다.")
    @GetMapping(value = "/getApiList")
    public JSONObject getApiList(@RequestParam(required = false) String module) {
        HashMap<String, Object> jsonObject = new HashMap<>();

        List<Map<String, Object>> apiList = new ArrayList<>();
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();

        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
            RequestMappingInfo mappingInfo = entry.getKey();
            HandlerMethod handlerMethod = entry.getValue();

            // com.academy 패키지의 API만 필터링
            String className = handlerMethod.getBeanType().getName();
            if (!className.startsWith("com.academy")) {
                continue;
            }

            Set<String> patterns = mappingInfo.getPatternValues();
            if (patterns.isEmpty()) {
                continue;
            }

            String url = patterns.iterator().next();

            // 모듈 필터링
            if (module != null && !module.isEmpty()) {
                if (!url.contains("/" + module + "/") && !url.startsWith("/api/" + module)) {
                    continue;
                }
            }

            // HTTP 메서드 추출
            Set<org.springframework.web.bind.annotation.RequestMethod> methods = mappingInfo.getMethodsCondition().getMethods();
            String httpMethod = methods.isEmpty() ? "ALL" : methods.stream()
                    .map(Enum::name)
                    .collect(Collectors.joining(", "));

            Map<String, Object> apiInfo = new HashMap<>();
            apiInfo.put("url", url);
            apiInfo.put("httpMethod", httpMethod);
            apiInfo.put("controller", handlerMethod.getBeanType().getSimpleName());
            apiInfo.put("method", handlerMethod.getMethod().getName());
            apiInfo.put("package", extractModule(className));

            apiList.add(apiInfo);
        }

        // URL 기준 정렬
        apiList.sort(Comparator.comparing(a -> (String) a.get("url")));

        jsonObject.put("apiList", apiList);
        jsonObject.put("totalCount", apiList.size());
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    @Operation(summary = "모듈별 API 통계 조회", description = "모듈별 API 개수와 상세 목록을 조회합니다.")
    @GetMapping(value = "/getApiSummary")
    public JSONObject getApiSummary() {
        HashMap<String, Object> jsonObject = new HashMap<>();

        Map<String, Integer> moduleCounts = new HashMap<>();
        Map<String, List<Map<String, Object>>> moduleApis = new HashMap<>();
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        int totalCount = 0;

        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
            RequestMappingInfo mappingInfo = entry.getKey();
            HandlerMethod handlerMethod = entry.getValue();

            String className = handlerMethod.getBeanType().getName();
            if (!className.startsWith("com.academy")) {
                continue;
            }

            Set<String> patterns = mappingInfo.getPatternValues();
            if (patterns.isEmpty()) {
                continue;
            }

            String moduleName = extractModule(className);
            moduleCounts.merge(moduleName, 1, (a, b) -> a + b);
            totalCount++;

            // 모듈별 API 목록 구성
            String url = patterns.iterator().next();
            Set<org.springframework.web.bind.annotation.RequestMethod> methods = mappingInfo.getMethodsCondition().getMethods();
            String httpMethod = methods.isEmpty() ? "ALL" : methods.stream()
                    .map(Enum::name)
                    .collect(Collectors.joining(", "));

            Map<String, Object> apiInfo = new HashMap<>();
            apiInfo.put("url", url);
            apiInfo.put("httpMethod", httpMethod);
            apiInfo.put("method", handlerMethod.getMethod().getName());

            moduleApis.computeIfAbsent(moduleName, k -> new ArrayList<>()).add(apiInfo);
        }

        // 모듈별 API 목록 정렬
        for (List<Map<String, Object>> apis : moduleApis.values()) {
            apis.sort(Comparator.comparing(a -> (String) a.get("url")));
        }

        List<Map<String, Object>> summary = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : moduleCounts.entrySet()) {
            Map<String, Object> moduleInfo = new HashMap<>();
            moduleInfo.put("module", entry.getKey());
            moduleInfo.put("count", entry.getValue());
            moduleInfo.put("apis", moduleApis.get(entry.getKey()));
            summary.add(moduleInfo);
        }

        // 모듈명 기준 정렬
        summary.sort(Comparator.comparing(a -> (String) a.get("module")));

        jsonObject.put("summary", summary);
        jsonObject.put("totalCount", totalCount);
        jsonObject.put("moduleCount", moduleCounts.size());
        jsonObject.put("retMsg", "OK");

        return new JSONObject(jsonObject);
    }

    /**
     * 클래스명에서 모듈명 추출
     */
    private String extractModule(String className) {
        // com.academy.board.BoardApi -> board
        // com.academy.lecture.service.LectureService -> lecture
        String[] parts = className.split("\\.");
        if (parts.length >= 3) {
            return parts[2]; // com.academy.<module>
        }
        return "unknown";
    }

}