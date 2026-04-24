import { useMemo, useState } from 'react';
import {
  Button,
  Drawer,
  Flex,
  Form,
  Input,
  InputNumber,
  Popconfirm,
  Radio,
  Select,
  Space,
  Table,
  Tag,
  message,
} from 'antd';
import type { ColumnsType } from 'antd/es/table';
import { PlusOutlined, ReloadOutlined, SearchOutlined } from '@ant-design/icons';
import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query';
import { PageContainer } from '@academy/ui-core';
import {
  deleteMember,
  getMemberDetail,
  insertMember,
  listMembers,
  updateMember,
  type Member,
  type MemberSearch,
  type MemberUpsert,
} from '../../api/members';

type SearchForm = {
  userId?: string;
  userNm?: string;
  email?: string;
  isUse?: '' | 'Y' | 'N';
  userRole?: '' | 'USER' | 'PRF' | 'ADMIN';
};

type DrawerMode = 'create' | 'edit' | null;

const ROLE_COLOR: Record<string, string> = {
  USER: 'blue',
  PRF: 'green',
  ADMIN: 'red',
};

const ROLE_LABEL: Record<string, string> = {
  USER: '회원',
  PRF: '강사',
  ADMIN: '관리자',
};

export function MembersPage() {
  const queryClient = useQueryClient();
  const [searchForm] = Form.useForm<SearchForm>();
  const [upsertForm] = Form.useForm<MemberUpsert>();

  const [search, setSearch] = useState<SearchForm>({});
  const [page, setPage] = useState(1);
  const [pageSize, setPageSize] = useState(10);

  const [drawerMode, setDrawerMode] = useState<DrawerMode>(null);
  const [editTarget, setEditTarget] = useState<string | null>(null);

  const params: MemberSearch = useMemo(
    () => ({
      ...search,
      curPage: page,
      pageUnit: pageSize,
      pageSize: 10,
    }),
    [search, page, pageSize],
  );

  const listQuery = useQuery({
    queryKey: ['members', params],
    queryFn: () => listMembers(params),
    placeholderData: (prev) => prev,
  });

  const invalidate = () => queryClient.invalidateQueries({ queryKey: ['members'] });

  const insertMutation = useMutation({
    mutationFn: insertMember,
    onSuccess: () => {
      message.success('회원을 등록했습니다.');
      invalidate();
      closeDrawer();
    },
    onError: (e: Error) => message.error(e.message),
  });

  const updateMutation = useMutation({
    mutationFn: updateMember,
    onSuccess: () => {
      message.success('회원 정보를 수정했습니다.');
      invalidate();
      closeDrawer();
    },
    onError: (e: Error) => message.error(e.message),
  });

  const deleteMutation = useMutation({
    mutationFn: deleteMember,
    onSuccess: () => {
      message.success('회원을 삭제했습니다.');
      invalidate();
    },
    onError: (e: Error) => message.error(e.message),
  });

  function openCreate() {
    upsertForm.resetFields();
    upsertForm.setFieldsValue({
      userRole: 'USER',
      isUse: 'Y',
      isokSms: 'N',
      isokEmail: 'N',
    });
    setEditTarget(null);
    setDrawerMode('create');
  }

  async function openEdit(userId: string) {
    try {
      const detail = await getMemberDetail(userId);
      if (!detail) {
        message.warning('회원 정보를 찾지 못했습니다.');
        return;
      }
      upsertForm.resetFields();
      upsertForm.setFieldsValue({
        userId: detail.USER_ID,
        userNm: detail.USER_NM,
        sex: detail.SEX ?? undefined,
        userRole: (detail.USER_ROLE as string) ?? 'USER',
        adminRole: detail.ADMIN_ROLE ?? undefined,
        birthDay: detail.BIRTH_DAY ?? undefined,
        email: detail.EMAIL ?? undefined,
        zipCode: detail.ZIP_CODE ?? undefined,
        address1: detail.ADDRESS1 ?? undefined,
        address2: detail.ADDRESS2 ?? undefined,
        userPoint: (detail.USER_POINT as number | string) ?? 0,
        memo: detail.MEMO ?? undefined,
        pic: detail.PIC ?? undefined,
        isUse: (detail.IS_USE as 'Y' | 'N') ?? 'Y',
        isokSms: (detail.ISOK_SMS as 'Y' | 'N') ?? 'N',
        isokEmail: (detail.ISOK_EMAIL as 'Y' | 'N') ?? 'N',
      });
      setEditTarget(userId);
      setDrawerMode('edit');
    } catch (err) {
      message.error((err as Error).message);
    }
  }

  function closeDrawer() {
    setDrawerMode(null);
    setEditTarget(null);
    upsertForm.resetFields();
  }

  async function onSubmitUpsert() {
    const values = await upsertForm.validateFields();
    const payload: MemberUpsert = {
      ...values,
      userPoint:
        values.userPoint === undefined || values.userPoint === null || values.userPoint === ''
          ? 0
          : values.userPoint,
    };
    if (drawerMode === 'create') {
      insertMutation.mutate(payload);
    } else if (drawerMode === 'edit' && editTarget) {
      updateMutation.mutate({ ...payload, userId: editTarget });
    }
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

  const columns: ColumnsType<Member> = [
    {
      title: '아이디',
      dataIndex: 'USER_ID',
      key: 'USER_ID',
      width: 140,
      render: (v: string) => <a onClick={() => openEdit(v)}>{v}</a>,
    },
    { title: '성명', dataIndex: 'USER_NM', key: 'USER_NM', width: 120 },
    {
      title: '권한',
      dataIndex: 'USER_ROLE',
      key: 'USER_ROLE',
      width: 90,
      render: (v: string) => (
        <Tag color={ROLE_COLOR[v] ?? 'default'}>{v ? `${ROLE_LABEL[v] ?? v} (${v})` : '-'}</Tag>
      ),
    },
    {
      title: '성별',
      dataIndex: 'SEX',
      key: 'SEX',
      width: 70,
      render: (v) => v || '-',
    },
    { title: '이메일', dataIndex: 'EMAIL', key: 'EMAIL', ellipsis: true },
    {
      title: '생년월일',
      dataIndex: 'BIRTH_DAY',
      key: 'BIRTH_DAY',
      width: 110,
      render: (v) => v || '-',
    },
    {
      title: '포인트',
      dataIndex: 'USER_POINT',
      key: 'USER_POINT',
      width: 90,
      align: 'right',
      render: (v) => (v == null ? 0 : Number(v).toLocaleString()),
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
    {
      title: '작업',
      key: 'actions',
      width: 150,
      fixed: 'right',
      render: (_, row) => (
        <Space size="small">
          <Button size="small" onClick={() => openEdit(row.USER_ID)}>
            수정
          </Button>
          <Popconfirm
            title="정말 삭제하시겠어요?"
            description={`${row.USER_ID} 회원을 삭제합니다.`}
            okText="삭제"
            cancelText="취소"
            okButtonProps={{ danger: true }}
            onConfirm={() => deleteMutation.mutate(row.USER_ID)}
          >
            <Button size="small" danger>
              삭제
            </Button>
          </Popconfirm>
        </Space>
      ),
    },
  ];

  const total = listQuery.data?.pagination.totalRecordCount ?? 0;

  return (
    <PageContainer
      title="회원관리"
      crumbs={[{ label: '운영' }, { label: '회원관리' }]}
      extra={
        <Space>
          <Button icon={<ReloadOutlined />} onClick={() => listQuery.refetch()}>
            새로고침
          </Button>
          <Button type="primary" icon={<PlusOutlined />} onClick={openCreate}>
            신규 등록
          </Button>
        </Space>
      }
    >
      <Form<SearchForm>
        form={searchForm}
        layout="inline"
        onFinish={onSubmitSearch}
        style={{ marginBottom: 16, rowGap: 8 }}
      >
        <Form.Item name="userId" label="아이디">
          <Input allowClear placeholder="아이디" style={{ width: 160 }} />
        </Form.Item>
        <Form.Item name="userNm" label="성명">
          <Input allowClear placeholder="성명" style={{ width: 160 }} />
        </Form.Item>
        <Form.Item name="email" label="이메일">
          <Input allowClear placeholder="이메일" style={{ width: 200 }} />
        </Form.Item>
        <Form.Item name="userRole" label="권한" initialValue="">
          <Select
            style={{ width: 140 }}
            options={[
              { value: '', label: '전체' },
              { value: 'USER', label: '회원 (USER)' },
              { value: 'PRF', label: '강사 (PRF)' },
              { value: 'ADMIN', label: '관리자 (ADMIN)' },
            ]}
          />
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

      <Flex vertical gap={8}>
        <Table<Member>
          rowKey="USER_ID"
          columns={columns}
          dataSource={listQuery.data?.items ?? []}
          loading={listQuery.isFetching}
          scroll={{ x: 1200 }}
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
      </Flex>

      <Drawer
        title={drawerMode === 'create' ? '회원 신규 등록' : `회원 수정 — ${editTarget ?? ''}`}
        width={560}
        open={drawerMode !== null}
        onClose={closeDrawer}
        destroyOnClose
        extra={
          <Space>
            <Button onClick={closeDrawer}>취소</Button>
            <Button
              type="primary"
              loading={insertMutation.isPending || updateMutation.isPending}
              onClick={onSubmitUpsert}
            >
              저장
            </Button>
          </Space>
        }
      >
        <Form<MemberUpsert> form={upsertForm} layout="vertical">
          <Form.Item
            name="userId"
            label="아이디"
            rules={[{ required: true, message: '아이디는 필수입니다.' }]}
          >
            <Input disabled={drawerMode === 'edit'} maxLength={50} />
          </Form.Item>
          <Form.Item
            name="userNm"
            label="성명"
            rules={[{ required: true, message: '성명은 필수입니다.' }]}
          >
            <Input maxLength={50} />
          </Form.Item>
          <Form.Item
            name="userPwd"
            label={drawerMode === 'edit' ? '비밀번호 (변경 시에만 입력)' : '비밀번호'}
            rules={
              drawerMode === 'create'
                ? [{ required: true, message: '비밀번호는 필수입니다.' }]
                : []
            }
            tooltip="서버에서 BCrypt 해시로 저장됩니다."
          >
            <Input.Password autoComplete="new-password" maxLength={100} />
          </Form.Item>
          <Flex gap={12}>
            <Form.Item name="userRole" label="권한" style={{ flex: 1 }}>
              <Select
                options={[
                  { value: 'USER', label: '회원 (USER)' },
                  { value: 'PRF', label: '강사 (PRF)' },
                  { value: 'ADMIN', label: '관리자 (ADMIN)' },
                ]}
              />
            </Form.Item>
            <Form.Item name="adminRole" label="관리 권한 코드" style={{ flex: 1 }}>
              <Input placeholder="관리자 일 때만" maxLength={50} />
            </Form.Item>
          </Flex>
          <Flex gap={12}>
            <Form.Item name="sex" label="성별" style={{ width: 160 }}>
              <Radio.Group
                options={[
                  { value: 'M', label: '남' },
                  { value: 'F', label: '여' },
                ]}
                optionType="button"
              />
            </Form.Item>
            <Form.Item name="birthDay" label="생년월일 (YYYYMMDD)" style={{ flex: 1 }}>
              <Input maxLength={10} placeholder="예: 20000101" />
            </Form.Item>
          </Flex>
          <Form.Item
            name="email"
            label="이메일"
            rules={[{ type: 'email', message: '이메일 형식이 올바르지 않습니다.' }]}
          >
            <Input autoComplete="off" maxLength={100} />
          </Form.Item>
          <Flex gap={12}>
            <Form.Item name="zipCode" label="우편번호" style={{ width: 160 }}>
              <Input maxLength={10} />
            </Form.Item>
            <Form.Item name="address1" label="주소" style={{ flex: 1 }}>
              <Input maxLength={200} />
            </Form.Item>
          </Flex>
          <Form.Item name="address2" label="상세주소">
            <Input maxLength={200} />
          </Form.Item>
          <Flex gap={12}>
            <Form.Item name="userPoint" label="포인트" style={{ width: 160 }}>
              <InputNumber min={0} style={{ width: '100%' }} />
            </Form.Item>
            <Form.Item name="isUse" label="사용여부" style={{ width: 160 }}>
              <Radio.Group
                options={[
                  { value: 'Y', label: '사용' },
                  { value: 'N', label: '미사용' },
                ]}
                optionType="button"
              />
            </Form.Item>
            <Form.Item name="isokSms" label="SMS 수신" style={{ width: 160 }}>
              <Radio.Group
                options={[
                  { value: 'Y', label: '동의' },
                  { value: 'N', label: '거부' },
                ]}
                optionType="button"
              />
            </Form.Item>
            <Form.Item name="isokEmail" label="이메일 수신" style={{ width: 160 }}>
              <Radio.Group
                options={[
                  { value: 'Y', label: '동의' },
                  { value: 'N', label: '거부' },
                ]}
                optionType="button"
              />
            </Form.Item>
          </Flex>
          <Form.Item name="memo" label="관리 메모">
            <Input.TextArea rows={3} maxLength={500} showCount />
          </Form.Item>
        </Form>
      </Drawer>
    </PageContainer>
  );
}
