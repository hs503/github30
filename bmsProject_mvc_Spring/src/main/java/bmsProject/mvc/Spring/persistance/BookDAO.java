package bmsProject.mvc.Spring.persistance;

import java.util.Map;

import bmsProject.mvc.Spring.vo.bookListVo;
import bmsProject.mvc.Spring.vo.orderListVo;


public interface BookDAO {
	//å ������������ �� �� ��������
	public bookListVo serchBook(String image);
	//�Ǹ� ����Ʈ�� �߰�
	public int DeliveryInsert(orderListVo dto);
	//å ������ �˻�
	public int bookAmount(int code);	
	//ȸ������ ã��
	public orderListVo memberSerch(Map map);
}
