package com.green;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class QuizController {
    
	// 1.입력 페이지;
	@GetMapping("/quiz")
	public String quizPage() {
		return "quiz-view";
	}
	
	// 2. 정답 확인 페이지
	@PostMapping("/check-quiz")
	public String checkPage(@RequestParam("pass") String pass,
			RedirectAttributes re
			) {
		
		if(pass.equals("1234")) {
			//정답이면
			//main Url 주소로 이동
		   return "redirect:/main";
		}else {
			// 오답이면 다시 퀴즈페이지로 이동
			re.addFlashAttribute("msg","비밀번호 틀림, 다시 시도해");
			// quiz URL주소로 이동
			return "redirect:/quiz";
		}
		
	}
	
	// 3. 메인 페이지 (정답일떄)
	@GetMapping("/main")
	public String mainPage() {
		// main-view.html 화면출력
		return "main-view";
	}
	
	
}
