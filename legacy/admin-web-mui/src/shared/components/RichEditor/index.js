/**
 * TipTap 기반 리치 텍스트 에디터
 *
 * 게시판, 공지, 이벤트 등에서 사용하는 WYSIWYG 에디터
 *
 * Usage:
 *   <RichEditor
 *     content={htmlContent}
 *     onChange={(html) => setContent(html)}
 *     placeholder="내용을 입력하세요"
 *   />
 *
 * React Hook Form 연동:
 *   <Controller
 *     name="content"
 *     control={control}
 *     render={({ field }) => (
 *       <RichEditor content={field.value} onChange={field.onChange} />
 *     )}
 *   />
 */
import { useEditor, EditorContent } from "@tiptap/react";
import StarterKit from "@tiptap/starter-kit";
import Placeholder from "@tiptap/extension-placeholder";
import { useEffect } from "react";

// MUI
import Box from "@mui/material/Box";
import IconButton from "@mui/material/IconButton";
import Divider from "@mui/material/Divider";
import Tooltip from "@mui/material/Tooltip";
import Icon from "@mui/material/Icon";

function MenuBar({ editor }) {
  if (!editor) return null;

  const buttons = [
    {
      icon: "format_bold",
      title: "굵게",
      action: () => editor.chain().focus().toggleBold().run(),
      isActive: editor.isActive("bold"),
    },
    {
      icon: "format_italic",
      title: "기울임",
      action: () => editor.chain().focus().toggleItalic().run(),
      isActive: editor.isActive("italic"),
    },
    {
      icon: "strikethrough_s",
      title: "취소선",
      action: () => editor.chain().focus().toggleStrike().run(),
      isActive: editor.isActive("strike"),
    },
    { divider: true },
    {
      icon: "format_list_bulleted",
      title: "목록",
      action: () => editor.chain().focus().toggleBulletList().run(),
      isActive: editor.isActive("bulletList"),
    },
    {
      icon: "format_list_numbered",
      title: "번호 목록",
      action: () => editor.chain().focus().toggleOrderedList().run(),
      isActive: editor.isActive("orderedList"),
    },
    { divider: true },
    {
      icon: "format_quote",
      title: "인용",
      action: () => editor.chain().focus().toggleBlockquote().run(),
      isActive: editor.isActive("blockquote"),
    },
    {
      icon: "horizontal_rule",
      title: "구분선",
      action: () => editor.chain().focus().setHorizontalRule().run(),
      isActive: false,
    },
    { divider: true },
    {
      icon: "undo",
      title: "되돌리기",
      action: () => editor.chain().focus().undo().run(),
      isActive: false,
    },
    {
      icon: "redo",
      title: "다시 실행",
      action: () => editor.chain().focus().redo().run(),
      isActive: false,
    },
  ];

  return (
    <Box
      display="flex"
      alignItems="center"
      gap={0.5}
      px={1}
      py={0.5}
      sx={{ borderBottom: "1px solid", borderColor: "divider" }}
    >
      {buttons.map((btn, i) =>
        btn.divider ? (
          <Divider key={i} orientation="vertical" flexItem sx={{ mx: 0.5 }} />
        ) : (
          <Tooltip key={i} title={btn.title}>
            <IconButton
              size="small"
              onClick={btn.action}
              sx={{
                color: btn.isActive ? "primary.main" : "text.secondary",
                bgcolor: btn.isActive ? "action.selected" : "transparent",
              }}
            >
              <Icon sx={{ fontSize: "18px !important" }}>{btn.icon}</Icon>
            </IconButton>
          </Tooltip>
        )
      )}
    </Box>
  );
}

/**
 * @param {string} content - HTML 콘텐츠
 * @param {Function} onChange - (html: string) => void
 * @param {string} placeholder - 플레이스홀더 텍스트
 * @param {number} minHeight - 최소 높이 (px)
 * @param {boolean} editable - 편집 가능 여부
 */
export function RichEditor({
  content = "",
  onChange,
  placeholder = "내용을 입력하세요...",
  minHeight = 200,
  editable = true,
}) {
  const editor = useEditor({
    extensions: [
      StarterKit,
      Placeholder.configure({ placeholder }),
    ],
    content,
    editable,
    onUpdate: ({ editor: e }) => {
      onChange?.(e.getHTML());
    },
  });

  // 외부에서 content가 변경되었을 때 동기화
  useEffect(() => {
    if (editor && content !== editor.getHTML()) {
      editor.commands.setContent(content, false);
    }
  }, [content]);

  return (
    <Box
      sx={{
        border: "1px solid",
        borderColor: "divider",
        borderRadius: 1,
        overflow: "hidden",
        "& .tiptap": {
          minHeight,
          padding: "12px 16px",
          outline: "none",
          "& p.is-editor-empty:first-of-type::before": {
            color: "text.disabled",
            content: "attr(data-placeholder)",
            float: "left",
            height: 0,
            pointerEvents: "none",
          },
        },
      }}
    >
      {editable && <MenuBar editor={editor} />}
      <EditorContent editor={editor} />
    </Box>
  );
}

export default RichEditor;
