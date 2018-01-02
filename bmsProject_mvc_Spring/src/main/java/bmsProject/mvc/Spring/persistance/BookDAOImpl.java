package bmsProject.mvc.Spring.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import bmsProject.mvc.Spring.config.Configuration;
import bmsProject.mvc.Spring.vo.MemberVo;
import bmsProject.mvc.Spring.vo.bookListVo;
import bmsProject.mvc.Spring.vo.orderListVo;

@Repository
public class BookDAOImpl implements BookDAO{

	@Override
	public bookListVo serchBook(String image) {
		bookListVo dto=new bookListVo();
		BookDAO dao=Configuration.getMapper(BookDAO.class);
		dto=dao.serchBook(image);
		
		return dto;
	}

	@Override
	public orderListVo memberSerch(Map map) {
		orderListVo dto=new orderListVo();
		BookDAO dao=Configuration.getMapper(BookDAO.class);
		dto=dao.memberSerch(map);

		return dto;
	}

	@Override
	public int bookAmount(int code) {
		int amount=0;
		BookDAO dao=Configuration.getMapper(BookDAO.class);
		amount=dao.bookAmount(code);
	
		return amount;
	}

	@Override
	public int DeliveryInsert(orderListVo dto) {
		
		int cnt=0;
		BookDAO dao=Configuration.getMapper(BookDAO.class);
		cnt=dao.DeliveryInsert(dto);
		
		return cnt;
	}
}
