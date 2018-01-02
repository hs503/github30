package bmsProject.mvc.Spring.persistance;

import java.util.ArrayList;
import java.util.Map;

import bmsProject.mvc.Spring.vo.DeliveryVo;
import bmsProject.mvc.Spring.vo.MemberVo;
import bmsProject.mvc.Spring.vo.bookListVo;
import bmsProject.mvc.Spring.vo.buyListVo;
import bmsProject.mvc.Spring.vo.cartVo;

public interface MemberDAO {
	// �ߺ�Ȯ�� üũ
	public int idCheck(String strId);
	// ȸ������
	public int insertMember(MemberVo vo);
	// �α��� ó��
	public int check(Map map);
	public String pwdS(Map map);
	
	// ���� ã��
	public String checkName(String strId);
	// ȸ������ �� ������
	public MemberVo getMemberInfo(String strId);
	// ȸ������ ����
	public int updateMember(MemberVo vo);
	// ȸ������ ����
	public int delete(String memId);
	// ��ٱ��� �߰�
	public int insertChat(Map map);
	// ��ٱ��� ���� Ȯ��
	public int getMemberCnt(String memId);
	
	public int getBookListCnt(String serchLike);
	// ��ٱ��� ��������
	public ArrayList<cartVo> getCartList(Map map);
	// ���Ÿ�� ��������
	public ArrayList<buyListVo> getbuyList(Map map);
	
	public ArrayList<bookListVo> getbookList(Map map);
	// code�� å ã��
	public bookListVo serchBook(int code);
	// ��ٱ��� ����
	public int cartDelete(int num);
	// ������
	public void BookUpdate(Map map2);
	// amount ��������
	public int bAmount(int code);
	// ���Ÿ�� ���� Ȯ��
	public int getbuyListCnt(String memId);
	// Delivery ���̺��� ȯ�ҿ��� ����
	public int refundUpdate(Map map);
	// closing ���̺��� refund 1����
	public void closingRefundUpdate();
	// ��ٱ��� ��������
	public int amountChange(Map map);
	// �̸��� Ȯ��/����
	public int confirmEmail(String email);
	// ���̵� ã��
	public String idSearch(String email);
	// ��й�ȣ ã��
	public String pwdSearch(String id);
	// �̸��� ã��
	public String emailSearch(String id);
}

