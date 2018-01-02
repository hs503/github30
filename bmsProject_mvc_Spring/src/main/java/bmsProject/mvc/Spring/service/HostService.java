package bmsProject.mvc.Spring.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.oreilly.servlet.MultipartRequest;

public interface HostService {
	//ȸ������ ����
	public void Hmember(HttpServletRequest req, Model model);
	//ȸ������
	public void deleteMem(HttpServletRequest req, Model model);	
	//����� ����
	public void bookList(HttpServletRequest req, Model model);	
	//�ֹ���� ����
	public void orderList(HttpServletRequest req, Model model);	
	//���Ÿ�� ����
	public void delivaryList(HttpServletRequest req, Model model);	
	//����� �߰�
	public void insertBook(HttpServletRequest req, Model model);	
	//å ��� ����
	public void deleteBook(HttpServletRequest req, Model model);	
	//å ��� ����
	public void updateBook(HttpServletRequest req, Model model);
	//�ǸŸ�� �߰�
	public void delivery(HttpServletRequest req, Model model);
	//������ ù������
	public void hostMain(HttpServletRequest req, Model model);
	//ȯ��
	public void refund(HttpServletRequest req, Model model);
	//ȯ�Ҹ�� ����
	public void deleteDel(HttpServletRequest req, Model model);
}
