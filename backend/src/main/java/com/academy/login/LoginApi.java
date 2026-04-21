package com.academy.login;

import java.util.HashMap;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.academy.common.CORSFilter;
import com.academy.common.JwtUtil;
import com.academy.login.service.LoginService;
import com.academy.login.service.MemberVO;

@RestController
@RequestMapping("/api/auth")
public class LoginApi extends CORSFilter {

	private LoginService loginService;
    private JwtUtil jwtUtil;
    public LoginApi(LoginService loginService, JwtUtil jwtUtil) {
        this.loginService = loginService;
        this.jwtUtil = jwtUtil;
    }

	/**
	 * 일반(세션) 로그인을 처리한다
	 * @param MemberVO - 아이디, 비밀번호가 담긴 LoginVO
	 * @return ResponseEntity - 로그인결과(세션정보)
	 * @exception Exception
	 */
	@PostMapping(value = "/sign-in")
	public JSONObject actionLogin(@ModelAttribute("MemberVO") MemberVO memberVO) throws Exception {

		HashMap<String,Object> jsonObject = new HashMap<String,Object>();

		try {
			// userId 값 검증 (null 방지)
            String userId = Optional.ofNullable(memberVO.getUserId())
                                    .map(Object::toString)
                                    .orElseThrow(() -> new IllegalArgumentException("User ID not found"));

			// 사용자 정보 조회
            JSONObject userInfo = loginService.getUser(memberVO);

            if (userInfo == null || userInfo.isEmpty()) {
                jsonObject.put("retMsg", "FAIL");
                jsonObject.put("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
                return new JSONObject(jsonObject);
            }

            // userRole 추출 (DB에서 조회된 값)
            String userRole = (String) userInfo.get("user_role");
            if (userRole == null) {
                userRole = (String) userInfo.get("userRole");
            }

            // JWT 토큰 생성 (userRole 포함)
            String token = jwtUtil.generateToken(userId, userRole);
            memberVO.setToken(token);
			loginService.updateToken(memberVO);

            jsonObject.put("userInfo", userInfo);
    		jsonObject.put("token", token);
    		jsonObject.put("userRole", userRole);
    		jsonObject.put("retMsg", "OK");

		} catch (Exception e){
			e.printStackTrace();
			jsonObject.put("retMsg", "FAIL");
			jsonObject.put("message", "로그인 처리 중 오류가 발생했습니다.");
		}

		JSONObject jObject = new JSONObject(jsonObject);
        return jObject;
	}

	/**
	 * 사용자 정보를 가져온다
	 * @param vo - LoginVO
	 * @return ResponseEntity - 로그인결과(세션정보)
	 * @exception Exception
	 */
	@PostMapping(value = "/profile")
	public JSONObject getUser(@ModelAttribute("MemberVO") MemberVO memberVO) {

		HashMap<String,Object> jsonObject = new HashMap<String,Object>();

		try {
            jsonObject.put("userInfo", loginService.getUser(memberVO));
            
		} catch (Exception e){
			e.printStackTrace();
			jsonObject.put("retMsg", "FAIL");
		}

		JSONObject jObject = new JSONObject(jsonObject);
        return jObject;
	}

	/**
	 * 회원가입
	 * @throws Exception
	 */
	@PostMapping(value = "/sign-up")
	public JSONObject insert(@ModelAttribute("MemberVO") MemberVO memberVO) throws Exception { 

		HashMap<String,Object> jsonObject = new HashMap<String,Object>();

		try {
			loginService.insertUser(memberVO);
			jsonObject.put("retMsg", "OK");
		} catch (Exception e){
			jsonObject.put("retMsg", "FAIL");
			e.printStackTrace();
		}
		
		JSONObject jObject = new JSONObject(jsonObject);

		return jObject;
	}

	/**
	 * 회원정보 수정
	 * @throws Exception
	 */
	@PostMapping(value="/updateUser")
	public JSONObject update(@ModelAttribute("MemberVO") MemberVO memberVO) throws Exception {
		
		HashMap<String,Object> jsonObject = new HashMap<String,Object>();
		
		try {
			loginService.updateUser(memberVO);
			jsonObject.put("retMsg", "OK");
		} catch (Exception e){
			jsonObject.put("retMsg", "FAIL");
			e.printStackTrace();
		}
	
		JSONObject jObject = new JSONObject(jsonObject);

		return jObject;
	}

	/**
	 * 로그아웃한다.
	 * @return ResponseEntity
	 * @exception Exception
	 */
	@PostMapping(value = "/logOut")
	public ResponseEntity<?> actionLogout(@ModelAttribute("MemberVO") MemberVO memberVO) throws Exception {

        // JSON 응답 반환
        HashMap<String, Object> responseMap = new HashMap<>();
        responseMap.put("message", "Logout successful");

        return ResponseEntity.ok(responseMap);
	}

}
