import { PageContainer } from '@academy/ui-core';
import { Alert } from 'antd';

export function LecturesPage() {
  return (
    <PageContainer title="강의 · 과목 · 교수진" crumbs={[{ label: '운영' }, { label: '강의' }]}>
      <Alert
        type="info"
        message="강의/과목/교수진 CRUD 구현 예정"
        description="/api/lecture/* · /api/subject/* · /api/teacher/* (기존 admin API, Sprint 1-5 로 ROLE_ADMIN 자동 보호). Oracle 레거시 쿼리의 MariaDB 호환은 QA 단계에서 개별 교정"
      />
    </PageContainer>
  );
}
