package com.academy.user.content.teacher;

public record TeacherView(
    String teacherId,
    String teacherNm,
    String email,
    long lectureCount
) {}
