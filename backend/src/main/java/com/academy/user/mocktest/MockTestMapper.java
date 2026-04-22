package com.academy.user.mocktest;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Mapper
public interface MockTestMapper {
    List<MockExam> findOpenExams();

    Optional<MockExam> findExamById(@Param("examId") String examId);

    int register(@Param("attemptId") String attemptId,
                 @Param("userId") String userId,
                 @Param("examId") String examId);

    Optional<MockAttempt> findAttempt(@Param("userId") String userId,
                                      @Param("examId") String examId);

    List<MockAttempt> findAttemptsByUser(@Param("userId") String userId);

    int submit(@Param("attemptId") String attemptId,
               @Param("answerSheet") String answerSheet,
               @Param("score") Integer score,
               @Param("submittedAt") LocalDateTime submittedAt);
}
