package bmsProject.mvc.Spring.vo;

import java.sql.Timestamp;

public class cartVo {

	int num;
	int code;
	String id;			//������ id
	String image;		//å �̹���
	String title;		//å����
	String author;		//����
	int price;			//�ǸŰ�
	int amount;			//�Ǹż���
	Timestamp publisher;//�Ⱓ��

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
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
	
	
	
}
