package kr.schedule.project.controller;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.schedule.project.service.MemberService;
import kr.schedule.project.vo.MemberVO;

@Controller
public class MemberController {	
	@Autowired
	MemberService memberService;
	@RequestMapping(value="/m")
	public String login(HttpServletRequest request, Model model) {//쿠키를 읽자
		Cookie[] cookies = request.getCookies();
		
		if(cookies!=null&& cookies.length>0) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("m_id")) {
					model.addAttribute("m_id",cookie.getValue());
					break;
				}
			}
		}
		return "member/login";
	}
	@RequestMapping(value="/m/loginOk" , method=RequestMethod.GET)
	public String loginOkGet() {
		return "redirect:/member/login";
	}		
	@RequestMapping(value="/m/loginOk" , method=RequestMethod.POST)
	public String loginOkPost(@ModelAttribute MemberVO memberVO,@RequestParam(required=false) String remember, HttpServletRequest request, HttpServletResponse response) {
		//서비스를 호출해서 로그인 확인
		MemberVO vo = memberService.loginOk(memberVO);
		if(vo==null)		
			return "redirect:/m";
		else {
			request.getSession().setAttribute("vo", vo); // 세션에 저장
			if(remember!=null&& remember.equals("save")) {
				Cookie cookie = new Cookie("m_id",vo.getM_id());
				cookie.setMaxAge(60*60*24*7);
				response.addCookie(cookie);
			}else {
				//쿠키 삭제
				Cookie cookie = new Cookie("m_id","");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			return "redirect:/";
		}		
	}

	@RequestMapping(value="/m/join")
	public String join() {
		return "member/join";
	}	
	
	@RequestMapping(value="/m/joinOk" , method=RequestMethod.GET)
	public String joinOkGet() {
		return "redirect:/m";
	}		
	@RequestMapping(value="/m/joinOk" , method=RequestMethod.POST)
	public String joinOkPost(@ModelAttribute MemberVO vo) {
		memberService.insert(vo);
		return "redirect:/m";
	}	
	
	// 이메일 중복 체크
	@RequestMapping(value = "/m/checkSignupEmail", method = RequestMethod.POST)
	public @ResponseBody int AjaxView( @RequestParam("m_email") String m_email){
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("m_email", m_email);
		int idcheck = (memberService.selectByEmail(map)==null?0:1);
		return idcheck;
	}

	@RequestMapping(value="/cal" )
	public String calendar(Model model ) {
		return "schedule/calendar"; 
	}	

	@RequestMapping(value="/cal2" )
	public String calendar2(Model model ) {
		return "schedule/calendar_2"; 
	}	

	@RequestMapping(value="/cal3" )
	public String calendar3(Model model ) {
		return "schedule/calendar_3"; 
	}	
}
