package com.academy.member.service;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.academy.mapper.MemberMapper;

@Service
public class MemberService {

	private MemberMapper memberMapper;
	
	public MemberService(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}
	
	public ArrayList<JSONObject> selectMemberList(MemberVO memberVO) {
		return memberMapper.selectMemberList(memberVO);
	}
    public int selectMemberListTotCnt(MemberVO memberVO) {
        return memberMapper.selectMemberListTotCnt(memberVO);
    }
	
	public JSONObject selectMemberDetail(MemberVO memberVO) {
		return memberMapper.selectMemberDetail(memberVO);
	}
	
    public void insertMember(MemberVO memberVO) {
    	memberMapper.insertMember(memberVO);
    }
    public void updateMember(MemberVO memberVO) {
    	memberMapper.updateMember(memberVO);
    }
    public void deleteMember(MemberVO memberVO) {
    	memberMapper.updateMember(memberVO);
    }

}