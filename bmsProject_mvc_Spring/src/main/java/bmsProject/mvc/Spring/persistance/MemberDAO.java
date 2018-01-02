package bmsProject.mvc.Spring.persistance;

import java.util.ArrayList;
import java.util.Map;

import bmsProject.mvc.Spring.vo.DeliveryVo;
import bmsProject.mvc.Spring.vo.MemberVo;
import bmsProject.mvc.Spring.vo.bookListVo;
import bmsProject.mvc.Spring.vo.buyListVo;
import bmsProject.mvc.Spring.vo.cartVo;

public interface MemberDAO {
	// 중복확인 체크
	public int idCheck(String strId);
	// 회원가입
	public int insertMember(MemberVo vo);
	// 로그인 처리
	public int check(Map map);
	public String pwdS(Map map);
	
	// 고객명 찾기
	public String checkName(String strId);
	// 회원정보 상세 페이지
	public MemberVo getMemberInfo(String strId);
	// 회원정보 수정
	public int updateMember(MemberVo vo);
	// 회원정보 삭제
	public int delete(String memId);
	// 장바구니 추가
	public int insertChat(Map map);
	// 장바구니 갯수 확인
	public int getMemberCnt(String memId);
	
	public int getBookListCnt(String serchLike);
	// 장바구니 가져오기
	public ArrayList<cartVo> getCartList(Map map);
	// 구매목록 가져오기
	public ArrayList<buyListVo> getbuyList(Map map);
	
	public ArrayList<bookListVo> getbookList(Map map);
	// code로 책 찾기
	public bookListVo serchBook(int code);
	// 장바구니 삭제
	public int cartDelete(int num);
	// 재고수정
	public void BookUpdate(Map map2);
	// amount 가져오기
	public int bAmount(int code);
	// 구매목록 갯수 확인
	public int getbuyListCnt(String memId);
	// Delivery 테이블의 환불여부 변경
	public int refundUpdate(Map map);
	// closing 테이블의 refund 1증가
	public void closingRefundUpdate();
	// 장바구니 수량변경
	public int amountChange(Map map);
	// 이메일 확인/인증
	public int confirmEmail(String email);
	// 아이디 찾기
	public String idSearch(String email);
	// 비밀번호 찾기
	public String pwdSearch(String id);
	// 이메일 찾기
	public String emailSearch(String id);
}

