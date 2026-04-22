package com.academy.user.content.lecture;

import com.academy.shared.common.PagedResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserLectureServiceTest {

    private UserLectureMapper mapper;
    private UserLectureService service;

    @BeforeEach
    void setUp() {
        mapper = mock(UserLectureMapper.class);
        service = new UserLectureService(mapper);
    }

    @Test
    void list_returns_paged_response_with_default_size_for_zero() {
        List<LectureSummary> rows = List.of(summary("L1"), summary("L2"));
        when(mapper.findOpenList(any(), any(), any(), anyInt(), anyInt())).thenReturn(rows);
        when(mapper.countOpenList(any(), any(), any())).thenReturn(42L);

        PagedResponse<LectureSummary> res = service.list(null, null, null, 0, 0);

        assertThat(res.items()).hasSize(2);
        assertThat(res.page()).isEqualTo(1);
        assertThat(res.size()).isEqualTo(20);
        assertThat(res.totalItems()).isEqualTo(42);
        assertThat(res.totalPages()).isEqualTo(3);
    }

    @Test
    void list_caps_size_at_100() {
        when(mapper.findOpenList(any(), any(), any(), anyInt(), anyInt())).thenReturn(List.of());
        when(mapper.countOpenList(any(), any(), any())).thenReturn(0L);

        PagedResponse<LectureSummary> res = service.list(null, null, null, 1, 999);

        assertThat(res.size()).isEqualTo(20);  // >100 → default 20
    }

    @Test
    void detail_returns_when_found() {
        LectureDetail fixture = new LectureDetail(
            "L1", "형법 기본", "CRIM", "형법", "teach-1", "김교수", "ON", "OPT",
            LocalDateTime.now(), List.of()
        );
        when(mapper.findDetailByMstCode("L1")).thenReturn(Optional.of(fixture));

        LectureDetail res = service.detail("L1");
        assertThat(res.mstCode()).isEqualTo("L1");
    }

    @Test
    void detail_throws_when_not_found() {
        when(mapper.findDetailByMstCode("GHOST")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.detail("GHOST"))
            .isInstanceOf(UserLectureService.LectureNotFoundException.class);
    }

    private LectureSummary summary(String id) {
        return new LectureSummary(id, "t-" + id, "CRIM", "형법", "tea", "김", "ON", LocalDateTime.now());
    }
}
