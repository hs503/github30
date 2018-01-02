package bmsProject.mvc.Spring.vo;

import java.sql.Timestamp;

public class BoardVO {
	private int num; 			//글번호
	private String id;
	private String writer;		//작성자
	private String pwd;			//비밀번호
	private String subject;		//글제목
	private String content;		//글내용
	private int readCnt;		//조회수
	private int ref;			//답변글 그룹화 아이디 --답변 글번호로서 원글번호와 일치하여야 한다.
	private int ref_step;		//답변글 그룹 스텝
	private int ref_level;		//답변글 그룹 레벨
	private Timestamp req_date;	//작성일
	
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
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadCnt() {
		return readCnt;
	}
	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getRef_step() {
		return ref_step;
	}
	public void setRef_step(int ref_step) {
		this.ref_step = ref_step;
	}
	public int getRef_level() {
		return ref_level;
	}
	public void setRef_level(int ref_level) {
		this.ref_level = ref_level;
	}
	public Timestamp getReq_date() {
		return req_date;
	}
	public void setReq_date(Timestamp req_date) {
		this.req_date = req_date;
	}
	
}
