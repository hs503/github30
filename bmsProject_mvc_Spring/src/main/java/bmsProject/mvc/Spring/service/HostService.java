package bmsProject.mvc.Spring.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.oreilly.servlet.MultipartRequest;

public interface HostService {
	//회원정보 보기
	public void Hmember(HttpServletRequest req, Model model);
	//회원삭제
	public void deleteMem(HttpServletRequest req, Model model);	
	//재고목록 보기
	public void bookList(HttpServletRequest req, Model model);	
	//주문목록 보기
	public void orderList(HttpServletRequest req, Model model);	
	//구매목록 보기
	public void delivaryList(HttpServletRequest req, Model model);	
	//재고목록 추가
	public void insertBook(HttpServletRequest req, Model model);	
	//책 재고 삭제
	public void deleteBook(HttpServletRequest req, Model model);	
	//책 재고 수정
	public void updateBook(HttpServletRequest req, Model model);
	//판매목록 추가
	public void delivery(HttpServletRequest req, Model model);
	//관리자 첫페이지
	public void hostMain(HttpServletRequest req, Model model);
	//환불
	public void refund(HttpServletRequest req, Model model);
	//환불목록 삭제
	public void deleteDel(HttpServletRequest req, Model model);
}
