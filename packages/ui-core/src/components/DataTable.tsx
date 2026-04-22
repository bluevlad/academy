import { Table } from 'antd';
import type { TableProps } from 'antd';
import type { PagedResponse } from '../types';

export interface DataTableProps<T> {
  paged?: PagedResponse<T>;
  rows?: T[];
  loading?: boolean;
  columns: TableProps<T>['columns'];
  rowKey: keyof T | ((row: T) => string);
  onChange?: (page: number, size: number) => void;
}

/**
 * 공용 테이블 — PagedResponse 또는 rows 배열 어느 쪽이든 수용.
 * Ant Design `Table` 의 얇은 래퍼, pagination 을 서버사이드 PagedResponse 와 연결.
 */
export function DataTable<T extends Record<string, unknown>>({
  paged,
  rows,
  loading,
  columns,
  rowKey,
  onChange,
}: DataTableProps<T>) {
  const dataSource = paged?.items ?? rows ?? [];
  const pagination = paged
    ? {
        current: paged.page,
        pageSize: paged.size,
        total: paged.totalItems,
        showSizeChanger: true,
      }
    : false;

  return (
    <Table<T>
      columns={columns}
      dataSource={dataSource}
      loading={loading}
      rowKey={rowKey as TableProps<T>['rowKey']}
      pagination={pagination}
      onChange={(p) => {
        if (onChange && p.current && p.pageSize) {
          onChange(p.current, p.pageSize);
        }
      }}
      size="middle"
    />
  );
}
