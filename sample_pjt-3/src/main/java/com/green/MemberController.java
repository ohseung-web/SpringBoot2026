package com.green;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class MemberController {
	
	// DI(의존성 객체 주입)
	// MemberController가 직접 MemberService를 생성하지 않고
	// 스프링 컨테이너가 만든 MemberService를 주입시켜라
	@Autowired
	MemberService memberService;
	
	// 아래 작성한 메소드들은 핸들러 메소들이다.
	//회원 가입양식
	//http://localhost:8090/member/signup의 경로를 매핑(=연결)한다.
	@GetMapping("/member/signup") 
	public String signUpForm() {
		// 아래 프린트문은 log역활
		System.out.println("signUpForm()");
		return "signUpForm"; //응답에 사용하는 html파일 이름 반환
	}
	
	//로그인 양식
	@GetMapping("/member/signin")
	public String signInForm() {
		System.out.println("signinForm");
  // src/main/resources/templates/siginForm.hml
  // 매핑한다. => 매핑 URL주소는 @GetMapping("/member/signin")이다.
		return "signinForm";
	}
	
 //------- 수정 코드 부분 -----------------------

	// MemberController.java 수정
	@PostMapping("/member/signUp_confirm")
	public String signupconfirm(MemberDTO mdto) {
	    System.out.println("회원 가입 처리 중...");
	    memberService.signUpConfirm(mdto);
	    
	    // 중요: 결과 페이지로 리턴하지 않고, 가입 폼 주소로 '재요청(redirect)' 시킵니다.
	    // 이렇게 하면 가입 버튼을 눌러도 화면은 가입 폼에 머물게 됩니다.
	    return "redirect:/member/signup";
	}
	
	// 전체 회원 목록을 보여주는 새로운 핸들러 메소드
	@GetMapping("/member/list")
	public String memberList(Model model) {
	    List<MemberDTO> list = memberService.findAllMembers();
	    model.addAttribute("memberList", list); // "memberList"라는 이름으로 데이터 전달
	    return "memberList"; // memberList.html 파일을 응답
	}
	//-------------- 수정 코드 완료 ----------------------
	
	/*
	//회원가입한 데이터가 signUpResult페이지로 전달되는 메소드
	// 숨겨서 가는 @PostMapping()사용한다.
	// 가입한 자료를 매개변수로 넘겨줘야 하므로 @RequestParam 사용한다.
	// public String sign(String id, String pw, String email){}
	@PostMapping("/member/signUp_confirm")
	public String signupconfirm(MemberDTO mdto,Model model // model id, pw, email 담아서 데이터 전달
			) {
		System.out.println("signupconfirm()");
		
		//현재가입한 시간을 출력하는 로직을 작성
		// 날짜 시간 출력형식 : 2026-01-21 11:20:10
		// 날짜형식 클래스 : SimpleDateFormat()
		// 오늘 날짜 클래스 : Date
		
		// MemberService 비즈니즈 로직을 담당하는 클래스
		// new 키워드를 이용하여 객체생성
		//MemberService memberservice = new MemberService();
		memberService.signUpConfirm(mdto);
		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		
		// model로 담기
		model.addAttribute("now",sd.format(now) );
		model.addAttribute("mdto",mdto );
//		model.addAttribute("new_id", mdto.getId());
//		model.addAttribute("new_pw", mdto.getPw());
//		model.addAttribute("new_email", mdto.getEmail());
		
		return "signUpResult";
	}
	
//	@GetMapping("/member/signIn_confirm")
//	public String signinconfirm(
//			@RequestParam("id") String id,
//			@RequestParam("pw") String pw,
//			Model model // model id, pw 담아서 데이터 전달
//			) {
//		System.out.println("signIpconfirm()");
//		
//		//현재가입한 시간을 출력하는 로직을 작성
//		// 날짜 시간 출력형식 : 2026-01-21 11:20:10
//		// 날짜형식 클래스 : SimpleDateFormat()
//		// 오늘 날짜 클래스 : Date
//		
//		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date now = new Date();
//		
//		// model로 담기
//		model.addAttribute("now",sd.format(now) );
//		model.addAttribute("new_id", id);
//		model.addAttribute("new_pw", pw);
//		
//		return "signinResult";
//	}
	
	*/
	
	// ModelAndView 클래스는 model과 View를 하나로 합쳐서 클라이언트에 전달한다.
	// MemberDTO를 데이터 타입으로 매개변수 지정한다.
	@GetMapping("/member/signIn_confirm")
	public ModelAndView signinconfirm(MemberDTO mdto) {
		System.out.println("signIpconfirm()");
		
		//현재가입한 시간을 출력하는 로직을 작성
		// 날짜 시간 출력형식 : 2026-01-21 11:20:10
		// 날짜형식 클래스 : SimpleDateFormat()
		// 오늘 날짜 클래스 : Date
		
		//MemberService memberservice = new MemberService();
		memberService.signinConfirm(mdto);
		
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("id", mdto.getId());
		modelview.addObject("pw", mdto.getPw());
		// view페이지 signinResult.html은 어떻게 추가하나
		modelview.setViewName("signinResult");
		
		// model로 담기
//		model.addAttribute("now",sd.format(now) );
//		model.addAttribute("new_id", id);
//		model.addAttribute("new_pw", pw);
		
		return modelview;
	}
	
	
		
	
}
