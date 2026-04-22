import { Navigate, Route, Routes } from 'react-router-dom';
import { RequireAuth } from '@academy/ui-core';
import { LoginPage } from './pages/LoginPage';
import { SignupPage } from './pages/SignupPage';
import { UserShell } from './pages/UserShell';
import { HomePage } from './pages/HomePage';
import { LectureListPage } from './pages/lecture/LectureListPage';
import { LectureDetailPage } from './pages/lecture/LectureDetailPage';
import { CartPage } from './pages/cart/CartPage';
import { MyLecturePage } from './pages/mypage/MyLecturePage';
import { ProfilePage } from './pages/mypage/ProfilePage';
import { MockExamsPage } from './pages/mocktest/MockExamsPage';

export function App() {
  return (
    <Routes>
      <Route path="/login" element={<LoginPage />} />
      <Route path="/signup" element={<SignupPage />} />
      <Route
        path="/"
        element={
          <RequireAuth requireRole="USER">
            <UserShell />
          </RequireAuth>
        }
      >
        <Route index element={<HomePage />} />
        <Route path="lectures" element={<LectureListPage />} />
        <Route path="lectures/:mstCode" element={<LectureDetailPage />} />
        <Route path="cart" element={<CartPage />} />
        <Route path="mypage/profile" element={<ProfilePage />} />
        <Route path="mypage/mylecture" element={<MyLecturePage />} />
        <Route path="mocktest" element={<MockExamsPage />} />
      </Route>
      <Route path="*" element={<Navigate to="/" replace />} />
    </Routes>
  );
}
