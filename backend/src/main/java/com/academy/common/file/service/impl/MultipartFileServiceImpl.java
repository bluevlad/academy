package com.academy.common.file.service.impl;

import java.io.Serializable;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.common.file.service.MultipartFileService;

/**
 * 멀티파트 파일 서비스 구현체
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
@Service
public class MultipartFileServiceImpl implements MultipartFileService, Serializable {

	private static final long serialVersionUID = 1L;

	private final MultipartFileDAO multipartfiledao;

	@Autowired
	public MultipartFileServiceImpl(MultipartFileDAO multipartfiledao) {
		this.multipartfiledao = multipartfiledao;
	}

	@Override
	public Object insertAttachFile(HashMap<String, Object> params) {
		return multipartfiledao.insertAttachFile(params);
	}
}
