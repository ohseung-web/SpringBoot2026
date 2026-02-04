package sample_ot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sample_ot.dto.ReplyBoardDTO;
import sample_ot.mapper.ReplyBoardMapper;

@Service
public class ReplyBoardServicelmpl implements ReplyBoardService{
    
	@Autowired
	ReplyBoardMapper replyBoardMapper;

	@Override
	public void insertReplyBoard(ReplyBoardDTO rdto) {
		// TODO Auto-generated method stub
		replyBoardMapper.insertReplyBoard(rdto);
	}

	@Override
	public List<ReplyBoardDTO> getAllReplyBoard() {
		// TODO Auto-generated method stub
		return replyBoardMapper.getAllReplyBoard();
	}
	
	

}
