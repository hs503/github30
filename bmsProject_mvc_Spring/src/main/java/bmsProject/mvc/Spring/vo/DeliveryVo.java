package bmsProject.mvc.Spring.vo;

import java.sql.Timestamp;

public class DeliveryVo {
/*	num number(30) primary key,--������ ��ȣ
    id varchar2(70) not null,
    name varchar2(10) not null,--�ֹ��� �̸�
    hp varchar2(13) not null,  --����ó
    email varchar2(20),        --�̸���
    addr varchar2(200) not null,--����� �ּ�
    title varchar2(200) not null, --å �̸�
    amount number(20) not null,--���ż���
    publisher Timestamp default sysdate, --publisher ��������
    price number(20) not null,--�����ݾ�
    refund varchar2(100)default '�Ǹ�' --ȯ��ó�� ����[�Ǹ�/ȯ��]*/
	
	int num;
	String id;
	String name;
	String hp;
	String email;
	String addr;
	String title;
	int amount;
	Timestamp publisher;
	int price;
	String refund;
	
	
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
	public String getRefund() {
		return refund;
	}
	public void setRefund(String refund) {
		this.refund = refund;
	}
	
}
