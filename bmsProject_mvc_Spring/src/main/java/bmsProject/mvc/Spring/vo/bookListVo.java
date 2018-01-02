package bmsProject.mvc.Spring.vo;

import java.sql.Timestamp;

public class bookListVo {	
	
	int code;			//물류코드
	String image;		//책 이미지
	String title;		//책제목
	String author;		//저자
	int price;			//판매가
	int amount;			//재고수량
	String bookChat;	//책 종류
	Timestamp publisher;//출간일
	
	
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
