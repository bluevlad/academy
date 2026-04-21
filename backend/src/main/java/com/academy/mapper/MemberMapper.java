package com.academy.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.member.service.MemberVO;

/**
 * 사용자에 관한 데이터 접근 클래스를 정의한다.
 * @author rainend
 * @since 2025.11
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      		수정자           수정내용
 *  ----------------    --------    ---------------------------
 *   2025.11  		rainend          최초 생성
 * </pre>
 */
@Mapper
public interface MemberMapper {

	public ArrayList<JSONObject> selectMemberList(MemberVO memberVO);
	public int selectMemberListTotCnt(MemberVO memberVO);

	public JSONObject selectMemberDetail(MemberVO memberVO);

	public void insertMember(MemberVO memberVO);
    public void updateMember(MemberVO memberVO);
    public void deleteMember(MemberVO memberVO);

}
