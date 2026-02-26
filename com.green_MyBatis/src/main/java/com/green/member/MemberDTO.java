package com.green.member;

import lombok.Data;

@Data
public class MemberDTO {
	private int no; //사용자 고유번호
	private String id;//사용자 아이디
	private String pw;//사용자 비밀번호
	private String mail;//사용자 email
	private String phone;//사용자 전화번호
	private String reg_date; //사용자 정보 등록일
	private String mod_date; //사용자 정보 수정일

}
