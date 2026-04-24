import { Navigate, Route, Routes } from 'react-router-dom';
import { RequireAuth } from '@academy/ui-core';
import { LoginPage } from './pages/LoginPage';
import { AdminShell } from './pages/AdminShell';
import { DashboardPage } from './pages/DashboardPage';
import { MembersPage } from './pages/members/MembersPage';
import { SubjectsPage } from './pages/subjects/SubjectsPage';
import { LecturesPage } from './pages/lectures/LecturesPage';
import { InstructorsPage } from './pages/instructors/InstructorsPage';
import { InquiriesPage } from './pages/inquiries/InquiriesPage';
import { OrdersPage } from './pages/orders/OrdersPage';
import { CouponsPage } from './pages/coupons/CouponsPage';
import { BooksPage } from './pages/books/BooksPage';
import { MockExamsPage } from './pages/mocktest/MockExamsPage';

export function App() {
  return (
    <Routes>
      <Route path="/login" element={<LoginPage />} />
      <Route
        path="/"
        element={
          <RequireAuth requireRole="ADMIN">
            <AdminShell />
          </RequireAuth>
        }
      >
        <Route index element={<Navigate to="/dashboard" replace />} />
        <Route path="dashboard" element={<DashboardPage />} />
        <Route path="members" element={<MembersPage />} />
        <Route path="subjects" element={<SubjectsPage />} />
        <Route path="lectures" element={<LecturesPage />} />
        <Route path="instructors" element={<InstructorsPage />} />
        <Route path="orders" element={<OrdersPage />} />
        <Route path="coupons" element={<CouponsPage />} />
        <Route path="books" element={<BooksPage />} />
        <Route path="mocktest" element={<MockExamsPage />} />
        <Route path="support/inquiries" element={<InquiriesPage />} />
      </Route>
      <Route path="*" element={<Navigate to="/" replace />} />
    </Routes>
  );
}
