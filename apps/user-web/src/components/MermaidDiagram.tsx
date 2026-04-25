import { useEffect, useId, useRef } from 'react';
import mermaid from 'mermaid';

mermaid.initialize({
  startOnLoad: false,
  theme: 'default',
  fontFamily: 'inherit',
  flowchart: { htmlLabels: true, curve: 'basis' },
});

interface Props {
  chart: string;
}

/**
 * Mermaid 다이어그램 래퍼. SSR 회피 위해 useEffect 에서 렌더.
 * id 충돌 방지로 React useId 사용.
 */
export function MermaidDiagram({ chart }: Props) {
  const baseId = useId().replace(/[:]/g, '');
  const containerRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    let cancelled = false;
    (async () => {
      try {
        const { svg } = await mermaid.render(`m-${baseId}`, chart);
        if (!cancelled && containerRef.current) {
          containerRef.current.innerHTML = svg;
        }
      } catch (e) {
        if (!cancelled && containerRef.current) {
          containerRef.current.innerHTML =
            `<pre style="color:#dc2626;font-size:12px">Mermaid render 실패: ${(e as Error).message}</pre>`;
        }
      }
    })();
    return () => { cancelled = true; };
  }, [baseId, chart]);

  return <div ref={containerRef} style={{ width: '100%', overflowX: 'auto' }} />;
}
