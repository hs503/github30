package bmsProject.mvc.Spring.vo;

import java.sql.Timestamp;

public class orderListVo {
	
	int num;			//������ ��ȣ
	String id;			//�ֹ��� id
	String name;		//�ֹ��� �̸�
	String hp;			//����ó
	String email;		//�̸���
	String addr;		//����� �ּ�
	String title;		//å ����
	int amount;			//���ż���
	Timestamp publisher;//���ų�¥
	int price;			//�����ݾ�
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Timestamp getPublisher() {
		return publisher;
	}
	public void setPublisher(Timestamp publisher) {
		this.publisher = publisher;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	
	
}