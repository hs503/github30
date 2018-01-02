package bmsProject.mvc.Spring.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import bmsProject.mvc.Spring.config.Configuration;
import bmsProject.mvc.Spring.vo.DeliveryVo;
import bmsProject.mvc.Spring.vo.MemberVo;
import bmsProject.mvc.Spring.vo.bookListVo;
import bmsProject.mvc.Spring.vo.buyListVo;
import bmsProject.mvc.Spring.vo.cartVo;
@Repository
public class MemberDAOImpl implements MemberDAO{
	// 컨넥션 객체를 보관
	DataSource datasource;
	
	// 싱글톤 방식 : 객체를 1번만 생성하겠다.(private 객체 생성)
	// 호출 :  MemberDAOImpl dao = MemberDAOImpl.getInstance();
	// 다형성 적용 : MemberDAO dao = MemberDAOImpl.getInstance();
	private static MemberDAOImpl instance = new MemberDAOImpl();
	
	// static이므로 클래스명.메소드(); MemberDAOImpl.getInstance();
	public static MemberDAOImpl getInstance() {
		return instance;
	}
	// 생성자
	public MemberDAOImpl() {
		try {
			// 컨넥션풀(DBCP) : Servers > context.xml
			Context context = new InitialContext(); 
			datasource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insertMember(MemberVo vo) {
		int cnt = 0;
		MemberDAO dao=Configuration.getMapper(MemberDAO.class);
		cnt=dao.insertMember(vo);
		
		return cnt;
	}
	
	// 중복확인
	@Override
	public int idCheck(String strId) {
		int cnt = 0;
		
		MemberDAO dao=Configuration.getMapper(MemberDAO.class);
		cnt=dao.idCheck(strId);
		
		return cnt;
	}
	@Override
	public String pwdS(Map map) {
		String pwd="";
		
		MemberDAO dao=Configuration.getMapper(MemberDAO.class);
		pwd=dao.pwdS(map);
		
		return pwd;
	}
	
	@Override
	public int check(Map map) {
		int cnt = 0;
		
		MemberDAO dao=Configuration.getMapper(MemberDAO.class);
		cnt=dao.check(map);
		
		if(cnt>0) {
			String pwd=dao.pwdS(map);
			if(map.get("strPwd").equals(pwd)) {
				cnt=1;
			}else {
				cnt=-1;
			}
		}
		
		return cnt;	
	}
	@Override
	public String checkName(String strId) {
	
		String name="";
		MemberDAO dao=Configuration.getMapper(MemberDAO.class);
		name=dao.checkName(strId);
		
		return name;	
	}
	@Override
	public MemberVo getMemberInfo(String strId) {
		MemberVo vo = new MemberVo();
		
		MemberDAO dao=Configuration.getMapper(MemberDAO.class);
		vo=dao.getMemberInfo(strId);
		
		return vo;
	}
	@Override
	public int updateMember(MemberVo vo) {
		int cnt = 0;
		
		MemberDAO dao=Configuration.getMapper(MemberDAO.class);
		cnt=dao.updateMember(vo);
		
		return cnt;	
	}
	@Override
	public int delete(String memId) {
		int cnt=0;
		
		MemberDAO dao=Configuration.getMapper(MemberDAO.class);
		cnt=dao.delete(memId);	
		
		return cnt;
	}
	@Override
	public int insertChat(Map map) {
		int cnt = 0;
		MemberDAO dao=Configuration.getMapper(MemberDAO.class);
		cnt=dao.insertChat(map);
		
		return cnt;
	}
	@Override
	public int getMemberCnt(String memId) {
		int cnt = 0;
		MemberDAO dao=Configuration.getMapper(MemberDAO.class);
		cnt=dao.getMemberCnt(memId);
		
		return cnt;
	}
	@Override
	public ArrayList<cartVo> getCartList(Map map) {
		ArrayList<cartVo> dtos=null; //큰 바구니
		
		MemberDAO dao=Configuration.getMapper(MemberDAO.class);
		dtos=dao.getCartList(map);
		
		return dtos;
	}
	@Override
	public bookListVo serchBook(int code) {
		
		bookListVo dto=new bookListVo();
		MemberDAO dao=Configuration.getMapper(MemberDAO.class);
		dto=dao.serchBook(code);
		
		return dto;
	}
	@Override
	public int cartDelete(int num) {
		int cnt=0;
		MemberDAO dao=Configuration.getMapper(MemberDAO.class);
		cnt=dao.cartDelete(num);		
		
		return cnt;
	}
	
	@Override
	public void BookUpdate(Map map2) {
		MemberDAO dao=Configuration.getMapper(MemberDAO.class);
		dao.BookUpdate(map2);		
	}
	@Override
	public int bAmount(int code) {
		
		int amount=0;
		MemberDAO dao=Configuration.getMapper(MemberDAO.class);
		amount=dao.bAmount(code);
		return amount;
		
	}
	@Override
	public int getbuyListCnt(String memId) {
		int cnt = 0;
		
		MemberDAO dao=Configuration.getMapper(MemberDAO.class);
		cnt=dao.getbuyListCnt(memId);
		
		return cnt;
	}
	@Override
	public ArrayList<buyListVo> getbuyList(Map map) {
		ArrayList<buyListVo> dtos=null; //큰 바구니
		
		MemberDAO dao=Configuration.getMapper(MemberDAO.class);
		dtos=dao.getbuyList(map);
		
		return dtos;
	}
	@Override
	public int refundUpdate(Map map) {
		
		int refundCnt=0;
		MemberDAO dao=Configuration.getMapper(MemberDAO.class);
		refundCnt=dao.refundUpdate(map);
		
		return refundCnt;		
	}
	@Override
	public void closingRefundUpdate() {
		MemberDAO dao=Configuration.getMapper(MemberDAO.class);
		dao.closingRefundUpdate();
	}
	@Override
	public int getBookListCnt(String serchLike) {
		int cnt = 0;
		
		MemberDAO dao=Configuration.getMapper(MemberDAO.class);
		cnt=dao.getBookListCnt(serchLike);
		
		return cnt;
	}
	@Override
	public ArrayList<bookListVo> getbookList(Map map) {
		ArrayList<bookListVo> dtos=null; //큰 바구니
		
		MemberDAO dao=Configuration.getMapper(MemberDAO.class);
		dtos=dao.getbookList(map);
		
		return dtos;
	}
	@Override
	public int amountChange(Map map) {
		int cnt = 0;
		
		MemberDAO dao=Configuration.getMapper(MemberDAO.class);
		cnt=dao.amountChange(map);
		
		return cnt;	
		
	}
	@Override
	public int confirmEmail(String email) {
		int cnt = 0;		
		MemberDAO dao=Configuration.getMapper(MemberDAO.class);
		cnt=dao.confirmEmail(email);
		
		return cnt;
	}
	@Override
	public String idSearch(String email) {
		String id = null;
		MemberDAO dao=Configuration.getMapper(MemberDAO.class);
		id=dao.idSearch(email);
		
		return id;
	}
	@Override
	public String pwdSearch(String id) {
		String pwd = null;
		MemberDAO dao=Configuration.getMapper(MemberDAO.class);
		pwd=dao.pwdSearch(id);
		
		return pwd;
	}
	@Override
	public String emailSearch(String id) {
		String email = null;
		MemberDAO dao=Configuration.getMapper(MemberDAO.class);
		email=dao.emailSearch(id);
		
		return email;
	}
}
