package bmsProject.mvc.Spring.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface BoardService {
	//�۸��
	public void BoardList(HttpServletRequest req, Model model);
	//�˻����
	public void BoardSerch(HttpServletRequest req, Model model);
	//�� ������
	public void contentForm(HttpServletRequest req, Model model);
	
	//�ۼ��� �� ������
	public void modifyView(HttpServletRequest req, Model model);
	
	//�ۼ��� ������
	public void modifyPro(HttpServletRequest req, Model model);
	
	//�۾��� ������ db�� ����
	public void writePro(HttpServletRequest req, Model model);
	
	//�ۻ��� ������
	public void deletePro(HttpServletRequest req, Model model);
}
