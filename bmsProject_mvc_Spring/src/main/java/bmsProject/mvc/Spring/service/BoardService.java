package bmsProject.mvc.Spring.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface BoardService {
	//글목록
	public void BoardList(HttpServletRequest req, Model model);
	//검색목록
	public void BoardSerch(HttpServletRequest req, Model model);
	//상세 페이지
	public void contentForm(HttpServletRequest req, Model model);
	
	//글수정 상세 페이지
	public void modifyView(HttpServletRequest req, Model model);
	
	//글수정 페이지
	public void modifyPro(HttpServletRequest req, Model model);
	
	//글쓰기 페이지 db에 저장
	public void writePro(HttpServletRequest req, Model model);
	
	//글삭제 페이지
	public void deletePro(HttpServletRequest req, Model model);
}
