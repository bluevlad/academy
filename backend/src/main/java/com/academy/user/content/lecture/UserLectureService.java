package com.academy.user.content.lecture;

import com.academy.shared.common.PagedResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLectureService {

    private final UserLectureMapper mapper;

    public UserLectureService(UserLectureMapper mapper) {
        this.mapper = mapper;
    }

    public PagedResponse<LectureSummary> list(String keyword, String subjectCd, String teacherId, int page, int size) {
        int normalizedPage = Math.max(1, page);
        int normalizedSize = size <= 0 || size > 100 ? 20 : size;
        int offset = (normalizedPage - 1) * normalizedSize;

        List<LectureSummary> items = mapper.findOpenList(keyword, subjectCd, teacherId, offset, normalizedSize);
        long total = mapper.countOpenList(keyword, subjectCd, teacherId);
        return PagedResponse.of(items, normalizedPage, normalizedSize, total);
    }

    public LectureDetail detail(String mstCode) {
        return mapper.findDetailByMstCode(mstCode)
            .orElseThrow(() -> new LectureNotFoundException("강의를 찾을 수 없습니다: " + mstCode));
    }

    public static class LectureNotFoundException extends RuntimeException {
        public LectureNotFoundException(String m) { super(m); }
    }
}
