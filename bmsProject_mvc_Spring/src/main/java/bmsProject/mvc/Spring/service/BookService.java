package bmsProject.mvc.Spring.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface BookService {
	//����� ����
	public void bookList(HttpServletRequest req, Model model);
	//�������� å ã��
	public void serchBook(HttpServletRequest req, Model model);
	//������������ �ٷ� ����
	public void DirectBy(HttpServletRequest req, Model model);

}
