package com.green;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.green.carproduct.CarProductDTO;
import com.green.carproduct.CarProductService;
import com.green.member.MemberDTO;
import com.green.member.MemberService;

import jakarta.servlet.http.HttpSession;


//@RestController는 @Controller + @ResponseBody를 합친
//어노테이션이다. => 컨트롤러 역활 + 데이터를 JSON으로 응답하여 사용하겠다.
//@ResponseBody는 메소드가 변환하는 데이터를 HTML 뷰를 찾는 용도가
//              아니라, 데이터 그 자체(JSON)로 응답 받아 직접쓰겠다는 의미
//@RestController 하나만 맨 위에 적어주면 모든 메소들은 
//@ResponseBody를 붙이지 않아도 된다.
@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	CarProductService carProductservice;//carList메소드
	
	@Autowired
	MemberService memberservice;
	
	// 자동차 리스트를 JSON으로 변환하는 API
	@GetMapping("/cars")
	public List<CarProductDTO> getCarList(){
		System.out.println("ApiController:  자동차 리스트 요청됨");
		// DB에서 데이터를 가져와서 그대로 리턴(Spring가 자동으로 JSON배열로 변환)
		// [{no:~,carName:~}]
		return carProductservice.getAllCarProduct();
	}
	
	//회원가입 API(POST방식)
	// @RequestBody어노테이션은 리액트에서 보낸 JSON데이터를
	// -> 자바 객체(MemberDTO)로 자동 변환해준다.
	@PostMapping("/member/signup")
	public int signup(@RequestBody MemberDTO mdto) {
		System.out.println("ApiController:  signup 요청됨");
		return memberservice.signupConfirm(mdto);
	}
	
	// 로그인 메소드
	@PostMapping("/member/login")
	public MemberDTO login(@RequestBody MemberDTO mdto,
			HttpSession session) {
		
		// loginUser = {no:~, id:~, pw:~, mail:~, phone:~}
		MemberDTO loginUser = memberservice.loginConfirm(mdto);
		
		if(loginUser != null) {
			// 세션에 로그인 정보 담기
			session.setAttribute("loginUser", loginUser.getId());
		}
		
		// React로 JSON 변환
		return loginUser;
	}
	
	// 로그아웃
	@GetMapping("/member/logout")
	public int logout(HttpSession session) {
		 session.invalidate(); // 세션 삭제
		 return 1; // 성공 
	}
	
	// 한 사람의 개인정보를 조회 하는 메소드
	// select 
    @GetMapping("/member/myinfo")
    public MemberDTO myInfo(HttpSession session) {
    	// 세션에서 로그인 한 사용자 꺼내기
    	// 다운캐스팅한다.
    	String loginId = (String)session.getAttribute("loginUser");
    	
    	if(loginId == null) {
    		 // 로그인이 안되 었으면 null로 반환
    		 return null;
    	}
    	
    	    // 로그인이 되어 있으면 DB 조회
    	    return memberservice.oneSelect(loginId);
    }
    
    // 한 사람 개인 정보를 삭제하는 컨트롤러
    // 삭제 하다 => @DeleteMapping()
    @DeleteMapping("/member/delete")
    public int delete(HttpSession session) {
    	  
    	String loginId = (String)session.getAttribute("loginUser");
    	  if(loginId == null) {
    		   return 0;
    	  }
    	  
    	  // 삭제 서비스 메소드
    	  // 삭제가 완료되면 1(true), 삭제 안되면 0(false)
    	  boolean result = memberservice.oneDelete(loginId);
    	  if(result) {
    		  // 로그아웃
    		  session.invalidate(); //세션삭제로 로그아웃
    		  return 1;
    	  }else {
    		  return 0;
    	  }
    	  
    }
    
    // 게시팡 검색
 // 게시판 검색 + 페이징 API
//    @GetMapping("/board/list")
//    public Map<String, Object> boardList(
//            @RequestParam(value="searchType", required=false) String searchType,
//            @RequestParam(value="searchKeyword", required=false) String searchKeyword,
//            @RequestParam(value="page", defaultValue="1") int page,
//            @RequestParam(value="pageSize", defaultValue="5") int pageSize
//    ) {
//
//        int totalCnt;
//
//        if(searchType != null && searchKeyword != null && !searchKeyword.trim().isEmpty()) {
//            totalCnt = boardservice.getSearchCount(searchType, searchKeyword);
//        } else {
//            totalCnt = boardservice.getAllcount();
//        }
//
//        PageHandler ph = new PageHandler(totalCnt, page, pageSize);
//
//        List<BoardDTO> listboard;
//
//        if(searchType != null && searchKeyword != null && !searchKeyword.trim().isEmpty()) {
//            listboard = boardservice.getSearchPageList(
//                    searchType,
//                    searchKeyword,
//                    ph.getStartRow(),
//                    pageSize);
//        } else {
//            listboard = boardservice.getPagelist(
//                    ph.getStartRow(),
//                    pageSize);
//        }
//
//        // React로 보낼 JSON 데이터
//        Map<String, Object> result = new HashMap<>();
//        result.put("list", listboard);
//        result.put("ph", ph);
//
//        return result;
//    }

   // @GetMapping("/board/list")
//    public BoardResponseDTO boardList(
//            @RequestParam(required=false) String searchType,
//            @RequestParam(required=false) String searchKeyword,
//            @RequestParam(defaultValue="1") int page,
//            @RequestParam(defaultValue="5") int pageSize
//    ) {
//
//        // 1️⃣ 검색 여부 먼저 판단
//        boolean isSearch = 
//                searchKeyword != null && !searchKeyword.trim().isEmpty();
//
//        // 2️⃣ 전체 개수 구하기
//        int totalCnt = isSearch
//                ? boardservice.getSearchCount(searchType, searchKeyword)
//                : boardservice.getAllcount();
//
//        PageHandler ph = new PageHandler(totalCnt, page, pageSize);
//
//        // 3️⃣ 리스트 가져오기
//        List<BoardDTO> list = isSearch
//                ? boardservice.getSearchPageList(
//                        searchType,
//                        searchKeyword,
//                        ph.getStartRow(),
//                        pageSize)
//                : boardservice.getPagelist(
//                        ph.getStartRow(),
//                        pageSize);
//
//        // 4️⃣ 응답 객체로 반환
//        return new BoardResponseDTO(list, ph);
//    }

 
    // 이미지 React에서 업로드해서 DTO에 한번에 받기
    // @ModelAttribute는 스프링 프레임워크에서 클라이언트가 보낸
    // 데이터를 자바 객체(DTO)로 자동 바인딩(=연결)해주는 어노테이션이다.
    @PostMapping("/cars/insert")
    public int insertCarProduct(
    		@ModelAttribute CarProductDTO cdto,
    		@RequestParam("uploadFile") MultipartFile file
    		) throws Exception {
    	     System.out.println("자동차 등록 요청");
    	     
    	     //저장경로
    	     String savePath = "C:/Spring_Boot/com.green_MyBatis/frontend/public/img/car/";
    	     
    	     // 저장할 경로가 존재하지 않으면 자동으로 생성하는 코드
    	     File dir = new File(savePath);
    	     if(!dir.exists()) {
    	    	    dir.mkdirs();
    	     }
    	     
    	     String fileName="";
    	     if(!file.isEmpty()) {
	    	    	 //사용자가 올린 파일명을 가져온다.
	    	    	 String originalName = file.getOriginalFilename();
	    	    	 
	    	    	 // 파일명 중복해서 입력되지 않도록 UUID클래스 이용
	    	    	 // UUID가 36글자까지 랜덤하게 출력한다.
	    	    	 fileName = UUID.randomUUID().toString().substring(0,4)+"_"+originalName;
	    	    	 // 파일전송
	    	    	 File saveFile = new File(savePath + fileName);
	    	    	 file.transferTo(saveFile);
    	     }
    	     
    	     // DTO중 setImg()에 파일명만 세팅한다.
    	     cdto.setImg(fileName);
    	     
    	     // DB에 저장
    	     carProductservice.insertCarProduct(cdto);
    	     
    	     return 1;
    	     
    }
    
    
    
	 
}
