package bmsProject.mvc.Spring.persistance;

import java.util.ArrayList;
import java.util.Map;

import bmsProject.mvc.Spring.vo.DeliveryVo;
import bmsProject.mvc.Spring.vo.MemberVo;
import bmsProject.mvc.Spring.vo.bookListVo;
import bmsProject.mvc.Spring.vo.closingVo;
import bmsProject.mvc.Spring.vo.orderListVo;


public interface HostDAO {
	//회원 테이블 글 갯수 구하기
	public int getMemberCnt();
	
	public int deleteMem(String id);
	//고객정보 목록 조회
	public ArrayList<MemberVo> getMemberList(Map<String, Integer> map);
	//재고목록에 수량 복구(환불했을시)
	public void bookRefund(Map map);
	//재고 테이블 글 갯수 구하기
	public int getBookListCnt();
	//주문 테이블 글 갯수 구하기
	public int getOrderListCnt();
	//구매 테이블 글 갯수 구하기
	public int getdelivaryListCnt();
	//재고 목록 조회
	public ArrayList<bookListVo> getBookList(Map<String,Integer> map);
	//closing테이블의 refund갯수를 변경
	public void updateClosingRefund(int refundCnt);
	//주문 목록 조회
	public ArrayList<orderListVo> getOrderList(Map<String,Integer> map);
	//환불갯수 줄이기
	public void subRefundCnt();
	//구매 목록 조회
	public ArrayList<DeliveryVo> getdeliveryList(Map<String, Integer> map);
	//delivery테이블의 refund가 '환불신청'인것의 갯수 구하기
	public int selectRefundCnt();
	//주문 정보 조회
	public orderListVo odSelect(int num);
	//재고 추가
	public int insertBook(bookListVo dto);
	//재고 삭제
	public int deleteBook(int code);
	//재고 수정
	public void updateBook(bookListVo dto);
	//판매 정보 입력
	public int insertDeli(orderListVo dto);
	
	public void sellCntUpdate(Map map);
	//책 판매수량 증가
	public String sellCnt(Map map);
	//가져온 차트를 closing테이블에서 맞는 차트의 판매갯수 증가.+결산금액 더하기
	public void totalSale(Map map2);
	//주문목록 삭제
	public void orderDel(int num);
	//closing 값 찾기
	public closingVo closingSerch(closingVo dto);
	//재고가 0인것의 갯수 구하기
	public int bookAmountSerch();
	//closing 테이블의 soltOutCnt에 품절갯수 저장
	public void updateClosing(int nullAmountCnt);
	//[환불]총판매금액 -
	public void subTotalSale(int price);
	//환불목록 삭제
	public int deleteDel(int num);
	//bookList에서 chat가져오기
	public String serchChat(String title);
	//closing테이블에서 환불갯수만큼 chat구매수량빼기
	public void subChat(Map map2);
	//bookList테이블의 sellCnt를 환불한 만큼 빼주기
	public void subSellCnt(Map map);
	
	public void delUpdate(int num);
}
