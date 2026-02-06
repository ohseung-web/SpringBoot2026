package replyBoard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import replyBoard.dto.ReplyBoardDTO;
import replyBoard.mapper.ReplyBoardMapper;

@Service
public class ReplyBoardServicelmpl implements ReplyBoardService {
	@Autowired
	ReplyBoardMapper replyBoardMapper;

	@Override
	public void insertReplyBoard(ReplyBoardDTO rdto) {
		replyBoardMapper.insertReplyBoard(rdto);
	}

	@Override
	public List<ReplyBoardDTO> getAllReplyBoard() {
		return replyBoardMapper.getAllReplyBoard();
	}

	@Override
	public ReplyBoardDTO getOneBoard(int num) {
		return replyBoardMapper.getOneBoard(num);
	}

	// reWriteInsert()는 “INSERT만 하는 메소드” 여야 재사용하기 편리하다.
	@Override
	public void reWriteInsert(ReplyBoardDTO rdto) {
		replyBoardMapper.reWriteInsert(rdto);
	}

	@Override
	public void reSqUpdate(ReplyBoardDTO rdto) {
		replyBoardMapper.reSqUpdate(rdto);
		
	}

	@Override
	public void replyProcess(ReplyBoardDTO rdto) {
		//반드시 update를 먼저 실행해야 함
		replyBoardMapper.reSqUpdate(rdto);
		replyBoardMapper.reWriteInsert(rdto);
	}
	

}
