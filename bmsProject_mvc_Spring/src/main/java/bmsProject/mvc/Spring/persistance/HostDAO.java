package bmsProject.mvc.Spring.persistance;

import java.util.ArrayList;
import java.util.Map;

import bmsProject.mvc.Spring.vo.DeliveryVo;
import bmsProject.mvc.Spring.vo.MemberVo;
import bmsProject.mvc.Spring.vo.bookListVo;
import bmsProject.mvc.Spring.vo.closingVo;
import bmsProject.mvc.Spring.vo.orderListVo;


public interface HostDAO {
	//ȸ�� ���̺� �� ���� ���ϱ�
	public int getMemberCnt();
	
	public int deleteMem(String id);
	//������ ��� ��ȸ
	public ArrayList<MemberVo> getMemberList(Map<String, Integer> map);
	//����Ͽ� ���� ����(ȯ��������)
	public void bookRefund(Map map);
	//��� ���̺� �� ���� ���ϱ�
	public int getBookListCnt();
	//�ֹ� ���̺� �� ���� ���ϱ�
	public int getOrderListCnt();
	//���� ���̺� �� ���� ���ϱ�
	public int getdelivaryListCnt();
	//��� ��� ��ȸ
	public ArrayList<bookListVo> getBookList(Map<String,Integer> map);
	//closing���̺��� refund������ ����
	public void updateClosingRefund(int refundCnt);
	//�ֹ� ��� ��ȸ
	public ArrayList<orderListVo> getOrderList(Map<String,Integer> map);
	//ȯ�Ұ��� ���̱�
	public void subRefundCnt();
	//���� ��� ��ȸ
	public ArrayList<DeliveryVo> getdeliveryList(Map<String, Integer> map);
	//delivery���̺��� refund�� 'ȯ�ҽ�û'�ΰ��� ���� ���ϱ�
	public int selectRefundCnt();
	//�ֹ� ���� ��ȸ
	public orderListVo odSelect(int num);
	//��� �߰�
	public int insertBook(bookListVo dto);
	//��� ����
	public int deleteBook(int code);
	//��� ����
	public void updateBook(bookListVo dto);
	//�Ǹ� ���� �Է�
	public int insertDeli(orderListVo dto);
	
	public void sellCntUpdate(Map map);
	//å �Ǹż��� ����
	public String sellCnt(Map map);
	//������ ��Ʈ�� closing���̺��� �´� ��Ʈ�� �ǸŰ��� ����.+���ݾ� ���ϱ�
	public void totalSale(Map map2);
	//�ֹ���� ����
	public void orderDel(int num);
	//closing �� ã��
	public closingVo closingSerch(closingVo dto);
	//��� 0�ΰ��� ���� ���ϱ�
	public int bookAmountSerch();
	//closing ���̺��� soltOutCnt�� ǰ������ ����
	public void updateClosing(int nullAmountCnt);
	//[ȯ��]���Ǹűݾ� -
	public void subTotalSale(int price);
	//ȯ�Ҹ�� ����
	public int deleteDel(int num);
	//bookList���� chat��������
	public String serchChat(String title);
	//closing���̺��� ȯ�Ұ�����ŭ chat���ż�������
	public void subChat(Map map2);
	//bookList���̺��� sellCnt�� ȯ���� ��ŭ ���ֱ�
	public void subSellCnt(Map map);
	
	public void delUpdate(int num);
}
