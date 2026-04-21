# Common (공통) 패키지

애플리케이션 전체에서 사용되는 공통 컴포넌트 및 유틸리티를 제공합니다.

## 패키지 구조

```
common/
├── ApiInfoApi.java              # API 정보 조회 REST 컨트롤러
├── ApiLoggingInterceptor.java   # API 요청/응답 로깅 인터셉터
├── CORSFilter.java              # CORS 필터 베이스 클래스
├── ComDefaultCodeVO.java        # 기본 코드 Value Object
├── ComDefaultVO.java            # 기본 Value Object
├── CommonUtil.java              # 공통 유틸리티
├── CommonVO.java                # 공통 Value Object 베이스 클래스
├── Configurations.java          # 애플리케이션 설정
├── DBUtil.java                  # 데이터베이스 유틸리티
├── EgovWebUtil.java             # 전자정부 웹 유틸리티
├── FileMngUtil.java             # 파일 관리 유틸리티
├── FileVO.java                  # 파일 Value Object
├── JwtUtil.java                 # JWT 토큰 유틸리티
├── MirProperties.java           # 프로퍼티 관리
├── PaginationInfo.java          # 페이징 정보
├── file/
│   ├── FileUtil.java                        # 파일 처리 유틸리티
│   ├── service/
│   │   ├── MultipartFileService.java        # 멀티파트 파일 서비스 인터페이스
│   │   └── impl/
│   │       ├── MultipartFileDAO.java        # 멀티파트 파일 DAO
│   │       └── MultipartFileServiceImpl.java # 멀티파트 파일 서비스 구현체
│   ├── view/
│   │   └── FileDownloadView.java            # 파일 다운로드 뷰
│   └── web/
│       └── FileDownloadController.java      # 파일 다운로드 컨트롤러
└── service/
    ├── CmmUseService.java               # 공통 사용 서비스 (@Service)
    └── CmmnDetailCode.java              # 공통 상세 코드 VO
```

## 주요 기능

### ApiInfoApi
- **GET** `/api/info/getApiList` - 모든 API 엔드포인트 목록 조회
- **GET** `/api/info/getApiSummary` - 모듈별 API 요약 정보 조회
- 런타임에 등록된 모든 REST API 목록 제공
- 모듈별 API 개수 통계 제공

### ApiLoggingInterceptor
- 모든 API 요청에 대한 로깅 처리
- 요청 URL, HTTP 메서드, 파라미터, 헤더 정보 기록
- 민감한 정보(비밀번호 등) 마스킹 처리
- 일별 로그 파일 생성 (`api_log_YYYY-MM-DD.log`)
- 클라이언트 IP 주소 추출 (프록시 헤더 지원)
- OPTIONS 요청 제외

### CORSFilter
- CORS(Cross-Origin Resource Sharing) 설정
- 모든 API 컨트롤러의 베이스 클래스
- 크로스 도메인 요청 처리

### CommonVO
- 모든 VO(Value Object)의 베이스 클래스
- 페이징 관련 공통 필드 제공
  - currentPage, pageRow, startNo, endNo
  - searchType, searchText, searchKind
- 등록/수정 정보 공통 필드
  - regId, regDt, updId, updDt

### CommonUtil
- Null 체크 및 기본값 처리
- 문자열 처리 유틸리티
- 데이터 변환 유틸리티

### FileUtil & FileMngUtil
- 파일 업로드 처리
- 파일 다운로드 처리
- 파일 삭제 처리
- 파일명 생성 및 검증

### FileDownloadController (파일 다운로드 API)
- **GET** `/download.do` - 파일 다운로드
  - `path` (필수): 파일 상대 경로
  - `filename` (선택): 다운로드 시 표시될 파일명
- **GET** `/imgFileView.do` - 이미지 파일 뷰어
  - `path` (필수): 이미지 파일 경로
- ResponseEntity 기반 현대적인 파일 다운로드 구현
- 브라우저별 파일명 인코딩 자동 처리
- 이미지 타입 자동 감지 (PNG, JPG, GIF, BMP, WebP, SVG)

### Configurations
- CORS 설정
- 애플리케이션 전역 설정
- Bean 등록 및 관리

## 사용 예시

```java
// CORSFilter 상속
@RestController
@RequestMapping("/api/book")
public class BookApi extends CORSFilter {
    // API 메소드 구현
}

// CommonVO 상속
public class BookVO extends CommonVO implements Serializable {
    private String rscId;
    private String rscNm;
    // 추가 필드
}

// CommonUtil 사용
String value = CommonUtil.isNull(request.getParameter("param"), "defaultValue");

// FileUtil 사용
fileUtil.uploadFile(multipartFile, uploadPath);
```

## 참고사항

- 모든 VO는 CommonVO를 상속해야 합니다
- 모든 API 컨트롤러는 CORSFilter를 상속해야 합니다
- 파일 업로드 경로는 application.properties에서 설정합니다
- CORS 설정은 Configurations에서 관리됩니다

---

## React 프론트엔드 연동 가이드

### 파일 다운로드

```javascript
/**
 * 파일 다운로드 함수
 * @param {string} filePath - 서버 내 파일 상대 경로
 * @param {string} fileName - 다운로드 시 표시될 파일명 (선택)
 */
const downloadFile = async (filePath, fileName) => {
  try {
    const params = new URLSearchParams({ path: filePath });
    if (fileName) {
      params.append('filename', fileName);
    }

    const response = await fetch(`/download.do?${params.toString()}`);

    if (!response.ok) {
      if (response.status === 404) {
        throw new Error('파일을 찾을 수 없습니다.');
      }
      throw new Error('파일 다운로드에 실패했습니다.');
    }

    const blob = await response.blob();
    const url = window.URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = url;
    link.download = fileName || filePath.split('/').pop();
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
    window.URL.revokeObjectURL(url);
  } catch (error) {
    console.error('Download error:', error);
    alert(error.message);
  }
};

// 사용 예시
downloadFile('/uploads/documents/report.pdf', '월간보고서.pdf');
```

### 이미지 표시

```jsx
// 방법 1: img 태그 직접 사용
<img
  src={`/imgFileView.do?path=${encodeURIComponent('/images/photo.png')}`}
  alt="이미지"
/>

// 방법 2: React 컴포넌트로 래핑
const ImageViewer = ({ path, alt, ...props }) => {
  const [error, setError] = useState(false);

  if (error) {
    return <div className="image-error">이미지를 불러올 수 없습니다.</div>;
  }

  return (
    <img
      src={`/imgFileView.do?path=${encodeURIComponent(path)}`}
      alt={alt}
      onError={() => setError(true)}
      {...props}
    />
  );
};

// 사용 예시
<ImageViewer path="/uploads/images/product.jpg" alt="상품 이미지" />
```

### 파일 업로드 (FormData 사용)

```javascript
/**
 * 파일 업로드 함수
 * @param {File} file - 업로드할 파일
 * @param {string} uploadUrl - 업로드 API 엔드포인트
 * @returns {Promise<Object>} 업로드 결과
 */
const uploadFile = async (file, uploadUrl) => {
  const formData = new FormData();
  formData.append('file', file);

  try {
    const response = await fetch(uploadUrl, {
      method: 'POST',
      body: formData,
      // Content-Type은 자동 설정됨 (multipart/form-data)
    });

    if (!response.ok) {
      throw new Error('파일 업로드에 실패했습니다.');
    }

    return await response.json();
  } catch (error) {
    console.error('Upload error:', error);
    throw error;
  }
};

// React 컴포넌트 예시
const FileUploader = ({ onUploadComplete }) => {
  const handleFileChange = async (event) => {
    const file = event.target.files[0];
    if (!file) return;

    try {
      const result = await uploadFile(file, '/api/file/upload');
      onUploadComplete(result);
    } catch (error) {
      alert(error.message);
    }
  };

  return (
    <input type="file" onChange={handleFileChange} />
  );
};
```

### Axios 사용 시

```javascript
import axios from 'axios';

// 파일 다운로드
const downloadFileWithAxios = async (filePath, fileName) => {
  try {
    const response = await axios.get('/download.do', {
      params: { path: filePath, filename: fileName },
      responseType: 'blob',
    });

    const url = window.URL.createObjectURL(new Blob([response.data]));
    const link = document.createElement('a');
    link.href = url;
    link.setAttribute('download', fileName || filePath.split('/').pop());
    document.body.appendChild(link);
    link.click();
    link.remove();
    window.URL.revokeObjectURL(url);
  } catch (error) {
    console.error('Download error:', error);
  }
};

// 파일 업로드
const uploadFileWithAxios = async (file, uploadUrl) => {
  const formData = new FormData();
  formData.append('file', file);

  const response = await axios.post(uploadUrl, formData, {
    headers: {
      'Content-Type': 'multipart/form-data',
    },
    onUploadProgress: (progressEvent) => {
      const percentCompleted = Math.round(
        (progressEvent.loaded * 100) / progressEvent.total
      );
      console.log(`업로드 진행률: ${percentCompleted}%`);
    },
  });

  return response.data;
};
```

### API 응답 코드

| HTTP 상태 코드 | 설명 |
|---------------|------|
| 200 OK | 요청 성공 |
| 404 Not Found | 파일을 찾을 수 없음 |
| 500 Internal Server Error | 서버 오류 |

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**
