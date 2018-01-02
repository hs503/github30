package bmsProject.mvc.Spring.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import bmsProject.mvc.Spring.vo.closingVo;
import bmsProject.mvc.Spring.vo.orderListVo;

@Repository
public class HostDAOImpl implements HostDAO{
	
	@Override
	public int getMemberCnt() {
		int cnt = 0;
		
		HostDAO dao=Configuration.getMapper(HostDAO.class);
		cnt=dao.getMemberCnt();
		
		return cnt;
	}
	@Override
	public ArrayList<MemberVo> getMemberList(Map<String, Integer> map) {
		ArrayList<MemberVo> dtos=null; //큰 바구니
		
		HostDAO dao=Configuration.getMapper(HostDAO.class);
		dtos=dao.getMemberList(map);
		
		return dtos;
	}

	@Override
	public int getBookListCnt() {
		int cnt = 0;
		
		HostDAO dao=Configuration.getMapper(HostDAO.class);
		cnt=dao.getBookListCnt();
		
		return cnt;
	}
	@Override
	public int getdelivaryListCnt() {
		int cnt = 0;
		
		HostDAO dao=Configuration.getMapper(HostDAO.class);
		cnt=dao.getdelivaryListCnt();
		
		return cnt;
	}
	
	@Override
	public int getOrderListCnt() {
		int cnt = 0;
		
		HostDAO dao=Configuration.getMapper(HostDAO.class);
		cnt=dao.getOrderListCnt();
		
		return cnt;
	}
	@Override
	public ArrayList<DeliveryVo> getdeliveryList(Map<String, Integer> map) {
		ArrayList<DeliveryVo> dtos=null; //큰 바구니
		
		HostDAO dao=Configuration.getMapper(HostDAO.class);
		dtos=dao.getdeliveryList(map);
		
		//5. 큰바구니(ArrayList)를 넘긴다.
		return dtos;
	}
	
	@Override
	public ArrayList<orderListVo> getOrderList(Map<String,Integer> map) {
		ArrayList<orderListVo> dtos=null; //큰 바구니
		
		HostDAO dao=Configuration.getMapper(HostDAO.class);
		dtos=dao.getOrderList(map);
		
		//5. 큰바구니(ArrayList)를 넘긴다.
		return dtos;
	}
	@Override
	public ArrayList<bookListVo> getBookList(Map<String,Integer> map) {
		ArrayList<bookListVo> dtos=null; //큰 바구니
		
		HostDAO dao=Configuration.getMapper(HostDAO.class);
		dtos=dao.getBookList(map);
		
		//5. 큰바구니(ArrayList)를 넘긴다.
		return dtos;
	}

	@Override
	public int insertBook(bookListVo dto) {
		int cnt = 0;
		
		HostDAO dao=Configuration.getMapper(HostDAO.class);
		cnt=dao.insertBook(dto);
		
		return cnt;
	}

	@Override
	public int deleteBook(int code) {
		int cnt=0;
		HostDAO dao=Configuration.getMapper(HostDAO.class);
		cnt=dao.deleteBook(code);
		
		return cnt;
	}

	@Override
	public void updateBook(bookListVo dto) {
		HostDAO dao=Configuration.getMapper(HostDAO.class);
		dao.updateBook(dto);
	}

	@Override
	public int deleteMem(String id) {
		int cnt=0;
		HostDAO dao=Configuration.getMapper(HostDAO.class);
		cnt=dao.deleteMem(id);
	
		return cnt;
	}

	@Override
	public orderListVo odSelect(int num) {
		orderListVo dto = new orderListVo();
		
		HostDAO dao=Configuration.getMapper(HostDAO.class);
		dto=dao.odSelect(num);
		
		return dto;
	}

	@Override
	public int insertDeli(orderListVo dto) {
		int cnt = 0;
		HostDAO dao=Configuration.getMapper(HostDAO.class);
		cnt=dao.insertDeli(dto);
		return cnt;
	}

	public void sellCntUpdate(Map map) {
		HostDAO dao=Configuration.getMapper(HostDAO.class);
		dao.sellCntUpdate(map);
	}
	
	@Override
	public String sellCnt(Map map) {
		
		HostDAO dao=Configuration.getMapper(HostDAO.class);
		String bookChat=dao.sellCnt(map);
		
		dao.sellCntUpdate(map);
		
		return bookChat;
	}

	@Override
	public void totalSale(Map map2) {
		//2. 가져온 차트를 closing테이블에서 맞는 차트의 판매갯수 증가.+결산금액 더하기
		HostDAO dao=Configuration.getMapper(HostDAO.class);
		dao.totalSale(map2);
	}

	@Override
	public void orderDel(int num) {
		HostDAO dao=Configuration.getMapper(HostDAO.class);
		dao.orderDel(num);
		
	}

	@Override
	public closingVo closingSerch(closingVo dto) {
		HostDAO dao=Configuration.getMapper(HostDAO.class);
		dto=dao.closingSerch(dto);
		
		return dto;
	}

	@Override
	public int bookAmountSerch() {
		int nullAmountCnt = 0;
		HostDAO dao=Configuration.getMapper(HostDAO.class);
		nullAmountCnt=dao.bookAmountSerch();
		
		return nullAmountCnt;
	}

	@Override
	public void updateClosing(int nullAmountCnt) {
		HostDAO dao=Configuration.getMapper(HostDAO.class);
		dao.updateClosing(nullAmountCnt);
	}

	@Override
	public int selectRefundCnt() {
		int RefundCnt = 0;
		
		HostDAO dao=Configuration.getMapper(HostDAO.class);
		RefundCnt=dao.selectRefundCnt();
		
		return RefundCnt;
	}

	@Override
	public void updateClosingRefund(int refundCnt) {
		HostDAO dao=Configuration.getMapper(HostDAO.class);
		dao.updateClosingRefund(refundCnt);		
	}

	@Override
	public void subTotalSale(int price) {
		HostDAO dao=Configuration.getMapper(HostDAO.class);
		dao.subTotalSale(price);		
	}

	@Override
	public void subRefundCnt() {
		HostDAO dao=Configuration.getMapper(HostDAO.class);
		dao.subRefundCnt();
	}

	@Override
	public void bookRefund(Map map) {
		HostDAO dao=Configuration.getMapper(HostDAO.class);
		dao.bookRefund(map);	
	}

	@Override
	public int deleteDel(int num) {
		
		int del=0;
		HostDAO dao=Configuration.getMapper(HostDAO.class);
		del=dao.deleteDel(num);
		
		return del;
	}

	@Override
	public String serchChat(String title) {
		String chat = "";
		
		HostDAO dao=Configuration.getMapper(HostDAO.class);
		chat=dao.serchChat(title);
		
		return chat;
	}

	@Override
	public void subChat(Map map2) {
		HostDAO dao=Configuration.getMapper(HostDAO.class);
		dao.subChat(map2);
	}

	@Override
	public void subSellCnt(Map map) {
		HostDAO dao=Configuration.getMapper(HostDAO.class);
		dao.subSellCnt(map);		
	}

	@Override
	public void delUpdate(int num) {
		HostDAO dao=Configuration.getMapper(HostDAO.class);
		dao.delUpdate(num);		
	}
}
