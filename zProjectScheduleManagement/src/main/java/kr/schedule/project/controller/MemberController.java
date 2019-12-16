package kr.schedule.project.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.schedule.project.service.CalendarService;
import kr.schedule.project.service.MemberService;
import kr.schedule.project.vo.CalendarVO;
import kr.schedule.project.vo.MemberVO;

@Controller
public class MemberController {
	@Autowired
	MemberService memberService;
	@Autowired
	CalendarService calendarService;

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@RequestMapping(value = "/m")
	public String login(HttpServletRequest request, Model model) {// 쿠키를 읽자
		Cookie[] cookies = request.getCookies();

		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("m_id")) {
					model.addAttribute("m_id", cookie.getValue());
					break;
				}
			}
		}
		return "member/login";
	}

	@RequestMapping(value = "/m/loginOk", method = RequestMethod.GET)
	public String loginOkGet() {
		return "redirect:/member/login";
	}

	@RequestMapping(value = "/m/loginOk", method = RequestMethod.POST)
	public String loginOkPost(@ModelAttribute MemberVO memberVO, @RequestParam(required = false) String remember,
			HttpServletRequest request, HttpServletResponse response) {
		// 서비스를 호출해서 로그인 확인
		MemberVO vo = memberService.loginOk(memberVO);
		if (vo == null)
			return "redirect:/m";
		else {
			request.getSession().setAttribute("vo", vo); // 세션에 저장
			if (remember != null && remember.equals("save")) {
				Cookie cookie = new Cookie("m_id", vo.getM_id());
				cookie.setMaxAge(60 * 60 * 24 * 7);
				response.addCookie(cookie);
			} else {
				// 쿠키 삭제
				Cookie cookie = new Cookie("m_id", "");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/m/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("vo");
		return "redirect:/";
	}


	@RequestMapping(value = "/m/join")
	public String join() {
		return "member/join";
	}

	@RequestMapping(value = "/m/joinOk", method = RequestMethod.GET)
	public String joinOkGet() {
		return "redirect:/m";
	}

	@RequestMapping(value = "/m/joinOk", method = RequestMethod.POST)
	public String joinOkPost(@ModelAttribute MemberVO vo) {
		memberService.insert(vo);
		return "redirect:/m";
	}

	// 이메일 중복 체크
	@RequestMapping(value = "/m/checkSignupEmail", method = RequestMethod.POST)
	public @ResponseBody int AjaxView(@RequestParam("m_email") String m_email) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("m_email", m_email);
		int check = (memberService.selectByEmail(map) == null ? 0 : 1);
		return check;
	}
	

	// 이메일 중복 체크
	@RequestMapping(value = "/m/checkSignupID", method = RequestMethod.POST)
	public @ResponseBody int AjaxID(@RequestParam("m_id") String m_id) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("m_id", m_id);
		int idcheck = (memberService.selectByUserid(map) == null ? 0 : 1);
		return idcheck;
	}
	

	@RequestMapping(value="/m/idSearch")	
	public String idSearch() {
		return "member/idSearch";
	}
	@RequestMapping(value="/m/idSearchOk" , method=RequestMethod.GET)	
	public String idSearchOkGet() {
		return "redirect:/m";
	}	
	@RequestMapping(value="/m/idSearchOk" , method=RequestMethod.POST)	
	public String idSearchOkPost(@ModelAttribute MemberVO memberVO ,Model model) {
		MemberVO vo = memberService.idSearch(memberVO);
		
		if(vo==null)		
			return "redirect:/m";
		else{
			model.addAttribute("vo", vo);
			return "member/viewUserId";
		}
	}	
	@RequestMapping(value="/m/pwSearch")	
	public String pwSearch() {
		return "member/passwordSearch";
	}
	@RequestMapping(value="/m/pwSearchOk" , method=RequestMethod.GET)	
	public String pwSearchOkGet() {
		return "redirect:/m/login";
	}	

	@RequestMapping(value="/m/pwSearchOk" , method=RequestMethod.POST)	
	public String pwSearchOkPost(@ModelAttribute MemberVO membervo ,Model model) {
		MemberVO vo = memberService.pwSearch(membervo);
		logger.info("pwSearchOkPost "+vo);
		//MemberVO vo = membervo;
		if(vo==null)		
			return "redirect:/m/login";
		else{
			model.addAttribute("vo", vo);
			return "member/viewUserPw";
		}
	}	

	@RequestMapping(value = "/cal")
	public String calendar(Model model) {
		return "schedule/calendar_2";
	}

	// 일단 Json파일을 읽는것부터 해보자
	@RequestMapping(value = "/readJson")
	public String readJson(HttpServletRequest request, Model model) {
		String filepath = request.getRealPath("resources/json/data.json");
		List<CalendarVO> list = calendarService.readJson(filepath);
		model.addAttribute("list", list);
		return "view";
	}

	// 데이터를 json파일로 저장하는 방법
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/saveJson")
	public String saveJson(@ModelAttribute CalendarVO vo, HttpServletRequest request, Model model) {
		calendarService.saveJSON(request.getRealPath("resources/json/data.json"), "1234");
		return "schedule/calendar_2";
	}

	// responsebody로 디비에 있는 데이터 json파일로 파싱할 수 있도록 함
	@RequestMapping(value = "/saveJson2")
	@ResponseBody
	public List<CalendarVO> responseBodyTest(HttpServletRequest request) {
		// List<CalendarVO> result = calendarService.selectByUserid(vo.getUsername());
		MemberVO memberVO = (MemberVO)request.getSession().getAttribute("vo");
		logger.info("memberVO saveJson2 log : " + memberVO);
		List<CalendarVO> result = calendarService.selectByUserid(memberVO.getM_id());
		logger.info("memberVO saveJson2 result : " + result);
		return result;
	}

	@RequestMapping(value = "/cal/insert", method = RequestMethod.POST)
	public @ResponseBody boolean insert(@ModelAttribute CalendarVO vo, HttpServletRequest request) {
		logger.info("Calendar insert : "+vo);
		MemberVO memberVO = (MemberVO) request.getSession().getAttribute("vo");
		vo.setUsername(memberVO.getM_id());
		logger.info("CalendarVO : " + vo);
		calendarService.insert(vo);
		return true;
	}

	@RequestMapping(value = "/cal/update", method = RequestMethod.POST)
	public @ResponseBody void update(@ModelAttribute CalendarVO vo,@RequestParam(required=false) int _id, HttpServletRequest request) {
		logger.info("Calendar update : id : "+vo.get_id()+","+_id+", "+vo);
		MemberVO memberVO = (MemberVO) request.getSession().getAttribute("vo");
		vo.setUsername(memberVO.getM_id());
		vo.set_id(_id);
		logger.info("CalendarVO : " + vo); 
		calendarService.update(vo);
	}

	@RequestMapping(value = "/cal/delete", method = RequestMethod.POST)
	public @ResponseBody void delete(@RequestParam(required=false) int _id) {
		logger.info("Calendar delete : "+_id);
		calendarService.delete(_id);
	}
}