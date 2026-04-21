/**
 * TanStack Table 8 기반 공통 DataTable 컴포넌트
 *
 * 기존 react-table 7 DataTable과 호환되는 props 인터페이스 제공
 * - 서버사이드 페이지네이션 지원
 * - 컬럼 정렬
 * - 글로벌 검색
 *
 * Usage:
 *   <ServerDataTable
 *     columns={columns}
 *     data={rows}
 *     pagination={paginationInfo}
 *     onPageChange={handlePageChange}
 *     onSearch={handleSearch}
 *     loading={isLoading}
 *   />
 */
import { useState, useMemo } from "react";
import {
  useReactTable,
  getCoreRowModel,
  getSortedRowModel,
  flexRender,
} from "@tanstack/react-table";

// MUI Components
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableContainer from "@mui/material/TableContainer";
import TableRow from "@mui/material/TableRow";
import TableHead from "@mui/material/TableHead";
import TableCell from "@mui/material/TableCell";
import Pagination from "@mui/material/Pagination";
import TextField from "@mui/material/TextField";
import InputAdornment from "@mui/material/InputAdornment";
import Icon from "@mui/material/Icon";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import CircularProgress from "@mui/material/CircularProgress";
import Select from "@mui/material/Select";
import MenuItem from "@mui/material/MenuItem";

/**
 * 서버사이드 페이지네이션 DataTable
 *
 * @param {Object[]} columns - TanStack Table 컬럼 정의 [{ accessorKey, header, size?, cell? }]
 * @param {Object[]} data - 현재 페이지 데이터 배열
 * @param {Object} pagination - { currentPageNo, totalPageCount, totalRecordCount, recordCountPerPage }
 * @param {Function} onPageChange - (pageNo) => void
 * @param {Function} onSearch - (searchValue) => void (optional)
 * @param {Function} onPageSizeChange - (pageSize) => void (optional)
 * @param {boolean} loading - 로딩 상태
 * @param {boolean} canSearch - 검색 바 표시 여부
 * @param {number[]} pageSizeOptions - 페이지 크기 옵션 [10, 20, 50]
 */
export function ServerDataTable({
  columns,
  data = [],
  pagination = {},
  onPageChange,
  onSearch,
  onPageSizeChange,
  loading = false,
  canSearch = false,
  pageSizeOptions = [10, 20, 50],
}) {
  const [sorting, setSorting] = useState([]);
  const [searchValue, setSearchValue] = useState("");

  const {
    currentPageNo = 1,
    totalPageCount = 1,
    totalRecordCount = 0,
    recordCountPerPage = 10,
  } = pagination;

  const table = useReactTable({
    data,
    columns,
    state: { sorting },
    onSortingChange: setSorting,
    getCoreRowModel: getCoreRowModel(),
    getSortedRowModel: getSortedRowModel(),
    manualPagination: true,
    pageCount: totalPageCount,
  });

  const handleSearchKeyDown = (e) => {
    if (e.key === "Enter" && onSearch) {
      onSearch(searchValue);
    }
  };

  const handlePageSizeChange = (e) => {
    if (onPageSizeChange) {
      onPageSizeChange(e.target.value);
    }
  };

  // 시작/끝 항목 번호 계산
  const startEntry = totalRecordCount === 0 ? 0 : (currentPageNo - 1) * recordCountPerPage + 1;
  const endEntry = Math.min(currentPageNo * recordCountPerPage, totalRecordCount);

  return (
    <Box>
      {/* 상단 영역: 검색 + 페이지 크기 */}
      {(canSearch || onPageSizeChange) && (
        <Box display="flex" justifyContent="space-between" alignItems="center" mb={2} px={1}>
          {onPageSizeChange ? (
            <Box display="flex" alignItems="center" gap={1}>
              <Select
                size="small"
                value={recordCountPerPage}
                onChange={handlePageSizeChange}
                sx={{ minWidth: 80 }}
              >
                {pageSizeOptions.map((size) => (
                  <MenuItem key={size} value={size}>
                    {size}
                  </MenuItem>
                ))}
              </Select>
              <Typography variant="caption" color="text.secondary">
                entries per page
              </Typography>
            </Box>
          ) : (
            <Box />
          )}

          {canSearch && (
            <TextField
              size="small"
              placeholder="Search..."
              value={searchValue}
              onChange={(e) => setSearchValue(e.target.value)}
              onKeyDown={handleSearchKeyDown}
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start">
                    <Icon fontSize="small">search</Icon>
                  </InputAdornment>
                ),
              }}
              sx={{ width: 250 }}
            />
          )}
        </Box>
      )}

      {/* 테이블 본체 */}
      <TableContainer>
        <Table size="small">
          <TableHead>
            {table.getHeaderGroups().map((headerGroup) => (
              <TableRow key={headerGroup.id}>
                {headerGroup.headers.map((header) => (
                  <TableCell
                    key={header.id}
                    onClick={header.column.getToggleSortingHandler()}
                    sx={{
                      cursor: header.column.getCanSort() ? "pointer" : "default",
                      userSelect: "none",
                      fontWeight: 700,
                      fontSize: "0.65rem",
                      textTransform: "uppercase",
                      color: "text.secondary",
                      width: header.getSize() !== 150 ? header.getSize() : undefined,
                    }}
                  >
                    <Box display="flex" alignItems="center" gap={0.5}>
                      {flexRender(header.column.columnDef.header, header.getContext())}
                      {header.column.getIsSorted() === "asc" && (
                        <Icon sx={{ fontSize: "14px !important" }}>arrow_upward</Icon>
                      )}
                      {header.column.getIsSorted() === "desc" && (
                        <Icon sx={{ fontSize: "14px !important" }}>arrow_downward</Icon>
                      )}
                    </Box>
                  </TableCell>
                ))}
              </TableRow>
            ))}
          </TableHead>

          <TableBody>
            {loading ? (
              <TableRow>
                <TableCell colSpan={columns.length} align="center" sx={{ py: 5 }}>
                  <CircularProgress size={30} />
                </TableCell>
              </TableRow>
            ) : data.length === 0 ? (
              <TableRow>
                <TableCell colSpan={columns.length} align="center" sx={{ py: 5 }}>
                  <Typography variant="body2" color="text.secondary">
                    데이터가 없습니다.
                  </Typography>
                </TableCell>
              </TableRow>
            ) : (
              table.getRowModel().rows.map((row) => (
                <TableRow key={row.id} hover>
                  {row.getVisibleCells().map((cell) => (
                    <TableCell
                      key={cell.id}
                      sx={{ fontSize: "0.8rem", py: 1.2 }}
                    >
                      {flexRender(cell.column.columnDef.cell, cell.getContext())}
                    </TableCell>
                  ))}
                </TableRow>
              ))
            )}
          </TableBody>
        </Table>
      </TableContainer>

      {/* 하단 영역: 엔트리 정보 + 페이지네이션 */}
      <Box display="flex" justifyContent="space-between" alignItems="center" mt={2} px={1}>
        <Typography variant="caption" color="text.secondary">
          {totalRecordCount > 0
            ? `${startEntry} - ${endEntry} / 총 ${totalRecordCount}건`
            : ""}
        </Typography>

        {totalPageCount > 1 && (
          <Pagination
            count={totalPageCount}
            page={currentPageNo}
            onChange={(_, page) => onPageChange?.(page)}
            size="small"
            color="primary"
          />
        )}
      </Box>
    </Box>
  );
}

export default ServerDataTable;
