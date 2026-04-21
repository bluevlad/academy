package com.academy.common.file.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academy.common.CommonUtil;

/**
 * 파일 다운로드 컨트롤러
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
 *   2026.01  Claude          ResponseEntity 방식으로 변경
 *
 * </pre>
 */
@RestController
@RequestMapping
public class FileDownloadController {

	private static final Logger logger = LoggerFactory.getLogger(FileDownloadController.class);

	@Value("${file.upload.path:C:/upload/}")
	private String uploadPath;

	/**
	 * 파일 다운로드
	 * @param path 파일 경로 (상대 경로)
	 * @param filename 다운로드 시 표시될 파일명 (선택)
	 * @param userAgent 브라우저 User-Agent
	 * @return 파일 리소스
	 */
	@GetMapping("download.do")
	public ResponseEntity<Resource> download(
			@RequestParam(value = "path", defaultValue = "") String path,
			@RequestParam(value = "filename", required = false) String filename,
			@RequestHeader(value = "User-Agent", defaultValue = "") String userAgent) {

		try {
			String filePath = CommonUtil.isNull(path, "");
			File file = new File(uploadPath + filePath);

			if (!file.exists() || !file.isFile()) {
				logger.warn("File not found: {}", file.getAbsolutePath());
				return ResponseEntity.notFound().build();
			}

			Resource resource = new FileSystemResource(file);
			String downloadFilename = CommonUtil.isNull(filename, file.getName());
			String encodedFilename = encodeFilename(downloadFilename, userAgent);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentLength(file.length());
			headers.setContentDispositionFormData("attachment", encodedFilename);
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFilename + "\"");

			return ResponseEntity.ok()
					.headers(headers)
					.body(resource);

		} catch (Exception e) {
			logger.error("File download error", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * 이미지 파일 뷰어
	 * @param path 이미지 파일 경로
	 * @return 이미지 바이트 배열
	 */
	@GetMapping("imgFileView.do")
	public ResponseEntity<byte[]> imgFileView(@RequestParam("path") String path) {
		try {
			File file = new File(uploadPath + path);

			if (!file.exists() || !file.isFile()) {
				logger.warn("Image file not found: {}", file.getAbsolutePath());
				return ResponseEntity.notFound().build();
			}

			byte[] imageBytes = Files.readAllBytes(file.toPath());
			MediaType mediaType = determineMediaType(file.getName());

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(mediaType);
			headers.setContentLength(imageBytes.length);

			return ResponseEntity.ok()
					.headers(headers)
					.body(imageBytes);

		} catch (FileNotFoundException e) {
			logger.warn("Image file not found: {}", path);
			return ResponseEntity.notFound().build();
		} catch (IOException e) {
			logger.error("Image file read error", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * 파일명을 브라우저에 맞게 인코딩
	 * @param filename 원본 파일명
	 * @param userAgent User-Agent 헤더
	 * @return 인코딩된 파일명
	 */
	private String encodeFilename(String filename, String userAgent) {
		try {
			if (userAgent == null) {
				userAgent = "";
			}

			if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
				// IE 브라우저
				return URLEncoder.encode(filename, StandardCharsets.UTF_8.name())
						.replaceAll("\\+", "%20");
			} else if (userAgent.contains("Firefox")) {
				// Firefox
				return new String(filename.getBytes(StandardCharsets.UTF_8), "ISO-8859-1");
			} else {
				// Chrome, Edge, Safari 등 기타 브라우저
				return URLEncoder.encode(filename, StandardCharsets.UTF_8.name())
						.replaceAll("\\+", "%20");
			}
		} catch (Exception e) {
			logger.warn("Filename encoding failed: {}", filename);
			return filename;
		}
	}

	/**
	 * 파일 확장자에 따른 MediaType 결정
	 * @param filename 파일명
	 * @return MediaType
	 */
	private MediaType determineMediaType(String filename) {
		String extension = "";
		int lastDot = filename.lastIndexOf('.');
		if (lastDot > 0) {
			extension = filename.substring(lastDot + 1).toLowerCase();
		}

		switch (extension) {
			case "png":
				return MediaType.IMAGE_PNG;
			case "jpg":
			case "jpeg":
				return MediaType.IMAGE_JPEG;
			case "gif":
				return MediaType.IMAGE_GIF;
			case "bmp":
				return MediaType.parseMediaType("image/bmp");
			case "webp":
				return MediaType.parseMediaType("image/webp");
			case "svg":
				return MediaType.parseMediaType("image/svg+xml");
			default:
				return MediaType.APPLICATION_OCTET_STREAM;
		}
	}
}