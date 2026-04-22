/**
=========================================================
* Material Dashboard 2 React - v2.2.0
=========================================================

* Product Page: https://www.creative-tim.com/product/material-dashboard-react
* Copyright 2023 Creative Tim (https://www.creative-tim.com)

Coded by www.creative-tim.com

 =========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
*/

/** 
  All of the routes for the Material Dashboard 2 React are added here,
  You can add a new route, customize the routes and delete the routes here.

  Once you add a new route on this file it will be visible automatically on
  the Sidenav.

  For adding a new route you can follow the existing routes in the routes array.
  1. The `type` key with the `collapse` value is used for a route.
  2. The `type` key with the `title` value is used for a title inside the Sidenav. 
  3. The `type` key with the `divider` value is used for a divider between Sidenav items.
  4. The `name` key is used for the name of the route on the Sidenav.
  5. The `key` key is used for the key of the route (It will help you with the key prop inside a loop).
  6. The `icon` key is used for the icon of the route on the Sidenav, you have to add a node.
  7. The `collapse` key is used for making a collapsible item on the Sidenav that has other routes
  inside (nested routes), you need to pass the nested routes inside an array as a value for the `collapse` key.
  8. The `route` key is used to store the route location which is used for the react router.
  9. The `href` key is used to store the external links location.
  10. The `title` key is only for the item with the type of `title` and its used for the title text on the Sidenav.
  10. The `component` key is used to store the component of its route.
*/

// Material Dashboard 2 React layouts
import Dashboard from "layouts/dashboard";
import Tables from "layouts/tables";
import Billing from "layouts/billing";
import Member from "layouts/member";
import Exam from "layouts/exam";
import GosiList from "layouts/exam/gosi";
import GosiDetail from "layouts/exam/gosi/detail";
import MouiList from "layouts/exam/moui";
import MouiDetail from "layouts/exam/moui/detail";
import Menu from "layouts/menu";
import Profile from "layouts/profile";
// Board Components
import BoardCounselRoom from "layouts/boardCounselRoom";
import BoardCustomerOn from "layouts/boardCustomerOn";
import BoardExamGuide from "layouts/boardExamGuide";
import BoardExamInfoOn from "layouts/boardExamInfoOn";
import BoardLibrary from "layouts/boardLibrary";
import BoardNotice from "layouts/boardNotice";
import Faq from "layouts/faq";
import BoardCommunity from "layouts/boardCommunity";
import SignIn from "layouts/authentication/sign-in";
import SignUp from "layouts/authentication/sign-up";

// Admin Components - placeholder components (to be implemented)
const AdminCode = () => null;
const AdminMenu = () => null;
const AdminAuth = () => null;
const UserMenu = () => null;
const AdminBanner = () => null;

// Book Components
import BookList from "layouts/book";
import BookWrite from "layouts/book/write";

// Coop Components
import CoopList from "layouts/coop";
import CoopBoardList from "layouts/coop/board";
import CoopBoardWrite from "layouts/coop/board/write";
import CoopOrderList from "layouts/coop/order";

// Counsel Components
import CounselList from "layouts/counsel";
import CounselWrite from "layouts/counsel/write";
import CounselDetail from "layouts/counsel/detail";

// D-Day Components
import DdayList from "layouts/dday";
import DdayDetail from "layouts/dday/detail";

// New Components (Event, Note, Popup, Stat, Survey)
import EventList from "layouts/event";
import EventDetail from "layouts/event/detail";
import NoteList from "layouts/note";
import NoteDetail from "layouts/note/detail";
import PopupList from "layouts/popup";
import PopupDetail from "layouts/popup/detail";
import StatList from "layouts/stat";
import SurveyList from "layouts/survey";

// Manage Components
import CategorySaleList from "layouts/manage/categorySale";
import LectureYearList from "layouts/manage/lectureYear";
import TeacherCalculateList from "layouts/manage/teacherCalculate";

// Order Components
import ProductOrderList from "layouts/order/productOrder";
import CouponList from "layouts/order/coupon";
import FreeOrderList from "layouts/order/freeOrder";

// Lecture Components
import OnlineLectureList from "layouts/lecture/online";
import OnlineLectureDetail from "layouts/lecture/online/detail";
import OfflineLectureList from "layouts/lecture/offline";
import OfflineLectureDetail from "layouts/lecture/offline/detail";
import LectureReplyList from "layouts/lecture/reply";
import LectureReplyDetail from "layouts/lecture/reply/detail";

// My Classroom Components
import MyClassroom from "layouts/myClassroom";
import MyClassroomWatch from "layouts/myClassroom/watch";

// @mui icons
import Icon from "@mui/material/Icon";

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
        name: "커뮤니티 게시판",
        key: "board-community",
        route: "/board/community",
        component: <BoardCommunity />,
      },
      {
        name: "상담실 게시판",
        key: "board-counsel-room",
        route: "/board/counsel-room",
        component: <BoardCounselRoom />,
      },
      {
        name: "고객의 소리",
        key: "board-customer-on",
        route: "/board/customer-on",
        component: <BoardCustomerOn />,
      },
      {
        name: "수험 정보 가이드",
        key: "board-exam-guide",
        route: "/board/exam-guide",
        component: <BoardExamGuide />,
      },
      {
        name: "시험 공고 (On)",
        key: "board-exam-info-on",
        route: "/board/exam-info-on",
        component: <BoardExamInfoOn />,
      },
      {
        name: "자료실",
        key: "board-library",
        route: "/board/library",
        component: <BoardLibrary />,
      },
      {
        name: "공지사항",
        key: "board-notice",
        route: "/board/notice",
        component: <BoardNotice />,
      },
      {
        name: "FAQ",
        key: "faq",
        route: "/board/faq",
        component: <Faq />,
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
    component: <CoopBoardWrite />, // Reuse write component for detail/edit
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
    name: "내 강의실",
    key: "my-classroom",
    icon: <Icon fontSize="small">video_library</Icon>,
    route: "/my-classroom",
    component: <MyClassroom />,
  },
  {
    key: "my-classroom-watch",
    route: "/my-classroom/watch",
    component: <MyClassroomWatch />,
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
        component: <UserMenu />,
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
