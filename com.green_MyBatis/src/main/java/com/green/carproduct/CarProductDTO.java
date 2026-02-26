package com.green.carproduct;

import lombok.Data;

// lombok을 이용해 DTO의 getter, setter을 자동으로 진행한다.
@Data
public class CarProductDTO {
	private int no;  // -- 자동차 식별자
	private String carName; // -- 자동차 이름
	private int price; // 자동차 가격
	private String company; //자동차 회사
	private String img; //자동차 이미지
	private String info; // 자동차 설명
	
	
	
}
