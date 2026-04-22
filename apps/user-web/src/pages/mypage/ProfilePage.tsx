import { PageContainer } from '@academy/ui-core';
import { Alert } from 'antd';

export function ProfilePage() {
  return (
    <PageContainer title="내 정보" crumbs={[{ label: '마이' }, { label: '내 정보' }]}>
      <Alert
        type="info"
        message="프로필 조회·수정 · 비밀번호 변경 · 회원탈퇴 · 수강확인증 구현 예정"
        description="GET/PUT /api/user/mypage/profile · PUT /password · DELETE /account · GET /certificate (P0)"
      />
    </PageContainer>
  );
}
