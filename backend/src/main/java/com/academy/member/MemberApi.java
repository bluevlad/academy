package com.academy.member;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academy.common.CORSFilter;
import com.academy.common.CommonUtil;
import com.academy.common.PaginationInfo;

import com.academy.member.service.MemberService;
import com.academy.member.service.MemberVO;

@RestController
@RequestMapping("/api/member")
public class MemberApi extends CORSFilter {

    private MemberService memberService;

    public MemberApi(MemberService memberService) {
        this.memberService = memberService;
    }
    
	/**
	 * 사용자 목록화면 이동
	 * @param MemberVO, @RequestParam Map<?, ?>
	 * @return HashMap<String,Object>
	 * @exception Exception
	 */
	@GetMapping(value = "/getMemberList")
	public JSONObject getMemberList(@ModelAttribute("MemberVO") MemberVO memberVO, @RequestParam Map<?, ?> commandMap) throws Exception { 
		
		HashMap<String,Object> jsonObject = new HashMap<String,Object>();
		
		String curPage = "1";
		if(!CommonUtil.empty(commandMap.get("curPage"))){
			curPage = (String)commandMap.get("curPage");
		}
		memberVO.setPageIndex(CommonUtil.parseInt(curPage));
		
		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(memberVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(memberVO.getPageUnit());
		paginationInfo.setPageSize(memberVO.getPageSize());

		memberVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		memberVO.setLastIndex(paginationInfo.getLastRecordIndex());
		memberVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		jsonObject.put("memberList", memberService.selectMemberList(memberVO));

		int totCnt = memberService.selectMemberListTotCnt(memberVO);
		paginationInfo.setTotalRecordCount(totCnt);
		jsonObject.put("paginationInfo", paginationInfo);
		
		JSONObject jObject = new JSONObject(jsonObject);

		return jObject;
	}

	/**
	 * 사용자 상세정보.
	 * @param MemberVO
	 * @return HashMap<String,Object>
	 * @throws Exception
	 */
	@GetMapping(value = "/getMemberDetail")
	public JSONObject getMemberDetail(@ModelAttribute("MemberVO") MemberVO memberVO) throws Exception, IOException, ParseException { 

		HashMap<String,Object> jsonObject = new HashMap<String,Object>();
		
		jsonObject.put("memberDetail", memberService.selectMemberDetail(memberVO));

		JSONObject jObject = new JSONObject(jsonObject);
		
		return jObject;
	}

	/**
	 * 사용자 신규로 등록한다.
	 * @param MemberVO
	 * @return HashMap<String,Object>
	 * @throws Exception
	 */
	@GetMapping(value="/insertMember")
	public JSONObject insertMember(@ModelAttribute("MemberVO") MemberVO memberVO) throws Exception {
		
		HashMap<String,Object> jsonObject = new HashMap<String,Object>();
		
		try {
			memberService.insertMember(memberVO);
			jsonObject.put("retMsg", "OK");
		} catch (Exception e){
			jsonObject.put("retMsg", "FAIL");
			e.printStackTrace();
		}
		
		JSONObject jObject = new JSONObject(jsonObject);

		return jObject;
	}

	/**
	 * 사용자 정보를 변경한다.
	 * @param MemberVO
	 * @return HashMap<String,Object>
	 * @throws Exception
	 */
	@GetMapping(value="/updateMember")
	public JSONObject updateMember(@ModelAttribute("MemberVO") MemberVO memberVO) throws Exception {

		HashMap<String,Object> jsonObject = new HashMap<String,Object>();
		
		try {
			memberService.updateMember(memberVO);
			jsonObject.put("retMsg", "OK");
		} catch (Exception e){
			jsonObject.put("retMsg", "FAIL");
			e.printStackTrace();
		}
	
		JSONObject jObject = new JSONObject(jsonObject);

		return jObject;
	}

	/**
	 * 사용자 정보를 삭제한다.
	 * @param MemberVO
	 * @return HashMap<String,Object>
	 * @throws Exception
	 */
	@GetMapping(value = "/deleteMember")
	@Transactional(readOnly=false,  rollbackFor=Exception.class)
	public JSONObject deleteMember(@ModelAttribute("MemberVO") MemberVO memberVO) throws Exception {
		
		HashMap<String,Object> jsonObject = new HashMap<String,Object>();
		
		try {
			memberService.deleteMember(memberVO);
			jsonObject.put("retMsg", "OK");
		} catch (Exception e){
			jsonObject.put("retMsg", "FAIL");
			e.printStackTrace();
		}
		
		JSONObject jObject = new JSONObject(jsonObject);

		return jObject;
	}

}