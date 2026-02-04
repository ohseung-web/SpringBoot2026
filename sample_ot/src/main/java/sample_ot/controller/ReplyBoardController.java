package sample_ot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import sample_ot.dto.ReplyBoardDTO;
import sample_ot.service.ReplyBoardService;
import sample_ot.service.ReplyBoardServicelmpl;

@Controller
public class ReplyBoardController {

   

	@Autowired
	ReplyBoardService replyBoardservice;

	// 게시글 목록으로 이동하는 컨트롤러
	@GetMapping("/board/list")
	public String boardList(Model model) {
		System.out.println("ReplyBoardController boardList() 호출");
		List<ReplyBoardDTO> replyList = replyBoardservice.getAllReplyBoard();
		
		model.addAttribute("rlist",replyList);
		
		return "replyboard/boardList";
	}
	
	// 1. 글쓰기 폼으로 이동하는 컨트롤러
	@GetMapping("/board/writer")
	public String boardWriterForm() {
		System.out.println("ReplyBoardController boardWriterForm() 호출" );
		String nextPage ="replyboard/BoardWriterForm";
		return nextPage;
	}
	
	// 2. 글쓰기를 처리하는 컨트롤러
	@GetMapping("/board/writerPro")
	public String boardWriterPro(Model model,ReplyBoardDTO rdto) {
		System.out.println("ReplyBoardController boardWriterPro() 호출 함");
		replyBoardservice.insertReplyBoard(rdto);
		return "redirect:/board/list";
	}
	
}
