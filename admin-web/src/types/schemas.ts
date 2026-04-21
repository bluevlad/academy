/**
 * Zod 스키마 정의 예시
 *
 * 각 feature 모듈에서 자체 스키마를 정의할 수 있으나,
 * 공통적으로 재사용되는 스키마는 여기에 정의
 *
 * Usage:
 *   import { boardSchema, type BoardForm } from "types/schemas";
 *   const { control, handleSubmit } = useForm<BoardForm>({
 *     resolver: zodResolver(boardSchema),
 *   });
 */
import { z } from "zod";

/** 게시판 관리 폼 스키마 */
export const boardMngSchema = z.object({
  boardMngName: z.string().min(1, "게시판명을 입력하세요"),
  boardMngType: z.string().min(1, "게시판 타입을 선택하세요"),
  isUse: z.enum(["Y", "N"]),
  attachFileYn: z.enum(["Y", "N"]),
  replyYn: z.enum(["Y", "N"]),
  commentYn: z.enum(["Y", "N"]),
});

export type BoardMngForm = z.infer<typeof boardMngSchema>;

/** 게시물 폼 스키마 */
export const boardSchema = z.object({
  boardTitle: z.string().min(1, "제목을 입력하세요"),
  boardContent: z.string().min(1, "내용을 입력하세요"),
  boardCatCd: z.string().optional(),
  openYn: z.enum(["Y", "N"]).default("Y"),
  mainYn: z.enum(["Y", "N"]).default("N"),
});

export type BoardForm = z.infer<typeof boardSchema>;

/** 협력사 게시판 폼 스키마 */
export const coopBoardSchema = z.object({
  SUBJECT: z.string().min(1, "제목을 입력하세요"),
  CONTENT: z.string().min(1, "내용을 입력하세요"),
  NOTICE_YN: z.enum(["Y", "N"]).default("N"),
  COOP_AREA: z.string().optional(),
  COOP_HSPT: z.string().optional(),
});

export type CoopBoardForm = z.infer<typeof coopBoardSchema>;

/** D-Day 폼 스키마 */
export const ddaySchema = z.object({
  ddayTitle: z.string().min(1, "제목을 입력하세요"),
  ddayDate: z.string().min(1, "날짜를 선택하세요"),
  ddayDesc: z.string().optional(),
  openYn: z.enum(["Y", "N"]).default("Y"),
});

export type DdayForm = z.infer<typeof ddaySchema>;
