package bmsProject.mvc.Spring.persistance;

import java.util.Map;

import bmsProject.mvc.Spring.vo.bookListVo;
import bmsProject.mvc.Spring.vo.orderListVo;


public interface BookDAO {
	//책 상세페이지에서 쓸 값 가져오기
	public bookListVo serchBook(String image);
	//판매 리스트에 추가
	public int DeliveryInsert(orderListVo dto);
	//책 재고수량 검색
	public int bookAmount(int code);	
	//회원정보 찾기
	public orderListVo memberSerch(Map map);
}
