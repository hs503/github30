package bmsProject.mvc.Spring.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface MemberService {
	//중복확인
	public void confirmId(HttpServletRequest req, Model model);
	//회원가입처리
	public void inputPro(HttpServletRequest req, Model model);
	//로그인 처리
	public void loginPro(HttpServletRequest req, Model model);
	//회원정보 수정 상세 페이지
	public void modifyView(HttpServletRequest req, Model model);
	//회원정보 수정
	public void modifyPro(HttpServletRequest req, Model model);
	//회원탈퇴(정보삭제)
	public void delete(HttpServletRequest req, Model model);
	//장바구니 보기
	public void cartList(HttpServletRequest req, Model model);
	//장바구니 추가
	public void cartInsert(HttpServletRequest req, Model model);
	//장바구니 삭제
	public void cartDelete(HttpServletRequest req, Model model);
	//책 구매
	public void buyBook(HttpServletRequest req, Model model);
	//재고 검사
	public void AmountTest(HttpServletRequest req, Model model);
	//구매목록 보기
	public void buyList(HttpServletRequest req, Model model);
	//환불신청
	public void refund(HttpServletRequest req, Model model);
	//구매확정
	public void Confirmation(HttpServletRequest req, Model model);
	//검색
	public void serch(HttpServletRequest req, Model model);
	//장바구니 수량변경
	public void amountChange(HttpServletRequest req, Model model);
	//이메일 확인/인증
	public void confirmEmail(HttpServletRequest req, Model model);
	//아이디 찾기
	public void idSearch(HttpServletRequest req, Model model);
	//비밀번호 찾기
	public void pwdSearch(HttpServletRequest req, Model model);
}
