import { useMemo, useState } from 'react';
import {
  Button,
  Descriptions,
  Drawer,
  Flex,
  Form,
  Input,
  Select,
  Space,
  Table,
  Tag,
  message,
} from 'antd';
import type { ColumnsType } from 'antd/es/table';
import { ReloadOutlined, SearchOutlined } from '@ant-design/icons';
import { useQuery } from '@tanstack/react-query';
import { PageContainer } from '@academy/ui-core';
import {
  listInstructors,
  getInstructorDetail,
  type Instructor,
  type InstructorDetail,
  type InstructorSearch,
} from '../../api/instructors';

type SearchForm = {
  userId?: string;
  userNm?: string;
  email?: string;
  isUse?: '' | 'Y' | 'N';
};

export function InstructorsPage() {
  const [searchForm] = Form.useForm<SearchForm>();
  const [search, setSearch] = useState<SearchForm>({});
  const [page, setPage] = useState(1);
  const [pageSize, setPageSize] = useState(10);
  const [detail, setDetail] = useState<InstructorDetail | null>(null);
  const [drawerOpen, setDrawerOpen] = useState(false);
  const [detailLoading, setDetailLoading] = useState(false);

  const params: InstructorSearch = useMemo(
    () => ({ ...search, curPage: page, pageUnit: pageSize, pageSize: 10 }),
    [search, page, pageSize],
  );

  const listQuery = useQuery({
    queryKey: ['instructors', params],
    queryFn: () => listInstructors(params),
    placeholderData: (prev) => prev,
  });

  async function openDetail(userId: string) {
    setDrawerOpen(true);
    setDetailLoading(true);
    try {
      const d = await getInstructorDetail(userId);
      if (!d) {
        message.warning('강사 정보를 찾지 못했습니다.');
        setDrawerOpen(false);
        return;
      }
      setDetail(d);
    } catch (e) {
      message.error((e as Error).message);
      setDrawerOpen(false);
    } finally {
      setDetailLoading(false);
    }
  }

  function closeDetail() {
    setDrawerOpen(false);
    setDetail(null);
  }

  function onSubmitSearch(values: SearchForm) {
    setSearch(values);
    setPage(1);
  }
  function onResetSearch() {
    searchForm.resetFields();
    setSearch({});
    setPage(1);
  }

  const columns: ColumnsType<Instructor> = [
    {
      title: '아이디',
      dataIndex: 'USER_ID',
      key: 'USER_ID',
      width: 140,
      render: (v: string) => <a onClick={() => openDetail(v)}>{v}</a>,
    },
    { title: '강사명', dataIndex: 'USER_NM', key: 'USER_NM', width: 140 },
    {
      title: '권한',
      dataIndex: 'USER_ROLE',
      key: 'USER_ROLE',
      width: 90,
      render: () => <Tag color="green">강사</Tag>,
    },
    { title: '이메일', dataIndex: 'EMAIL', key: 'EMAIL', ellipsis: true },
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

  const total = listQuery.data?.pagination.totalRecordCount ?? 0;

  return (
    <PageContainer
      title="강사관리"
      crumbs={[{ label: '학사관리' }, { label: '강사관리' }]}
      extra={
        <Space>
          <Button icon={<ReloadOutlined />} onClick={() => listQuery.refetch()}>
            새로고침
          </Button>
        </Space>
      }
    >
      <Flex vertical gap={4} style={{ marginBottom: 16 }}>
        <span style={{ color: '#94a3b8', fontSize: 12 }}>
          ※ 강사 = <code>acm_member.user_role = 'PRF'</code> 회원. 신규 등록·수정·삭제는
          회원관리 화면에서 권한 = 강사로 처리.
        </span>
      </Flex>

      <Form<SearchForm>
        form={searchForm}
        layout="inline"
        onFinish={onSubmitSearch}
        style={{ marginBottom: 16, rowGap: 8 }}
      >
        <Form.Item name="userId" label="아이디">
          <Input allowClear placeholder="아이디" style={{ width: 160 }} />
        </Form.Item>
        <Form.Item name="userNm" label="강사명">
          <Input allowClear placeholder="강사명" style={{ width: 160 }} />
        </Form.Item>
        <Form.Item name="email" label="이메일">
          <Input allowClear placeholder="이메일" style={{ width: 200 }} />
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
            <Button onClick={onResetSearch}>초기화</Button>
          </Space>
        </Form.Item>
      </Form>

      <Table<Instructor>
        rowKey="USER_ID"
        columns={columns}
        dataSource={listQuery.data?.items ?? []}
        loading={listQuery.isFetching}
        scroll={{ x: 900 }}
        size="middle"
        pagination={{
          current: page,
          pageSize,
          total,
          showSizeChanger: true,
          showTotal: (t) => `총 ${t.toLocaleString()}명`,
          onChange: (p, size) => {
            setPage(p);
            setPageSize(size);
          },
        }}
      />

      <Drawer
        title={`강사 상세 — ${detail?.USER_ID ?? ''}`}
        width={520}
        open={drawerOpen}
        onClose={closeDetail}
        destroyOnClose
        loading={detailLoading}
      >
        {detail && (
          <Descriptions column={1} bordered size="small">
            <Descriptions.Item label="아이디">{detail.USER_ID}</Descriptions.Item>
            <Descriptions.Item label="강사명">{detail.USER_NM}</Descriptions.Item>
            <Descriptions.Item label="권한">
              <Tag color="green">강사 (PRF)</Tag>
            </Descriptions.Item>
            <Descriptions.Item label="이메일">{detail.EMAIL ?? '-'}</Descriptions.Item>
            <Descriptions.Item label="생년월일">{detail.BIRTH_DAY ?? '-'}</Descriptions.Item>
            <Descriptions.Item label="주소">
              {[detail.ZIP_CODE, detail.ADDRESS1, detail.ADDRESS2].filter(Boolean).join(' ') || '-'}
            </Descriptions.Item>
            <Descriptions.Item label="포인트">
              {detail.USER_POINT == null ? 0 : Number(detail.USER_POINT).toLocaleString()}
            </Descriptions.Item>
            <Descriptions.Item label="사용여부">
              <Tag color={detail.IS_USE === 'Y' ? 'green' : 'red'}>{detail.IS_USE ?? '-'}</Tag>
            </Descriptions.Item>
            <Descriptions.Item label="등록일">
              {detail.REG_DT ? String(detail.REG_DT).replace('T', ' ').slice(0, 19) : '-'}
            </Descriptions.Item>
            <Descriptions.Item label="메모">{detail.MEMO ?? '-'}</Descriptions.Item>
          </Descriptions>
        )}
        <div style={{ marginTop: 16, color: '#64748b', fontSize: 12 }}>
          담당 과목·강의 매핑은 다음 PR 에서 추가 예정 (강사↔강의 join 화면).
        </div>
      </Drawer>
    </PageContainer>
  );
}
