package sample_ot.service;

import java.util.List;

import sample_ot.dto.ReplyBoardDTO;

public interface ReplyBoardService {
	
	    // 게시글 작성하여 추가하기
		public void insertReplyBoard(ReplyBoardDTO rdto);
		
		// 게시글 전체 목록 검색
		public List<ReplyBoardDTO> getAllReplyBoard();
}
