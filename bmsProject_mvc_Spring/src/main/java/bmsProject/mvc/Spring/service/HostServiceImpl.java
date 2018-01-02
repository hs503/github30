package bmsProject.mvc.Spring.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import bmsProject.mvc.Spring.persistance.HostDAO;
import bmsProject.mvc.Spring.persistance.HostDAOImpl;
import bmsProject.mvc.Spring.persistance.MemberDAO;
import bmsProject.mvc.Spring.persistance.MemberDAOImpl;
import bmsProject.mvc.Spring.vo.DeliveryVo;
import bmsProject.mvc.Spring.vo.MemberVo;
import bmsProject.mvc.Spring.vo.bookListVo;
import bmsProject.mvc.Spring.vo.closingVo;
import bmsProject.mvc.Spring.vo.orderListVo;

@Service
public class HostServiceImpl implements HostService{

	@Override
	public void Hmember(HttpServletRequest req, Model model) {
		int pageSize  = 5;	//한 페이지당 출력한 글 갯수
		int pageBlock = 3;	//한 블럭당 페이지 갯수
		
		int cnt   = 0; 			//글갯수
		int start = 0; 			//현재 페이지 글시작 번호
		int end   = 0;			//현재 페이지 글마지막 번호
		int number = 0;			//출력할 글번호
		String pageNum = null;	//페이지번호
		int currentPage = 0;	//현재 페이지
		
		int pageCount = 0;		//페이지 갯수
		int startPage = 0;		//시작 페이지
		int endPage = 0;		//마지막 페이지
		
		int Hmember=0;
		//dao 객체생성(싱글톤, 다형성)
		HostDAO dao=new HostDAOImpl();
		
		//글갯수 구하기		
		cnt=dao.getMemberCnt();
		
		pageNum=req.getParameter("pageNum");
		
		if(pageNum == null) {
			pageNum="1"; //첫페이지를 1페이지로 설정
		}
		
		currentPage =Integer.parseInt(pageNum);//현재 페이지
		//pageCnt = 12/5+1; //나머지 2건이 1페이지로 할당되므로 3페이지
		pageCount = (cnt/pageSize)+(cnt%pageSize>0?1:0);//페이지 갯수=(글갯수/페이지당 글갯수)+(나머지가 있으면1 아니면0)
		
		//현재 페이지 1=(1-1)*5+1
		//6=(2-1)*5+1
		//11=(3-1)*5+1
		//21=(5-1)*5+1
		
		start = (currentPage -1 )*pageSize +1;//현재 페이지 시작번호
	
		//5=1+5-1;
		
		end = start + pageSize -1; //현재 페이지 끝번호
	
		System.out.println("start:" + start);
		System.out.println("end:" + end);
		
		if(end>cnt)end=cnt;
		
		// 21 - (5-1)*5;
		number=cnt-(currentPage -1)*pageSize; //출력할 글번호.. 최신글(큰페이지)출력할 글번호
		System.out.println("number:" + number);
		System.out.println("cnt:" + cnt);
		System.out.println("currentPage:" + currentPage);
		System.out.println("pageSize:" + pageSize);
		
		if(cnt>0) {
			//게시글 목록 조회
			Map<String, Integer> map=new HashMap<String, Integer>();
			map.put("start", start);
			map.put("end", end);
			
			ArrayList<MemberVo> dtos=dao.getMemberList(map);
			req.setAttribute("dtos", dtos);
			
		}
		
		startPage=(currentPage/pageBlock)*pageBlock+1; //시작페이지=(현재 페이지/한 블럭당 페이지 갯수)*한 블럭당 페이지 갯수+1;
		if(currentPage % pageBlock ==0)startPage -= pageBlock; //(현재페이지%한 블럭당 페이지 갯수==0)
		
		endPage=startPage+pageBlock-1; //마지막페이지=시작페이지+한 블럭당 페이지 갯수-1;
		if(endPage > pageCount)endPage = pageCount; 
		Hmember=1;
		req.setAttribute("cnt", cnt); //글갯수
		req.setAttribute("number", number);//글번호
		req.setAttribute("pageNum", pageNum); //페이지번호
		req.setAttribute("Hmember", Hmember);
		if(cnt>0) {
			req.setAttribute("startPage", startPage); //시작 페이지
			req.setAttribute("endPage", endPage); //마지막 페이지
			req.setAttribute("pageBlock", pageBlock); //출력할 페이지 갯수
			req.setAttribute("pageCount", pageCount); //페이지 갯수
			req.setAttribute("currentPage",currentPage);//현재 페이지
		}
	}

	//회원삭제
	@Override
	public void deleteMem(HttpServletRequest req, Model model) {
		String id=req.getParameter("id");
		
		HostDAO dao=new HostDAOImpl();
		
		int cnt=dao.deleteMem(id);
		req.setAttribute("cnt", cnt);
	}

	@Override
	public void bookList(HttpServletRequest req, Model model) {
		int pageSize  = 5;	//한 페이지당 출력한 글 갯수
		int pageBlock = 3;	//한 블럭당 페이지 갯수
		
		int cnt   = 0; 			//글갯수
		int start = 0; 			//현재 페이지 글시작 번호
		int end   = 0;			//현재 페이지 글마지막 번호
		int number = 0;			//출력할 글번호
		String pageNum = null;	//페이지번호
		int currentPage = 0;	//현재 페이지
		
		int pageCount = 0;		//페이지 갯수
		int startPage = 0;		//시작 페이지
		int endPage = 0;		//마지막 페이지
		
		int bookList=0;
		//dao 객체생성(싱글톤, 다형성)
		HostDAO dao=new HostDAOImpl();
		
		//글갯수 구하기		
		cnt=dao.getBookListCnt();
		
		pageNum=req.getParameter("pageNum");
		
		if(pageNum == null) {
			pageNum="1"; //첫페이지를 1페이지로 설정
		}

		currentPage =Integer.parseInt(pageNum);//현재 페이지
		//pageCnt = 12/5+1; //나머지 2건이 1페이지로 할당되므로 3페이지
		pageCount = (cnt/pageSize)+(cnt%pageSize>0?1:0);//페이지 갯수=(글갯수/페이지당 글갯수)+(나머지가 있으면1 아니면0)
		
		//현재 페이지 1=(1-1)*5+1
		//6=(2-1)*5+1
		//11=(3-1)*5+1
		//21=(5-1)*5+1
		
		start = (currentPage -1 )*pageSize +1;//현재 페이지 시작번호
	
		//5=1+5-1;
		
		end = start + pageSize -1; //현재 페이지 끝번호
	
		System.out.println("start:" + start);
		System.out.println("end:" + end);
		
		if(end>cnt)end=cnt;
		
		// 21 - (5-1)*5;
		number=cnt-(currentPage -1)*pageSize; //출력할 글번호.. 최신글(큰페이지)출력할 글번호
		System.out.println("number:" + number);
		System.out.println("cnt:" + cnt);
		System.out.println("currentPage:" + currentPage);
		System.out.println("pageSize:" + pageSize);
		
		if(cnt>0) {
			//게시글 목록 조회
			Map<String, Integer> map=new HashMap<String, Integer>();
			map.put("start", start);
			map.put("end", end);
			ArrayList<bookListVo> dtos=dao.getBookList(map);
			req.setAttribute("dtos", dtos);
			
		}
		
		startPage=(currentPage/pageBlock)*pageBlock+1; //시작페이지=(현재 페이지/한 블럭당 페이지 갯수)*한 블럭당 페이지 갯수+1;
		if(currentPage % pageBlock ==0)startPage -= pageBlock; //(현재페이지%한 블럭당 페이지 갯수==0)
		
		endPage=startPage+pageBlock-1; //마지막페이지=시작페이지+한 블럭당 페이지 갯수-1;
		if(endPage > pageCount)endPage = pageCount; 
		bookList=1;
		req.setAttribute("cnt", cnt); //글갯수
		req.setAttribute("number", number);//글번호
		req.setAttribute("pageNum", pageNum); //페이지번호
		req.setAttribute("bookList", bookList);
		if(cnt>0) {
			req.setAttribute("startPage", startPage); //시작 페이지
			req.setAttribute("endPage", endPage); //마지막 페이지
			req.setAttribute("pageBlock", pageBlock); //출력할 페이지 갯수
			req.setAttribute("pageCount", pageCount); //페이지 갯수
			req.setAttribute("currentPage",currentPage);//현재 페이지
		}
	}
	@Override
	public void orderList(HttpServletRequest req, Model model) {
		int pageSize  = 5;	//한 페이지당 출력한 글 갯수
		int pageBlock = 3;	//한 블럭당 페이지 갯수
		
		int cnt   = 0; 			//글갯수
		int start = 0; 			//현재 페이지 글시작 번호
		int end   = 0;			//현재 페이지 글마지막 번호
		int number = 0;			//출력할 글번호
		String pageNum = null;	//페이지번호
		int currentPage = 0;	//현재 페이지
		
		int pageCount = 0;		//페이지 갯수
		int startPage = 0;		//시작 페이지
		int endPage = 0;		//마지막 페이지
		
		int orderList=0;
		//dao 객체생성(싱글톤, 다형성)
		HostDAO dao=new HostDAOImpl();
		
		//글갯수 구하기		
		cnt=dao.getOrderListCnt();
		
		pageNum=req.getParameter("pageNum");
		
		if(pageNum == null) {
			pageNum="1"; //첫페이지를 1페이지로 설정
		}

		currentPage =Integer.parseInt(pageNum);//현재 페이지
		//pageCnt = 12/5+1; //나머지 2건이 1페이지로 할당되므로 3페이지
		pageCount = (cnt/pageSize)+(cnt%pageSize>0?1:0);//페이지 갯수=(글갯수/페이지당 글갯수)+(나머지가 있으면1 아니면0)
		
		//현재 페이지 1=(1-1)*5+1
		//6=(2-1)*5+1
		//11=(3-1)*5+1
		//21=(5-1)*5+1
		
		start = (currentPage -1 )*pageSize +1;//현재 페이지 시작번호
	
		//5=1+5-1;
		
		end = start + pageSize -1; //현재 페이지 끝번호
	
		System.out.println("start:" + start);
		System.out.println("end:" + end);
		
		if(end>cnt)end=cnt;
		
		// 21 - (5-1)*5;
		number=cnt-(currentPage -1)*pageSize; //출력할 글번호.. 최신글(큰페이지)출력할 글번호
		System.out.println("number:" + number);
		System.out.println("cnt:" + cnt);
		System.out.println("currentPage:" + currentPage);
		System.out.println("pageSize:" + pageSize);
		
		if(cnt>0) {
			//게시글 목록 조회
			Map<String,Integer> map =new HashMap<String,Integer>();
			map.put("start", start);
			map.put("end", end);
			
			ArrayList<orderListVo> dtos=dao.getOrderList(map);

			req.setAttribute("dtos", dtos);
			
		}
		
		startPage=(currentPage/pageBlock)*pageBlock+1; //시작페이지=(현재 페이지/한 블럭당 페이지 갯수)*한 블럭당 페이지 갯수+1;
		if(currentPage % pageBlock ==0)startPage -= pageBlock; //(현재페이지%한 블럭당 페이지 갯수==0)
		
		endPage=startPage+pageBlock-1; //마지막페이지=시작페이지+한 블럭당 페이지 갯수-1;
		if(endPage > pageCount)endPage = pageCount; 
		orderList=1;
		req.setAttribute("cnt", cnt); //글갯수
		req.setAttribute("number", number);//글번호
		req.setAttribute("pageNum", pageNum); //페이지번호
		req.setAttribute("orderList", orderList);
		if(cnt>0) {
			req.setAttribute("startPage", startPage); //시작 페이지
			req.setAttribute("endPage", endPage); //마지막 페이지
			req.setAttribute("pageBlock", pageBlock); //출력할 페이지 갯수
			req.setAttribute("pageCount", pageCount); //페이지 갯수
			req.setAttribute("currentPage",currentPage);//현재 페이지
		}
		
	}
	@Override
	public void delivaryList(HttpServletRequest req, Model model) {
		int pageSize  = 5;	//한 페이지당 출력한 글 갯수
		int pageBlock = 3;	//한 블럭당 페이지 갯수
		
		int cnt   = 0; 			//글갯수
		int start = 0; 			//현재 페이지 글시작 번호
		int end   = 0;			//현재 페이지 글마지막 번호
		int number = 0;			//출력할 글번호
		String pageNum = null;	//페이지번호
		int currentPage = 0;	//현재 페이지
		
		int pageCount = 0;		//페이지 갯수
		int startPage = 0;		//시작 페이지
		int endPage = 0;		//마지막 페이지
		

		//dao 객체생성(싱글톤, 다형성)
		HostDAO dao=new HostDAOImpl();
		
		//글갯수 구하기		
		cnt=dao.getdelivaryListCnt();
		
		pageNum=req.getParameter("pageNum1");
		
		if(pageNum == null) {
			pageNum="1"; //첫페이지를 1페이지로 설정
		}

		currentPage =Integer.parseInt(pageNum);//현재 페이지
		//pageCnt = 12/5+1; //나머지 2건이 1페이지로 할당되므로 3페이지
		pageCount = (cnt/pageSize)+(cnt%pageSize>0?1:0);//페이지 갯수=(글갯수/페이지당 글갯수)+(나머지가 있으면1 아니면0)
		
		//현재 페이지 1=(1-1)*5+1
		//6=(2-1)*5+1
		//11=(3-1)*5+1
		//21=(5-1)*5+1
		
		start = (currentPage -1 )*pageSize +1;//현재 페이지 시작번호
	
		//5=1+5-1;
		
		end = start + pageSize -1; //현재 페이지 끝번호
	
		System.out.println("start:" + start);
		System.out.println("end:" + end);
		
		if(end>cnt)end=cnt;
		
		// 21 - (5-1)*5;
		number=cnt-(currentPage -1)*pageSize; //출력할 글번호.. 최신글(큰페이지)출력할 글번호
		System.out.println("number:" + number);
		System.out.println("cnt:" + cnt);
		System.out.println("currentPage:" + currentPage);
		System.out.println("pageSize:" + pageSize);
		
		if(cnt>0) {
			//게시글 목록 조회
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("start", start);
			map.put("end", end);
			
			ArrayList<DeliveryVo> dtos=dao.getdeliveryList(map);
			
			req.setAttribute("dtos1", dtos);
			
		}
		
		startPage=(currentPage/pageBlock)*pageBlock+1; //시작페이지=(현재 페이지/한 블럭당 페이지 갯수)*한 블럭당 페이지 갯수+1;
		if(currentPage % pageBlock ==0)startPage -= pageBlock; //(현재페이지%한 블럭당 페이지 갯수==0)
		
		endPage=startPage+pageBlock-1; //마지막페이지=시작페이지+한 블럭당 페이지 갯수-1;
		if(endPage > pageCount)endPage = pageCount; 

		req.setAttribute("cnt1", cnt); //글갯수
		req.setAttribute("number1", number);//글번호
		req.setAttribute("pageNum1", pageNum); //페이지번호
		if(cnt>0) {
			req.setAttribute("startPage1", startPage); //시작 페이지
			req.setAttribute("endPage1", endPage); //마지막 페이지
			req.setAttribute("pageBlock1", pageBlock); //출력할 페이지 갯수
			req.setAttribute("pageCount1", pageCount); //페이지 갯수
			req.setAttribute("currentPage1",currentPage);//현재 페이지
		}
		
	}
	
	@Override
	public void insertBook(HttpServletRequest req, Model model) {
		// MultipartRequest 타입의 변수 선언
		MultipartRequest mr = null;
		// 업로드할 파일의 최대 사이즈(10 * 1024 * 1024 = 10MB)
		int maxSize = 10 * 1024 * 1024;
		// 임시 파일이 저장되는 논리적인 경로
		String saveDir = req.getRealPath("/saveImg/");
		// 업로드할 파일이 위치하게될 물리적인 경로
		String realDir = "C:\\Dev2\\workspace\\bmsProject_mvc_Spring\\src\\main\\webapp\\resources\\saveImg\\";
		// 인코딩 타입 : 한글 파일명이 열화되는 것을 방지함
		String encType = "UTF-8";
		try {
		
		/** DefaultFileRenamePolicy() 객체는 중복된 파일명이 있을 경우, 자동으로 파일명을 변경함
		* (예 : filename.png 가 이미 존재할 경우, filename1.png 과 같이)*/
		
		mr = new MultipartRequest(req, saveDir, maxSize, encType, new DefaultFileRenamePolicy());
		FileInputStream fis = new FileInputStream(saveDir + mr.getFilesystemName("image"));
		FileOutputStream fos = new FileOutputStream(realDir + mr.getFilesystemName("image"));
		int data = 0;
		// 논리적인 경로에 저장된 임시 파일을 물리적인 경로로 복사함
		while((data = fis.read()) != -1) {
		fos.write(data);
		}
		fis.close();
		fos.close();
		
		/** 위에서 MultipartRequest() 객체를 선언해서 받는 모든 request 객체들은
		* MultipartRequest 타입으로 참조돼야함
		* (예 : request.getParameter 에서 mr.getParameter)*/
		
		bookListVo dto = new bookListVo();

		dto.setCode(Integer.parseInt(mr.getParameter("code")));
		dto.setImage(mr.getFilesystemName("image"));
		dto.setTitle(mr.getParameter("title"));
		dto.setAuthor(mr.getParameter("author"));
		dto.setPrice(Integer.parseInt(mr.getParameter("price")));
		dto.setAmount(Integer.parseInt(mr.getParameter("amount")));
		dto.setBookChat(mr.getParameter("bookChat"));
		
		HostDAO dao=new HostDAOImpl();
		int cnt = dao.insertBook(dto);
		
		req.setAttribute("cnt", cnt);
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteBook(HttpServletRequest req, Model model) {
		int code=Integer.parseInt(req.getParameter("code"));
		String image=req.getParameter("image");
		HostDAO dao=new HostDAOImpl();
		
		int cnt=dao.deleteBook(code);
		if(cnt!=0) {
			File file= new File("C:\\Dev2\\workspace\\bmsProject_mvc_Spring\\src\\main\\webapp\\resources\\saveImg\\"+image);
			if(file.exists()) {
				if(file.delete()) {
					System.out.println("파일삭제 성공");
				}else{
	                System.out.println("파일삭제 실패");
	            }
	        }else{
	            System.out.println("파일이 존재하지 않습니다.");
	        }
		}
	}

	@Override
	public void updateBook(HttpServletRequest req, Model model) {
		// MultipartRequest 타입의 변수 선언
		MultipartRequest mr = null;
		// 업로드할 파일의 최대 사이즈(10 * 1024 * 1024 = 10MB)
		int maxSize = 10 * 1024 * 1024;
		// 임시 파일이 저장되는 논리적인 경로
		String saveDir = req.getRealPath("/saveImg/");
		// 업로드할 파일이 위치하게될 물리적인 경로
		String realDir = "C:\\Dev2\\workspace\\bmsProject_mvc_Spring\\src\\main\\webapp\\resources\\saveImg\\";
		// 인코딩 타입 : 한글 파일명이 열화되는 것을 방지함
		String encType = "UTF-8";
		try {
		
		/** DefaultFileRenamePolicy() 객체는 중복된 파일명이 있을 경우, 자동으로 파일명을 변경함
		* (예 : filename.png 가 이미 존재할 경우, filename1.png 과 같이)*/
		
		mr = new MultipartRequest(req, saveDir, maxSize, encType, new DefaultFileRenamePolicy());
		FileInputStream fis = new FileInputStream(saveDir + mr.getFilesystemName("image"));
		FileOutputStream fos = new FileOutputStream(realDir + mr.getFilesystemName("image"));
		int data = 0;
		// 논리적인 경로에 저장된 임시 파일을 물리적인 경로로 복사함
		while((data = fis.read()) != -1) {
		fos.write(data);
		}
		fis.close();
		fos.close();
		
		/** 위에서 MultipartRequest() 객체를 선언해서 받는 모든 request 객체들은
		* MultipartRequest 타입으로 참조돼야함
		* (예 : request.getParameter 에서 mr.getParameter)*/
		
		bookListVo dto = new bookListVo();
		
		dto.setCode(Integer.parseInt(mr.getParameter("code")));
		dto.setImage(mr.getFilesystemName("image"));
		dto.setTitle(mr.getParameter("title"));
		dto.setAuthor(mr.getParameter("author"));
		dto.setPrice(Integer.parseInt(mr.getParameter("price")));
		dto.setAmount(Integer.parseInt(mr.getParameter("amount")));
		
		HostDAO dao=new HostDAOImpl();
		dao.updateBook(dto);
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delivery(HttpServletRequest req, Model model) {
		int num=Integer.parseInt(req.getParameter("num"));
		int price=Integer.parseInt(req.getParameter("price"));
		int amount=Integer.parseInt(req.getParameter("amount"));
		String title=req.getParameter("title");
		
		int delivary=0;
		String bookChat;
		HostDAO dao=new HostDAOImpl();
		/*
		orderListVo dto=dao.odSelect(num);
		
		delivary=dao.insertDeli(dto);*/
		//delevary 테이블의 환불여부(refund)변경 '배송대기중' -> '판매'
		dao.delUpdate(num);
		//1. 구매한 책의 판매수량 증가후 그 책의 차트를 리턴
		Map map=new HashMap();
		map.put("amount", amount);
		map.put("title", title);
		bookChat=dao.sellCnt(map);
		//2. 가져온 차트를 closing테이블에서 맞는 차트의 판매갯수 증가.+결산금액 더하기
		Map map2=new HashMap();
		map2.put("price", price);
		map2.put("bookChat", bookChat);
		map2.put("amount", amount);
		dao.totalSale(map2);
		//3. 배송한 책을 주문목록에서 삭제
		dao.orderDel(num);
		
		req.setAttribute("delivary", delivary);
	}

	@Override
	public void hostMain(HttpServletRequest req, Model model) {
		HostDAO dao=new HostDAOImpl();
		//재고목록에서 재고가 0인것의 갯수 구하기
		int nullAmountCnt=dao.bookAmountSerch();
		//closing 테이블의 soltOutCnt에 품절갯수 저장
		dao.updateClosing(nullAmountCnt);
		//delivery테이블에서 refund가 '환불신청'인 갯수 구하기
		int refundCnt=dao.selectRefundCnt();
		//closing 테이블의 refundCnt값 저장
		dao.updateClosingRefund(refundCnt);
		//closing테이블에서 값을 가져오기 dto
		closingVo dto =new closingVo();
		
		dto=dao.closingSerch(dto);
		
		req.setAttribute("dto", dto);
		
	}

	@Override
	public void refund(HttpServletRequest req, Model model) {
		int price=Integer.parseInt(req.getParameter("price"));
		String title=req.getParameter("title");
		int num=Integer.parseInt(req.getParameter("num"));
		String refund="환불";
		int amount=Integer.parseInt(req.getParameter("amount"));
		
		HostDAO dao=new HostDAOImpl();
		MemberDAO dao1=MemberDAOImpl.getInstance();
		//bookList테이블에서 bookChat구하기
		String bookChat=dao.serchChat(title);
		//bookList테이블의 sellCnt를 환불한 만큼 빼주기
		Map map=new HashMap();
		map.put("amount", amount);
		map.put("title", title);
		dao.subSellCnt(map);
		//closing테이블에서 환불갯수만큼 chat구매수량빼기
		Map map2=new HashMap();
		map2.put("bookChat", bookChat);
		map2.put("amount", amount);
		dao.subChat(map2);
		//총판매금액 - 하기
		dao.subTotalSale(price);
		//closing테이블의 refundCnt -1하기
		dao.subRefundCnt();
	
		//재고목록에 수량 +
		dao.bookRefund(map);
		//delivary테이블의 refund '환불'로 변경
		Map map3=new HashMap();
		map3.put("num", num);
		map3.put("refund", refund);
		
		int refundCnt=dao1.refundUpdate(map3);
		
		
		req.setAttribute("refundCnt", refundCnt);
	}

	@Override
	public void deleteDel(HttpServletRequest req, Model model) {
		int num=Integer.parseInt(req.getParameter("num"));
		HostDAO dao=new HostDAOImpl();
		
		int del=dao.deleteDel(num);
		
		req.setAttribute("del", del);
	}
}
