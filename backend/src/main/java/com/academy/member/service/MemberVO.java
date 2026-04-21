package com.academy.member.service;


import com.academy.common.CommonVO;

/**
*
* 사용자 VO 클래스
* @author rainend
* @version 1.0
* @see
** <pre>
* << 개정이력(Modification Information) >>
*
*  	  수정일           수정자                수정내용
*  ---------------    --------------    ---------------------------
*  2025.11   			rainend			 사용자 정보 등록
* </pre>
*/
public class MemberVO extends CommonVO {

	private static final long serialVersionUID = 638950577710720796L;

	/** 회원 ID */
	private String userId;
	/** 성명 */
	private String userNm;
	/** 비밀번호 */
	private String userPwd;
	/** 성별 */
	private String sex;
	/** 사용자 권한(사용자, 관리자, 강사) */
	private String userRole;
	/** 관리자 권한(관리자에 사용) */
	private String adminRole;
	/** 생일(생년월일) */
	private String birthDay;
	/** 이메일 */
	private String email;
	/** 우편번호 */
	private String zipCode;
	/** 자택주소 */
	private String address1;
	/** 자택주소 상세 */
	private String address2;
	/** 포인트 */
	private String userPoint;
	/** 메모(관리자에 사용) */
	private String memo;
	/** 사진(소)(강사에 사용) */
	private String pic;
	// isUse, regDt, regId, updDt, updId inherited from CommonVO
	/** 문자수신여부 */
	private String isokSms;
	/** 이메일수신여부 */
	private String isokEmail;
	/** 로그인 token */
	private String token;

	/**
	 * userId attribute 를 리턴한다.
	 * @return String
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * userId attribute 값을 설정한다.
	 * @param userId String
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * userNm attribute 를 리턴한다.
	 * @return String
	 */
	public String getUserNm() {
		return userNm;
	}
	/**
	 * userNm attribute 값을 설정한다.
	 * @param userNm String
	 */
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	/**
	 * userPwd attribute 를 리턴한다.
	 * @return String
	 */
	public String getUserPwd() {
		return userPwd;
	}
	/**
	 * userPwd attribute 값을 설정한다.
	 * @param userPwd String
	 */
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	/**
	 * sex attribute 를 리턴한다.
	 * @return String
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * sex attribute 값을 설정한다.
	 * @param sex String
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * userRole attribute 를 리턴한다.
	 * @return String
	 */
	public String getUserRole() {
		return userRole;
	}
	/**
	 * userRole attribute 값을 설정한다.
	 * @param userRole String
	 */
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	/**
	 * adminRole attribute 를 리턴한다.
	 * @return String
	 */
	public String getAdminRole() {
		return adminRole;
	}
	/**
	 * adminRole attribute 값을 설정한다.
	 * @param adminRole String
	 */
	public void setAdminRole(String adminRole) {
		this.adminRole = adminRole;
	}

	/**
	 * birthDay attribute 를 리턴한다.
	 * @return String
	 */
	public String getBirthDay() {
		return birthDay;
	}
	/**
	 * birthDay attribute 값을 설정한다.
	 * @param birthDay String
	 */
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	/**
	 * email attribute 를 리턴한다.
	 * @return String
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * email attribute 값을 설정한다.
	 * @param email String
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * zipCode attribute 를 리턴한다.
	 * @return String
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * zipCode attribute 값을 설정한다.
	 * @param zipCode String
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * address1 attribute 를 리턴한다.
	 * @return String
	 */
	public String getAddress1() {
		return address1;
	}
	/**
	 * address1 attribute 값을 설정한다.
	 * @param address1 String
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * address2 attribute 를 리턴한다.
	 * @return String
	 */
	public String getAddress2() {
		return address2;
	}
	/**
	 * address2 attribute 값을 설정한다.
	 * @param address2 String
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * userPoint attribute 를 리턴한다.
	 * @return String
	 */
	public String getUserPoint() {
		return userPoint;
	}
	/**
	 * userPoint attribute 값을 설정한다.
	 * @param userPoint String
	 */
	public void setUserPoint(String userPoint) {
		this.userPoint = userPoint;
	}

	/**
	 * memo attribute 를 리턴한다.
	 * @return String
	 */
	public String getMemo() {
		return memo;
	}
	/**
	 * memo attribute 값을 설정한다.
	 * @param memo String
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * pic attribute 를 리턴한다.
	 * @return String
	 */
	public String getPic() {
		return pic;
	}
	/**
	 * pic attribute 값을 설정한다.
	 * @param pic String
	 */
	public void setPic(String pic) {
		this.pic = pic;
	}

	// isUse, regDt, regId, updDt, updId getter/setter inherited from CommonVO

	/**
	 * isokSms attribute 를 리턴한다.
	 * @return String
	 */
	public String getIsokSms() {
		return isokSms;
	}
	/**
	 * isokSms attribute 값을 설정한다.
	 * @param isokSms String
	 */
	public void setIsokSms(String isokSms) {
		this.isokSms = isokSms;
	}

	/**
	 * isokEmail attribute 를 리턴한다.
	 * @return String
	 */
	public String getIsokEmail() {
		return isokEmail;
	}
	/**
	 * isokEmail attribute 값을 설정한다.
	 * @param isokEmail String
	 */
	public void setIsokEmail(String isokEmail) {
		this.isokEmail = isokEmail;
	}

	/**
	 * token attribute 를 리턴한다.
	 * @return String
	 */
	public String getToken() {
		return token;
	}
	/**
	 * token attribute 값을 설정한다.
	 * @param token String
	 */
	public void setToken(String token) {
		this.token = token;
	}

}