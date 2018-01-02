package bmsProject.mvc.Spring.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import bmsProject.mvc.Spring.persistance.BookDAO;
import bmsProject.mvc.Spring.persistance.BookDAOImpl;
import bmsProject.mvc.Spring.persistance.HostDAO;
import bmsProject.mvc.Spring.persistance.HostDAOImpl;
import bmsProject.mvc.Spring.persistance.MemberDAO;
import bmsProject.mvc.Spring.persistance.MemberDAOImpl;
import bmsProject.mvc.Spring.vo.MemberVo;
import bmsProject.mvc.Spring.vo.bookListVo;
import bmsProject.mvc.Spring.vo.cartVo;
import bmsProject.mvc.Spring.vo.orderListVo;

@Service
public class BookServiceImpl implements BookService{

	@Override
	public void bookList(HttpServletRequest req, Model model) {
		int pageSize  = 10;	//한 페이지당 출력한 글 갯수
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
		cnt=dao.getBookListCnt();
		System.out.println("cnt:"+cnt+"====================================");
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
			Map map =new HashMap();
			map.put("start", start);
			map.put("end", end);
			ArrayList<bookListVo> dtos=dao.getBookList(map);
			req.setAttribute("dtos", dtos);
			
		}
		
		startPage=(currentPage/pageBlock)*pageBlock+1; //시작페이지=(현재 페이지/한 블럭당 페이지 갯수)*한 블럭당 페이지 갯수+1;
		if(currentPage % pageBlock ==0)startPage -= pageBlock; //(현재페이지%한 블럭당 페이지 갯수==0)
		
		endPage=startPage+pageBlock-1; //마지막페이지=시작페이지+한 블럭당 페이지 갯수-1;
		if(endPage > pageCount)endPage = pageCount; 
		req.setAttribute("start", start);
		req.setAttribute("end", end);
		req.setAttribute("cnt", cnt); //글갯수
		req.setAttribute("number", number);//글번호
		req.setAttribute("pageNum", pageNum); //페이지번호

		if(cnt>0) {
			req.setAttribute("startPage", startPage); //시작 페이지
			req.setAttribute("endPage", endPage); //마지막 페이지
			req.setAttribute("pageBlock", pageBlock); //출력할 페이지 갯수
			req.setAttribute("pageCount", pageCount); //페이지 갯수
			req.setAttribute("currentPage",currentPage);//현재 페이지
		}
	}

	@Override
	public void serchBook(HttpServletRequest req, Model model) {
		String image=req.getParameter("image");
		BookDAO dao=new BookDAOImpl();
		
		bookListVo dto=dao.serchBook(image);
		
		req.setAttribute("dto", dto);
	}

	@Override
	public void DirectBy(HttpServletRequest req, Model model) {
		int code=Integer.parseInt(req.getParameter("code"));//책의 구매 코드
		int amount=Integer.parseInt(req.getParameter("amount"));//구매한 수량
		int price=Integer.parseInt(req.getParameter("price"));//합계 총 구매가격
		String title=req.getParameter("title");
		System.out.println("title:"+title);
				
		String id = (String) req.getSession().getAttribute("memId");
		String ip = req.getRemoteAddr();
		
		int DirectByCnt=0;
		
		BookDAO dao=new BookDAOImpl();

		orderListVo dto=new orderListVo();
		
		if(id==null) { //로그인하지 않은 상태
			String name=req.getParameter("name");
			dto.setName(name);
			
			String hp1=req.getParameter("hp1");
			String hp2=req.getParameter("hp2");
			String hp3=req.getParameter("hp3");
			String hp=hp1+"-"+hp2+"-"+hp3;
			dto.setHp(hp);
			
			String email1=req.getParameter("email1");
			String email2=req.getParameter("email2");
			String email=email1+"@"+email2;
			dto.setEmail(email);
			
			String postNum=req.getParameter("postNum");
			String addr1=req.getParameter("addr1");
			String addr2=req.getParameter("addr2");
			String addr="["+postNum+"]"+addr1+" "+addr2;
			dto.setAddr(addr);
			
			dto.setAmount(amount);
			dto.setPrice(price);
			dto.setTitle(title);
			dto.setId(ip);
			/*
			 	dto.setNum(num);//시퀀스로 입력
				dto.setPublisher(publisher);//구매한 현재시간을 입력(sysdate)
			*/

			DirectByCnt=dao.DeliveryInsert(dto);

		}else { //로그인한 상태
			//member정보를 가져와서 order정보에 저장할 값을 가져온다.
			Map map =new HashMap();
			map.put("amount", amount);
			map.put("price", price);
			map.put("title", title);
			map.put("id", id);
			orderListVo MDto=dao.memberSerch(map);

			DirectByCnt=dao.DeliveryInsert(MDto);
		}
		//재고수량 - 구매수량
		int Camount=dao.bookAmount(code)-amount;		
		//재고목록에 수량수정(update)
		MemberDAO dao1=MemberDAOImpl.getInstance();
		//재고목록에 수량수정(update)
		Map map=new HashMap();
		map.put("code", code);
		map.put("amount", Camount);
		dao1.BookUpdate(map);
		
		req.setAttribute("DirectByCnt", DirectByCnt);
	}

}
