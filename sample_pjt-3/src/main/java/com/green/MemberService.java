package com.green;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// MemberService 클래스는 비즈니스 로직을 작성하는 클래스이다.
// @Service의 의미이다.
@Service
public class MemberService {

	//MemberDAO 클래스를 MemberService클래스에서 사용하는 방법
	//DI를 이용해 외부로 부터 객체를 주입하여 사용한다.
	// DI를 의미하는 @AutoWride를 사용한다.
	@Autowired
	MemberDAO memberDao;
	
	public void signUpConfirm(MemberDTO mdto) {
		System.out.println("회원가입 정보 출력화면 이야~");
		memberDao.insertMember(mdto);
	}

	public void signinConfirm(MemberDTO mdto) {
		System.out.println("로그인 정보 출력화면 이야~");
		
		
	    MemberDTO loginMember = memberDao.selectMember(mdto);
		
		// if문을 이용해서 id,pw가 DB에존재하면 로그인성공, 
				// 아니면 로그인 실패로 출력하기
				// Cannot invoke "com.green.MemberDTO.getId()" 
				// because "loginMember" is null 
				// => 로그인할 id, pw입력하지 않은채로 로그인을 실행했을 때 발생하는 오류이다.
				// 그러므로 반드시 null을 예외로 처리한다.
				if(loginMember !=null && loginMember.getPw().equals(mdto.getPw())
						) {
					System.out.println("id: "+loginMember.getId());
					System.out.println("pw: "+loginMember.getPw());
					//System.out.println("email: "+loginMember.getEmail());
					System.out.println("로그인 성공!");
				}else {
					System.out.println("로그인 실패");
				}
				
	}
	
	public List<MemberDTO> findAllMembers() {
        return memberDao.getAllMembers();
    }

}
