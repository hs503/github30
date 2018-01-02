package bmsProject.mvc.Spring.vo;

import java.sql.Timestamp;

public class DeliveryVo {
/*	num number(30) primary key,--구분할 번호
    id varchar2(70) not null,
    name varchar2(10) not null,--주문자 이름
    hp varchar2(13) not null,  --연락처
    email varchar2(20),        --이메일
    addr varchar2(200) not null,--배송지 주소
    title varchar2(200) not null, --책 이름
    amount number(20) not null,--구매수량
    publisher Timestamp default sysdate, --publisher 구매일자
    price number(20) not null,--결제금액
    refund varchar2(100)default '판매' --환불처리 여부[판매/환불]*/
	
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
