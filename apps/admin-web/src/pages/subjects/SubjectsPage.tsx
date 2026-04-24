import { useMemo, useState } from 'react';
import { Alert, Button, Flex, Form, Input, Select, Space, Table, Tag } from 'antd';
import type { ColumnsType } from 'antd/es/table';
import { ReloadOutlined, SearchOutlined } from '@ant-design/icons';
import { useQuery } from '@tanstack/react-query';
import { PageContainer } from '@academy/ui-core';
import { listSubjects, type Subject, type SubjectSearch } from '../../api/subjects';

type SearchForm = {
  sjtNm?: string;
  isUse?: '' | 'Y' | 'N';
};

export function SubjectsPage() {
  const [searchForm] = Form.useForm<SearchForm>();
  const [search, setSearch] = useState<SearchForm>({});
  const [page, setPage] = useState(1);
  const [pageSize, setPageSize] = useState(10);

  const params: SubjectSearch = useMemo(
    () => ({ ...search, currentPage: page, pageRow: pageSize }),
    [search, page, pageSize],
  );

  const listQuery = useQuery({
    queryKey: ['subjects', params],
    queryFn: () => listSubjects(params),
    placeholderData: (prev) => prev,
    retry: false,
  });

  const sqlError = listQuery.isError;

  const columns: ColumnsType<Subject> = [
    { title: '과목코드', dataIndex: 'SJT_CD', key: 'SJT_CD', width: 140 },
    { title: '과목명', dataIndex: 'SJT_NM', key: 'SJT_NM' },
    {
      title: '깊이',
      dataIndex: 'SJT_DEPTH',
      key: 'SJT_DEPTH',
      width: 80,
      align: 'right',
      render: (v) => v ?? '-',
    },
    {
      title: '순서',
      dataIndex: 'SJT_ORDR',
      key: 'SJT_ORDR',
      width: 80,
      align: 'right',
      render: (v) => v ?? '-',
    },
    {
      title: '사용',
      dataIndex: 'IS_USE',
      key: 'IS_USE',
      width: 70,
      render: (v: string) => <Tag color={v === 'Y' ? 'green' : 'red'}>{v || '-'}</Tag>,
    },
    {
      title: '등록일',
      dataIndex: 'REG_DT',
      key: 'REG_DT',
      width: 170,
      render: (v: string) => (v ? String(v).replace('T', ' ').slice(0, 19) : '-'),
    },
  ];

  return (
    <PageContainer
      title="과목관리"
      crumbs={[{ label: '학사관리' }, { label: '과목관리' }]}
      extra={
        <Space>
          <Button icon={<ReloadOutlined />} onClick={() => listQuery.refetch()}>
            새로고침
          </Button>
        </Space>
      }
    >
      {sqlError && (
        <Alert
          type="warning"
          showIcon
          style={{ marginBottom: 16 }}
          message="백엔드 SQL 마이그레이션 대기 중"
          description={
            <span>
              <code>/api/subject/list</code> 가 Oracle 잔존 SQL 로 인해 MariaDB 환경에서
              500 응답 중. <code>backend/src/main/resources/mapper/lectureSubjectSQL.xml</code>{' '}
              MariaDB 호환화 후 본 화면이 활성화됩니다. 메뉴 구조·UI 골격은 미리 준비.
            </span>
          }
        />
      )}

      <Form<SearchForm>
        form={searchForm}
        layout="inline"
        onFinish={(v) => {
          setSearch(v);
          setPage(1);
        }}
        style={{ marginBottom: 16, rowGap: 8 }}
      >
        <Form.Item name="sjtNm" label="과목명">
          <Input allowClear placeholder="과목명" style={{ width: 200 }} />
        </Form.Item>
        <Form.Item name="isUse" label="사용여부" initialValue="">
          <Select
            style={{ width: 110 }}
            options={[
              { value: '', label: '전체' },
              { value: 'Y', label: '사용' },
              { value: 'N', label: '미사용' },
            ]}
          />
        </Form.Item>
        <Form.Item>
          <Space>
            <Button type="primary" htmlType="submit" icon={<SearchOutlined />}>
              검색
            </Button>
            <Button
              onClick={() => {
                searchForm.resetFields();
                setSearch({});
                setPage(1);
              }}
            >
              초기화
            </Button>
          </Space>
        </Form.Item>
      </Form>

      <Flex vertical>
        <Table<Subject>
          rowKey="SJT_CD"
          columns={columns}
          dataSource={listQuery.data?.items ?? []}
          loading={listQuery.isFetching}
          size="middle"
          pagination={{
            current: page,
            pageSize,
            total: listQuery.data?.totalCount ?? 0,
            showSizeChanger: true,
            showTotal: (t) => `총 ${t.toLocaleString()}건`,
            onChange: (p, size) => {
              setPage(p);
              setPageSize(size);
            },
          }}
        />
      </Flex>
    </PageContainer>
  );
}
