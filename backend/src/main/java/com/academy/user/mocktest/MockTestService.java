package com.academy.user.mocktest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class MockTestService {

    private final MockTestMapper mapper;

    public MockTestService(MockTestMapper mapper) {
        this.mapper = mapper;
    }

    public List<MockExam> openExams() { return mapper.findOpenExams(); }

    public List<MockAttempt> myAttempts(String userId) { return mapper.findAttemptsByUser(userId); }

    @Transactional
    public MockAttempt register(String userId, String examId) {
        mapper.findExamById(examId)
            .orElseThrow(() -> new ExamNotFoundException("모의고사를 찾을 수 없습니다: " + examId));
        return mapper.findAttempt(userId, examId).orElseGet(() -> {
            String attemptId = UUID.randomUUID().toString();
            mapper.register(attemptId, userId, examId);
            return mapper.findAttempt(userId, examId).orElseThrow();
        });
    }

    @Transactional
    public MockAttempt submit(String userId, String examId, String answerSheet, Integer autoScore) {
        MockAttempt attempt = mapper.findAttempt(userId, examId)
            .orElseThrow(() -> new ExamNotFoundException("신청 내역이 없습니다."));
        mapper.submit(attempt.attemptId(), answerSheet, autoScore, LocalDateTime.now());
        return mapper.findAttempt(userId, examId).orElseThrow();
    }

    public static class ExamNotFoundException extends RuntimeException {
        public ExamNotFoundException(String m) { super(m); }
    }
}
