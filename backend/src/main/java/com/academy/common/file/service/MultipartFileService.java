package com.academy.common.file.service;

import java.util.HashMap;

/**
 * 멀티파트 파일 서비스 인터페이스
 * @author Y.K.KIM
 * @since 2025.11
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2025.11  Y.K.KIM          최초 생성
 *
 * </pre>
 */
public interface MultipartFileService {

	/**
	 * 첨부파일 정보를 DB에 저장한다.
	 * @param params 파일 정보 맵
	 * @return 저장된 파일 ID
	 */
	Object insertAttachFile(HashMap<String, Object> params);

}

