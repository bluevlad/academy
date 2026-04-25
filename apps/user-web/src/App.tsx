import { Navigate, Route, Routes } from 'react-router-dom';
import { RequireAuth } from '@academy/ui-core';
import { LoginPage } from './pages/LoginPage';
import { SignupPage } from './pages/SignupPage';
import { DashboardPage } from './pages/DashboardPage';
import { UserShell } from './pages/UserShell';
import { HomePage } from './pages/HomePage';
import { LectureListPage } from './pages/lecture/LectureListPage';
import { LectureDetailPage } from './pages/lecture/LectureDetailPage';
import { CartPage } from './pages/cart/CartPage';
import { MyLecturePage } from './pages/mypage/MyLecturePage';
import { ProfilePage } from './pages/mypage/ProfilePage';
import { MockExamsPage } from './pages/mocktest/MockExamsPage';
import { InquiryWritePage } from './pages/support/InquiryWritePage';
import { MyInquiriesPage } from './pages/support/MyInquiriesPage';
import { InquiryDetailPage } from './pages/support/InquiryDetailPage';

export function App() {
  return (
    <Routes>
      {/* Public — 로그인 불필요 */}
      <Route path="/" element={<DashboardPage />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/signup" element={<SignupPage />} />

      {/* 학생 영역 — 로그인 필수 */}
      <Route
        element={
          <RequireAuth requireRole="USER">
            <UserShell />
          </RequireAuth>
        }
      >
        <Route path="/home" element={<HomePage />} />
        <Route path="/lectures" element={<LectureListPage />} />
        <Route path="/lectures/:mstCode" element={<LectureDetailPage />} />
        <Route path="/cart" element={<CartPage />} />
        <Route path="/mypage/profile" element={<ProfilePage />} />
        <Route path="/mypage/mylecture" element={<MyLecturePage />} />
        <Route path="/mocktest" element={<MockExamsPage />} />
        <Route path="/support/inquiry" element={<InquiryWritePage />} />
        <Route path="/support/inquiries" element={<MyInquiriesPage />} />
        <Route path="/support/inquiries/:csSeq" element={<InquiryDetailPage />} />
      </Route>

      <Route path="*" element={<Navigate to="/" replace />} />
    </Routes>
  );
}
