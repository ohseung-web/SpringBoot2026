package replyBoard.service;

import java.util.List;

import replyBoard.dto.ReplyBoardDTO;

public interface ReplyBoardService {
	        // 게시글 작성하여 추가하기
			public void insertReplyBoard(ReplyBoardDTO rdto);
			
			// 게시글 전체 목록 검색
			public List<ReplyBoardDTO> getAllReplyBoard();
			
			// 하나의 게시글을 리턴받는 메소드 작성
		    public ReplyBoardDTO getOneBoard(int num);
		    
		    // 답글 작성하여 추가하기
		    public void reWriteInsert(ReplyBoardDTO rdto);
		    
		    // 답글 작성시 부모글의 re_level 보다 큰 값들을 모두 1씩 증가시키는 메소드
		    public void reSqUpdate(ReplyBoardDTO rdto);
		    
		    public void replyProcess(ReplyBoardDTO rdto);
}
