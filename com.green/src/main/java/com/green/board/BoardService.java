package com.green.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
   
	@Autowired
	BoardDAO boardDao;
	
	// 하나의 게시글이 추가되는 메소드를 BoardDAO에서 접근하여 사용
	public void addBoard(BoardDTO bdto) {
		System.out.println("3)BoardService addBoard()메소드 호출 ");
		boardDao.insertBoard(bdto);
	}
	
	// 게시글 전체 목록을 출력하는 메소드
	public List<BoardDTO> allBoard(){
		System.out.println("3)BoardService allBoard()메소드 호출 ");
		return boardDao.getAllBoard();
	}
	
	// 하나의 게시글을 출력하는 메소드
	public BoardDTO OneBoard(int num) {
		System.out.println("3)BoardService OneBoard()메소드 호출 ");
		return boardDao.getOneBoard(num);
	}
	
	// 하나의 게시글을 수정하는 메소드
	public boolean modifyBoard(BoardDTO bdto) {
		System.out.println("3)BoardService modifyBoard()메소드 호출 ");
		
		int result = boardDao.updateBoard(bdto);
		
		if(result > 0) {
			System.out.println("게시글 수정 성공");
			return true; // 수정이 된 경우
		}else {
			System.out.println("게시글 수정 실패(비밀번호 불일치)");
			return false; // 수정이 안된경우
		}
	}
	
	// 게시글 하나를 삭제하는 메소드
	public boolean removeBoard(int num, String writerPw) {
		System.out.println("3)BoardService removeBoard()메소드 호출 ");
		// DAO에서 받아오는 deleteBoard()는 삭제=1, 아니면 0
		int result = boardDao.deleteBoard(num, writerPw);
		if(result > 0) {
			System.out.println("게시글 삭제 성공");
			return true;
		}else {
			System.out.println("게시글 삭제 실패(비밀번호 불일치)");
			return false;
		}
	}
	
	
	// 게시글 검색하는 메소드
	public List<BoardDTO> searchBoard(String searchType, String searchKeyword){
		System.out.println("3)BoardService searchBoard()메소드 호출 ");
		System.out.println("3)searchType : "+searchType);
		System.out.println("3)searchKeyword : "+searchKeyword);
		return boardDao.getSearchBoard(searchType, searchKeyword);
	}
	
	
	
	
	
}
