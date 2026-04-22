package com.academy.user.enrollment;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EnrollmentMapper {
    int upsertActive(@Param("enrollmentId") String enrollmentId,
                     @Param("userId") String userId,
                     @Param("mstCode") String mstCode,
                     @Param("orderId") String orderId,
                     @Param("periodStart") LocalDate periodStart,
                     @Param("periodEnd") LocalDate periodEnd);

    List<Enrollment> findActiveByUserId(@Param("userId") String userId);

    int updateProgress(@Param("enrollmentId") String enrollmentId,
                       @Param("manualProgress") int manualProgress);
}
