package com.green.board;

import lombok.Data;

@Data
public class BoardDTO {
	// 반드시 MySQL에 작성한 테이블 필드명 순서, 
	// 데이터 타입이랑 같은 형식으로 작성한다.
  private int num;//글번호 
  private String writer;//글작성자 
  private String subject;//글제목 
  private String writerPw;//글비밀번호 
  private String reg_date;//글 작성일자
  private int readcount;//조회수
  private String content;//글내용 
  private String id; //회원 아이디
  
 
  
}

