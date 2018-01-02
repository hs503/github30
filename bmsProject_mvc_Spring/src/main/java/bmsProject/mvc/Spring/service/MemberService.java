package bmsProject.mvc.Spring.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface MemberService {
	//�ߺ�Ȯ��
	public void confirmId(HttpServletRequest req, Model model);
	//ȸ������ó��
	public void inputPro(HttpServletRequest req, Model model);
	//�α��� ó��
	public void loginPro(HttpServletRequest req, Model model);
	//ȸ������ ���� �� ������
	public void modifyView(HttpServletRequest req, Model model);
	//ȸ������ ����
	public void modifyPro(HttpServletRequest req, Model model);
	//ȸ��Ż��(��������)
	public void delete(HttpServletRequest req, Model model);
	//��ٱ��� ����
	public void cartList(HttpServletRequest req, Model model);
	//��ٱ��� �߰�
	public void cartInsert(HttpServletRequest req, Model model);
	//��ٱ��� ����
	public void cartDelete(HttpServletRequest req, Model model);
	//å ����
	public void buyBook(HttpServletRequest req, Model model);
	//��� �˻�
	public void AmountTest(HttpServletRequest req, Model model);
	//���Ÿ�� ����
	public void buyList(HttpServletRequest req, Model model);
	//ȯ�ҽ�û
	public void refund(HttpServletRequest req, Model model);
	//����Ȯ��
	public void Confirmation(HttpServletRequest req, Model model);
	//�˻�
	public void serch(HttpServletRequest req, Model model);
	//��ٱ��� ��������
	public void amountChange(HttpServletRequest req, Model model);
	//�̸��� Ȯ��/����
	public void confirmEmail(HttpServletRequest req, Model model);
	//���̵� ã��
	public void idSearch(HttpServletRequest req, Model model);
	//��й�ȣ ã��
	public void pwdSearch(HttpServletRequest req, Model model);
}
