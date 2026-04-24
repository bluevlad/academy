#!/usr/bin/env bash
# docs/roles/*.md → PDF 변환 (pandoc + xelatex 사용 — 한글 지원)
#
# 사전 요구: brew install pandoc && brew install --cask mactex-no-gui
# (또는 bin: pandoc + xelatex 가 PATH 에 있어야 함)
#
# 사용:
#   ./scripts/docs-to-pdf.sh              # 전체 (README, matrix, 4개 역할)
#   ./scripts/docs-to-pdf.sh student      # 학생만
#   ./scripts/docs-to-pdf.sh matrix       # 매트릭스만

set -euo pipefail

ROOT="$(cd "$(dirname "$0")/.." && pwd)"
SRC="$ROOT/docs/roles"
OUT="$ROOT/docs/dist"
mkdir -p "$OUT"

# 한글 폰트 — macOS 기본 Apple SD Gothic Neo 사용. 환경에 맞게 변경 가능.
MAIN_FONT="${PANDOC_MAIN_FONT:-Apple SD Gothic Neo}"
MONO_FONT="${PANDOC_MONO_FONT:-Menlo}"

convert() {
  local name="$1"
  local src="$SRC/$name.md"
  local out="$OUT/$name.pdf"
  if [[ ! -f "$src" ]]; then
    echo "skip: $src (not found)"
    return
  fi
  echo "→ $name.md → $name.pdf"
  pandoc "$src" \
    -o "$out" \
    --pdf-engine=xelatex \
    -V mainfont="$MAIN_FONT" \
    -V monofont="$MONO_FONT" \
    -V geometry:margin=20mm \
    -V documentclass=article \
    -V colorlinks=true \
    --toc \
    --toc-depth=3
}

target="${1:-all}"
case "$target" in
  all)
    convert README
    convert menu-matrix
    convert 01-student
    convert 02-instructor
    convert 03-operator
    convert 04-sysadmin
    ;;
  student)     convert 01-student ;;
  instructor)  convert 02-instructor ;;
  operator)    convert 03-operator ;;
  sysadmin)    convert 04-sysadmin ;;
  matrix)      convert menu-matrix ;;
  readme)      convert README ;;
  *)
    echo "usage: $0 [all|student|instructor|operator|sysadmin|matrix|readme]"
    exit 1
    ;;
esac

echo ""
echo "✓ PDF 출력 위치: $OUT"
ls -lh "$OUT"
