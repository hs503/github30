package bmsProject.mvc.Spring.vo;

import java.sql.Timestamp;

public class bookListVo {	
	
	int code;			//�����ڵ�
	String image;		//å �̹���
	String title;		//å����
	String author;		//����
	int price;			//�ǸŰ�
	int amount;			//������
	String bookChat;	//å ����
	Timestamp publisher;//�Ⱓ��
	
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
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
	public String getBookChat() {
		return bookChat;
	}
	public void setBookChat(String bookChat) {
		this.bookChat = bookChat;
	}
	public Timestamp getPublisher() {
		return publisher;
	}
	public void setPublisher(Timestamp publisher) {
		this.publisher = publisher;
	}
	
	
}
