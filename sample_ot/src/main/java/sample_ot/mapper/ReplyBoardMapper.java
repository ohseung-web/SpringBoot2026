package sample_ot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import sample_ot.dto.ReplyBoardDTO;

@Mapper
public interface ReplyBoardMapper {

	// 게시글 작성하여 추가하기
	public void insertReplyBoard(ReplyBoardDTO rdto);
	
	// 게시글 전체 목록 검색
	public List<ReplyBoardDTO> getAllReplyBoard();
}
