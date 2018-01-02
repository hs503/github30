package bmsProject.mvc.Spring.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface BookService {
	//재고목록 보기
	public void bookList(HttpServletRequest req, Model model);
	//상세페이지 책 찾기
	public void serchBook(HttpServletRequest req, Model model);
	//상세페이지에서 바로 구매
	public void DirectBy(HttpServletRequest req, Model model);

}
