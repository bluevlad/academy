/**
 * Material Dashboard 2 React - Routes Configuration
 * Lazy loading 적용으로 초기 번들 크기 최소화
 */

import { lazy } from "react";

// @mui icons
import Icon from "@mui/material/Icon";

// Lazy-loaded layouts
const Dashboard = lazy(() => import("layouts/dashboard"));
const Tables = lazy(() => import("layouts/tables"));
const Billing = lazy(() => import("layouts/billing"));
const Member = lazy(() => import("layouts/member"));
const Exam = lazy(() => import("layouts/exam"));
const GosiList = lazy(() => import("layouts/exam/gosi"));
const GosiDetail = lazy(() => import("layouts/exam/gosi/detail"));
const MouiList = lazy(() => import("layouts/exam/moui"));
const MouiDetail = lazy(() => import("layouts/exam/moui/detail"));
const Menu = lazy(() => import("layouts/menu"));
const Profile = lazy(() => import("layouts/profile"));

// Board
const Board = lazy(() => import("layouts/board"));
const BoardAll = lazy(() => import("layouts/board/boardAll"));
const BoardManagement = lazy(() => import("layouts/board/boardManagement"));
const BoardViewManagement = lazy(() => import("layouts/board/boardViewManagement"));

// Admin
const AdminCode = lazy(() => import("layouts/admin/code"));
const AdminAuth = lazy(() => import("layouts/admin/auth"));
const AdminMenu = lazy(() => import("layouts/admin/menu"));
const AdminBanner = lazy(() => import("layouts/admin/banner"));

// Auth
const SignIn = lazy(() => import("layouts/authentication/sign-in"));
const SignUp = lazy(() => import("layouts/authentication/sign-up"));

// Book
const BookList = lazy(() => import("layouts/book"));
const BookWrite = lazy(() => import("layouts/book/write"));

// Coop
const CoopList = lazy(() => import("layouts/coop"));
const CoopBoardList = lazy(() => import("layouts/coop/board"));
const CoopBoardWrite = lazy(() => import("layouts/coop/board/write"));
const CoopOrderList = lazy(() => import("layouts/coop/order"));

// Counsel
const CounselList = lazy(() => import("layouts/counsel"));
const CounselWrite = lazy(() => import("layouts/counsel/write"));
const CounselDetail = lazy(() => import("layouts/counsel/detail"));

// D-Day
const DdayList = lazy(() => import("layouts/dday"));
const DdayDetail = lazy(() => import("layouts/dday/detail"));

// Service
const EventList = lazy(() => import("layouts/event"));
const EventDetail = lazy(() => import("layouts/event/detail"));
const NoteList = lazy(() => import("layouts/note"));
const NoteDetail = lazy(() => import("layouts/note/detail"));
const PopupList = lazy(() => import("layouts/popup"));
const PopupDetail = lazy(() => import("layouts/popup/detail"));
const StatList = lazy(() => import("layouts/stat"));
const SurveyList = lazy(() => import("layouts/survey"));

// Manage
const CategorySaleList = lazy(() => import("layouts/manage/categorySale"));
const LectureYearList = lazy(() => import("layouts/manage/lectureYear"));
const TeacherCalculateList = lazy(() => import("layouts/manage/teacherCalculate"));

// Order
const ProductOrderList = lazy(() => import("layouts/order/productOrder"));
const CouponList = lazy(() => import("layouts/order/coupon"));
const FreeOrderList = lazy(() => import("layouts/order/freeOrder"));

// Lecture
const OnlineLectureList = lazy(() => import("layouts/lecture/online"));
const OnlineLectureDetail = lazy(() => import("layouts/lecture/online/detail"));
const OfflineLectureList = lazy(() => import("layouts/lecture/offline"));
const OfflineLectureDetail = lazy(() => import("layouts/lecture/offline/detail"));
const LectureReplyList = lazy(() => import("layouts/lecture/reply"));
const LectureReplyDetail = lazy(() => import("layouts/lecture/reply/detail"));

const routes = [
  {
    type: "collapse",
    name: "Dashboard",
    key: "dashboard",
    icon: <Icon fontSize="small">dashboard</Icon>,
    route: "/dashboard",
    component: <Dashboard />,
  },
  {
    type: "collapse",
    name: "게시판 통합 관리",
    key: "board-integrated-management",
    icon: <Icon fontSize="small">dashboard</Icon>,
    collapse: [
      {
        name: "게시판",
        key: "board-list",
        route: "/board",
        component: <Board />,
      },
      {
        name: "전체 게시물 조회",
        key: "board-all",
        route: "/board/all",
        component: <BoardAll />,
      },
      {
        name: "게시판 관리",
        key: "board-management",
        route: "/board/management",
        component: <BoardManagement />,
      },
      {
        name: "게시판 뷰 관리",
        key: "board-view-management",
        route: "/board/view-management",
        component: <BoardViewManagement />,
      },
    ],
  },
  {
    type: "collapse",
    name: "서비스 관리",
    key: "service-management",
    icon: <Icon fontSize="small">settings_applications</Icon>,
    collapse: [
      {
        name: "이벤트 관리",
        key: "event-list",
        route: "/event",
        component: <EventList />,
      },
      {
        name: "쪽지 관리",
        key: "note-list",
        route: "/note",
        component: <NoteList />,
      },
      {
        name: "팝업 관리",
        key: "popup-list",
        route: "/popup",
        component: <PopupList />,
      },
      {
        name: "설문조사 관리",
        key: "survey-list",
        route: "/survey",
        component: <SurveyList />,
      },
      {
        name: "통계 관리",
        key: "stat-list",
        route: "/stat",
        component: <StatList />,
      },
    ],
  },
  {
    type: "collapse",
    name: "경영 관리",
    key: "manage",
    icon: <Icon fontSize="small">manage_accounts</Icon>,
    collapse: [
      {
        name: "카테고리별 매출",
        key: "category-sale",
        route: "/manage/category-sale",
        component: <CategorySaleList />,
      },
      {
        name: "연도별 강의",
        key: "lecture-year",
        route: "/manage/lecture-year",
        component: <LectureYearList />,
      },
      {
        name: "강사료 정산",
        key: "teacher-calculate",
        route: "/manage/teacher-calculate",
        component: <TeacherCalculateList />,
      },
    ],
  },
  {
    type: "collapse",
    name: "상품 주문 관리",
    key: "order",
    icon: <Icon fontSize="small">shopping_cart</Icon>,
    collapse: [
      {
        name: "주문 내역",
        key: "product-order",
        route: "/order/product-order",
        component: <ProductOrderList />,
      },
      {
        name: "쿠폰 관리",
        key: "coupon",
        route: "/order/coupon",
        component: <CouponList />,
      },
      {
        name: "무료 수강 신청",
        key: "free-order",
        route: "/order/free-order",
        component: <FreeOrderList />,
      },
    ],
  },
  {
    type: "collapse",
    name: "강의 관리",
    key: "lecture",
    icon: <Icon fontSize="small">school</Icon>,
    collapse: [
      {
        name: "단과 강의 관리",
        key: "online-lecture",
        route: "/lecture/online",
        component: <OnlineLectureList />,
      },
      {
        name: "학원 강의 관리",
        key: "offline-lecture",
        route: "/lecture/offline",
        component: <OfflineLectureList />,
      },
      {
        name: "수강 후기 관리",
        key: "lecture-reply",
        route: "/lecture/reply",
        component: <LectureReplyList />,
      },
    ],
  },
  // Hidden routes for details
  {
    key: "online-lecture-detail",
    route: "/lecture/online/detail",
    component: <OnlineLectureDetail />,
  },
  {
    key: "online-lecture-write",
    route: "/lecture/online/write",
    component: <OnlineLectureDetail />,
  },
  {
    key: "offline-lecture-detail",
    route: "/lecture/offline/detail",
    component: <OfflineLectureDetail />,
  },
  {
    key: "offline-lecture-write",
    route: "/lecture/offline/write",
    component: <OfflineLectureDetail />,
  },
  {
    key: "lecture-reply-detail",
    route: "/lecture/reply/detail",
    component: <LectureReplyDetail />,
  },
  {
    key: "event-detail",
    route: "/event/detail",
    component: <EventDetail />,
  },
  {
    key: "note-detail",
    route: "/note/detail",
    component: <NoteDetail />,
  },
  {
    key: "popup-detail",
    route: "/popup/detail",
    component: <PopupDetail />,
  },
  {
    type: "collapse",
    name: "Member",
    key: "Member",
    icon: <Icon fontSize="small">notifications</Icon>,
    route: "/member",
    component: <Member />,
  },
  {
    type: "collapse",
    name: "Exam Management",
    key: "exam-management",
    icon: <Icon fontSize="small">quiz</Icon>,
    collapse: [
      {
        name: "Sample Gosi",
        key: "sample-gosi",
        route: "/exam/gosi",
        component: <GosiList />,
      },
      {
        name: "Mock Test",
        key: "mock-test",
        route: "/exam/moui",
        component: <MouiList />,
      },
    ],
  },
  {
    key: "gosi-detail",
    route: "/exam/gosi/detail",
    component: <GosiDetail />,
  },
  {
    key: "gosi-write",
    route: "/exam/gosi/write",
    component: <GosiDetail />,
  },
  {
    key: "moui-detail",
    route: "/exam/moui/detail",
    component: <MouiDetail />,
  },
  {
    key: "moui-write",
    route: "/exam/moui/write",
    component: <MouiDetail />,
  },
  {
    type: "collapse",
    name: "Menu",
    key: "menu",
    icon: <Icon fontSize="small">menu</Icon>,
    route: "/menu",
    component: <Menu />,
  },
  {
    type: "collapse",
    name: "Coop",
    key: "coop",
    icon: <Icon fontSize="small">business</Icon>,
    collapse: [
      {
        name: "Coop Management",
        key: "coop-management",
        route: "/coop",
        component: <CoopList />,
      },
      {
        name: "Coop Board",
        key: "coop-board",
        route: "/coop/board",
        component: <CoopBoardList />,
      },
      {
        name: "Coop Order",
        key: "coop-order",
        route: "/coop/order",
        component: <CoopOrderList />,
      },
    ],
  },
  {
    key: "coop-board-write",
    route: "/coop/board/write",
    component: <CoopBoardWrite />,
  },
  {
    key: "coop-board-detail",
    route: "/coop/board/detail",
    component: <CoopBoardWrite />,
  },
  {
    type: "collapse",
    name: "Counsel",
    key: "counsel",
    icon: <Icon fontSize="small">help</Icon>,
    collapse: [
      {
        name: "Counsel Management",
        key: "counsel-management",
        route: "/counsel",
        component: <CounselList />,
      },
    ],
  },
  {
    key: "counsel-write",
    route: "/counsel/write",
    component: <CounselWrite />,
  },
  {
    key: "counsel-detail",
    route: "/counsel/detail",
    component: <CounselDetail />,
  },
  {
    type: "collapse",
    name: "D-Day",
    key: "dday",
    icon: <Icon fontSize="small">today</Icon>,
    route: "/dday",
    component: <DdayList />,
  },
  {
    key: "dday-detail",
    route: "/dday/detail",
    component: <DdayDetail />,
  },
  {
    type: "collapse",
    name: "Book",
    key: "book",
    icon: <Icon fontSize="small">library_books</Icon>,
    route: "/book",
    component: <BookList />,
  },
  {
    key: "book-write",
    route: "/book/write",
    component: <BookWrite />,
  },
  {
    type: "collapse",
    name: "Billing",
    key: "billing",
    icon: <Icon fontSize="small">receipt_long</Icon>,
    route: "/billing",
    component: <Billing />,
  },
  {
    type: "collapse",
    name: "운영자관리",
    key: "admin-management",
    icon: <Icon fontSize="small">admin_panel_settings</Icon>,
    collapse: [
      {
        name: "코드관리",
        key: "code-management",
        route: "/admin/code",
        component: <AdminCode />,
      },
      {
        name: "관리자 메뉴관리",
        key: "admin-menu-management",
        route: "/admin/admin-menu",
        component: <AdminMenu />,
      },
      {
        name: "권한관리",
        key: "auth-management",
        route: "/admin/auth",
        component: <AdminAuth />,
      },
      {
        name: "사용자 메뉴관리",
        key: "user-menu-management",
        route: "/admin/user-menu",
        component: <Menu />,
      },
      {
        name: "배너관리",
        key: "banner-management",
        route: "/admin/banner",
        component: <AdminBanner />,
      },
    ],
  },
  {
    type: "collapse",
    name: "Profile",
    key: "profile",
    icon: <Icon fontSize="small">person</Icon>,
    route: "/profile",
    component: <Profile />,
  },
  {
    type: "collapse",
    name: "Sign In",
    key: "sign-in",
    icon: <Icon fontSize="small">login</Icon>,
    route: "/authentication/sign-in",
    component: <SignIn />,
  },
  {
    type: "collapse",
    name: "Sign Up",
    key: "sign-up",
    icon: <Icon fontSize="small">assignment</Icon>,
    route: "/authentication/sign-up",
    component: <SignUp />,
  },
];

export default routes;
