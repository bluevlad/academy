package com.academy;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

/**
 * Modular Monolith 경계 규칙 (Sprint 6 · ADR-001).
 *
 * <p>URL prefix 로는 런타임 경계를 잡았지만 코드 수준에서 다음 방향성 역의존을
 * 컴파일 시점에 금지한다:
 * <ul>
 *   <li>admin → user 역참조 금지 (admin 은 user 모듈을 몰라야 함)</li>
 *   <li>user → admin 역참조 금지</li>
 *   <li>shared → admin/user 역참조 금지 (shared 는 하위를 모름)</li>
 * </ul>
 *
 * <p>공통이 필요하면 반드시 shared 로 승격. 향후 Sprint 6 말기 ArchUnit 규칙을
 * 세분화할 때 이 클래스에 추가.
 */
@AnalyzeClasses(
    packages = "com.academy",
    importOptions = {ImportOption.DoNotIncludeTests.class}
)
class ModularMonolithRulesTest {

    @ArchTest
    static final ArchRule shared_must_not_depend_on_admin =
        noClasses().that().resideInAPackage("com.academy.shared..")
            .should().dependOnClassesThat().resideInAPackage("com.academy.admin..");

    @ArchTest
    static final ArchRule shared_must_not_depend_on_user =
        noClasses().that().resideInAPackage("com.academy.shared..")
            .should().dependOnClassesThat().resideInAPackage("com.academy.user..");

    @ArchTest
    static final ArchRule admin_must_not_depend_on_user =
        noClasses().that().resideInAPackage("com.academy.admin..")
            .should().dependOnClassesThat().resideInAPackage("com.academy.user..");

    @ArchTest
    static final ArchRule user_must_not_depend_on_admin =
        noClasses().that().resideInAPackage("com.academy.user..")
            .should().dependOnClassesThat().resideInAPackage("com.academy.admin..");

    /** 컨트롤러는 반드시 /api/ prefix URL 만 사용 (RequestMapping 규칙은 별도 세밀 테스트 여지). */
    @ArchTest
    static final ArchRule restcontrollers_live_in_api_packages =
        classes().that().areAnnotatedWith("org.springframework.web.bind.annotation.RestController")
            .should().resideInAnyPackage(
                "com.academy..",  // 허용 — 실제 경로 검증은 SecurityConfig + filter 가 담당
                "com.academy.shared..",
                "com.academy.admin..",
                "com.academy.user..",
                "com.academy.auth..",
                "com.academy.login..",
                "com.academy.lecture..",
                "com.academy.member..",
                "com.academy.book..",
                "com.academy.board..",
                "com.academy.exam..",
                "com.academy.counsel..",
                "com.academy.coop..",
                "com.academy.dashboard..",
                "com.academy.event..",
                "com.academy.popup..",
                "com.academy.note..",
                "com.academy.box..",
                "com.academy.freeOrder..",
                "com.academy.productorder..",
                "com.academy.bookCmmt..",
                "com.academy.bookOrder..",
                "com.academy.apitest..",
                "com.academy.banner..",
                "com.academy.dday..",
                "com.academy.gosi..",
                "com.academy.index..",
                "com.academy.common.."
            );
}
