/**
 * 수강생에게 노출되는 콘텐츠 — 강의·과목·교수진 (Sprint 2).
 *
 * <p>기존 admin 쪽 Oracle 레거시 쿼리 (ROWNUM·{@code (+)}·NVL·DECODE) 는 복잡하고
 * MariaDB 호환이 보장되지 않으므로, user 경로는 MariaDB 표준 문법의 경량 쿼리로 신규 작성.
 * P0 canonical "형법" (과목·강의 대표, variants 660) 를 Sprint 2 목표로 연결.
 *
 * <p>동영상 플레이어·진도율 등은 Plan B (MVP 이후) 로 유예. 이 패키지는 목록·상세·검색만 담당.
 */
package com.academy.user.content;
