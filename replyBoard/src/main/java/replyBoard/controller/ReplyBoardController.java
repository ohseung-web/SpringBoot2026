package replyBoard.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import replyBoard.dto.ReplyBoardDTO;
import replyBoard.service.ReplyBoardService;

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
		
		return "/replyBoard/replyboardList";
	}
	
	// 1. 글쓰기 폼으로 이동하는 컨트롤러
	@GetMapping("/board/writer")
	public String boardWriterForm() {
		System.out.println("ReplyBoardController boardWriterForm() 호출" );
		String nextPage ="replyBoard/replyboardWrite_Form";
		return nextPage;
	}
	
	// 2. 글쓰기를 처리하는 컨트롤러
//	@GetMapping("/board/writerPro")
//	public String boardWriterPro(Model model,ReplyBoardDTO rdto) {
//		System.out.println("ReplyBoardController boardWriterPro() 호출 함");
//		replyBoardservice.insertReplyBoard(rdto);
//		return "redirect:/board/list";
//	}
	// 파일업로드는 PostMapping()이다.
	@PostMapping("/board/writerPro")
	public String boardWriterPro(Model model,ReplyBoardDTO rdto,
			@RequestParam("file1") MultipartFile upload1,
	        @RequestParam("file2") MultipartFile upload2
			) throws IllegalStateException, IOException {
		System.out.println("ReplyBoardController boardWriterPro() 호출 함");
		
		 // ===== 1. 이미지 업로드 처리 =====
	    if (!upload1.isEmpty()) {
	        String originalName1 = upload1.getOriginalFilename();
	        String saveName1 = UUID.randomUUID() + "_" + originalName1;

	        File file1 = new File("c:/upload/" + saveName1);
	        upload1.transferTo(file1);

	        // 👉 DB에 저장할 파일명 DTO에 세팅
	        rdto.setUpload1(saveName1);
	    }

	    if (!upload2.isEmpty()) {
	        String originalName2 = upload2.getOriginalFilename();
	        String saveName2 = UUID.randomUUID() + "_" + originalName2;

	        File file2 = new File("c:/upload/" + saveName2);
	        upload2.transferTo(file2);

	        rdto.setUpload2(saveName2);
	    }

		replyBoardservice.insertReplyBoard(rdto);
		return "redirect:/board/list";
	}
	
	// 3. 하나의 게시글 정보로 이동하는 컨트롤러
	@GetMapping("/board/detail")
	public String getOneBoard(@RequestParam("num") int num, Model model) {
		System.out.println("ReplyBoardController getOneBoard() 호출 함");
		
		ReplyBoardDTO oneList = replyBoardservice.getOneBoard(num);
		model.addAttribute("onelist",oneList);
		
		return "/replyBoard/replyboardDetail";
	}
	
	// 4. 답글 작헝하는 폼으로 이동하는 컨트롤러
	@GetMapping("/board/reply")
	public String reWriteForm(Model model, @RequestParam("num") int num,
			@RequestParam("ref") int ref,
			@RequestParam("re_step") int re_step,
			@RequestParam("re_level") int re_level
			) {
		
		model.addAttribute("num",num);
		model.addAttribute("ref",ref);
		model.addAttribute("re_step",re_step);
		model.addAttribute("re_level",re_level);
		
		return "/replyBoard/replyboardReWrite_Form";
	}
	
	// 5. 답글 작성을 처리하는 컨트롤러
	@PostMapping("/board/reWritePro")
	public String reWritePro(ReplyBoardDTO rdto) {
		replyBoardservice.replyProcess(rdto);
		return "redirect:/board/list";
	}
}
