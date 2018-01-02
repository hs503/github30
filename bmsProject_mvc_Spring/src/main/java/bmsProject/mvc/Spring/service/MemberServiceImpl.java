package bmsProject.mvc.Spring.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import bmsProject.mvc.Spring.persistance.BookDAO;
import bmsProject.mvc.Spring.persistance.BookDAOImpl;
import bmsProject.mvc.Spring.persistance.MemberDAO;
import bmsProject.mvc.Spring.persistance.MemberDAOImpl;
import bmsProject.mvc.Spring.service.MemberServiceImpl.MyAuthentication;
import bmsProject.mvc.Spring.vo.MemberVo;
import bmsProject.mvc.Spring.vo.bookListVo;
import bmsProject.mvc.Spring.vo.buyListVo;
import bmsProject.mvc.Spring.vo.cartVo;
import bmsProject.mvc.Spring.vo.orderListVo;
@Service
public class MemberServiceImpl implements MemberService {

	// 중복확인 처리
		@Override
		public void confirmId(HttpServletRequest req, Model model) {
			// 3단계. 화면으로부터 입력받은 값을 받아온다.
			String strId = req.getParameter("id");
			
			// 4단계. 다형성 적용, 싱글톤 방식으로 객체 생성
			MemberDAO dao = MemberDAOImpl.getInstance();
				
			// 5단계. 중복된 id가 있는 지 확인
			int cnt = dao.idCheck(strId);
			
			// 6단계. request나 session에 처리 결과를 저장	
			req.setAttribute("cnt", cnt);
			req.setAttribute("id", strId);
			
		}
	
	@Override
	public void inputPro(HttpServletRequest req, Model model) {
		MemberVo vo=new MemberVo();
		
	    /*id varchar2(10) primary key,
	    pwd varchar2(20),
	    name varchar2(10),
	    gender varchar2(2),
	    birth number(6),
	    email varchar2(20),
	    hp varchar2(13)
	    addr */
	
		vo.setId(req.getParameter("id"));
		vo.setPwd(req.getParameter("pwd"));
		vo.setName(req.getParameter("name"));
		
		if(req.getParameter("gender").equals("1")) {
			vo.setGender("mail");
		}else {
			vo.setGender("femail");
		}
		
		if(req.getParameter("birth1").equals("1")) {
			vo.setBirth(req.getParameter("birth")+"+");
		}else {
			vo.setBirth(req.getParameter("birth")+"-");
		}
		
		String email="";
		String email1=req.getParameter("email1");
		String email2=req.getParameter("email2");
		String email3=req.getParameter("email3");
		
		if(email3.equals("0")) {
			email=email1+"@"+email2;
		}else {
			email=email1+"@"+email3;
		}
		vo.setEmail(email);
		
		String hp="";
		String hp1 = req.getParameter("hp1");
		String hp2 = req.getParameter("hp2");
		String hp3 = req.getParameter("hp3");
		
		hp = hp1 + "-" + hp2 + "-" + hp3;
		vo.setHp(hp);
		
		String postNum=req.getParameter("postNum");
		String addr1=req.getParameter("addr1");
		String addr2=req.getParameter("addr2");
		String addr="["+postNum+"]"+addr1+" "+addr2;
		vo.setAddr(addr);
		
		MemberDAO dao = MemberDAOImpl.getInstance();
		
		int cnt = dao.insertMember(vo);
		String name=req.getParameter("name");
		// 5단계. request나 session에 처리 결과를 저장하면 jsp에서 받는다.
		req.setAttribute("cnt", cnt);
		req.setAttribute("name", name);

				
	}

	@Override
	public void loginPro(HttpServletRequest req, Model model) {
		// 1단계. 화면으로부터 아이디, 패스워드 받아온다.
				String strId = req.getParameter("id");
				String strPwd = req.getParameter("pwd");
				
				// 2-1단계. dao 객체생성(싱글톤, 다형성 적용)
				MemberDAO dao = MemberDAOImpl.getInstance();
				
				// 2-2단계. 모델을 사용하여 요청한 기능을 수행한다.
				Map map = new HashMap();
				map.put("strId", strId);
				map.put("strPwd", strPwd);
				int cnt = dao.check(map);
				
				String name=dao.checkName(strId);
				// 3단계. request나 session에 처리 결과를 저장하면 jsp에서 받는다.
				// 로그인 성공(cnt==1)하면 세션id, cnt:1도 넘긴다.
				// 로그인 실패(비밀번호 불일치 cnt:-1 || 아이디가 존재하지 않을 때 cnt:0
				if(cnt == 1) {
					//memId 대소문자 구분
						req.getSession().setAttribute("memId", strId);
						req.getSession().setAttribute("memName", name);
				}
				req.setAttribute("cnt", cnt);
		
	}

	@Override
	public void modifyView(HttpServletRequest req, Model model) {
		// 1단계 . 화면으로 아이디, 패스워드 값을 받아온다.
		String strId = (String) req.getSession().getAttribute("memId");
		String strPwd = req.getParameter("pwd");
		
		// 2-1단계. dao 객체생성(싱글톤, 다형성 적용)
		MemberDAO dao = MemberDAOImpl.getInstance();
		
		// 2-2단계. 모델을 사용하여 요청한 기능을 수행한다.
		/* 
		 * 아이디와 패스워드가 일치하면 (selectCnt == 1) 입력한 회원정보를 읽어온다.
		 * 패스워드가 일치하지 않으면 (selectCnt == -1) | 아이디가 존재하지 않으면 (selectCnt == 0)
		 */	
		Map map=new HashMap();
		map.put("strId", strId);
		map.put("strPwd",strPwd);
		
		int selectCnt = dao.check(map);
		
		// 아이디와 패스워드가 일치하면, 수정하기 위해서, 입력한 정보를 읽어온다.
		if(selectCnt == 1) {
			MemberVo vo = dao.getMemberInfo(strId);
			req.setAttribute("vo", vo);
		}

		System.out.println("selectCnt -->" + selectCnt);
				  
		// 3단계. request나 session에 처리 결과를 저장하면 jsp에서 받는다.
		req.setAttribute("selectCnt", selectCnt);
		
	}

	@Override
	public void modifyPro(HttpServletRequest req, Model model) {
		// 1단계. 모델을 이용해서 요청한 기능을 수행한다.
		// vo 바구니 생성
		MemberVo vo = new MemberVo();	
				
		// 2단계. vo 바구니에 담는다(화면에서 넘어온 값을).
		// id
		String id = (String) req.getSession().getAttribute("memId");
		vo.setId(id);
		vo.setPwd(req.getParameter("pwd"));
		
		// name
		vo.setName(req.getParameter("name"));
		
		
		// hp
		String hp="";
		String hp1 = req.getParameter("hp1");
		String hp2 = req.getParameter("hp2");
		String hp3 = req.getParameter("hp3");
				
		// 필수입력 항목이 아니므로 null 체크없이 무조건 insert하면 null pointer Exception 발생
		hp = hp1 + "-" + hp2 + "-" + hp3;
		vo.setHp(hp);
				
		// email
		String email="";
		String email1 = req.getParameter("email1");
		String email2 = req.getParameter("email2");
		email = email1 + "@" + email2;
		vo.setEmail(email);
		
		String postNum=req.getParameter("postNum");
		String addr1=req.getParameter("addr1");
		String addr2=req.getParameter("addr2");
		String addr="["+postNum+"]"+addr1+" "+addr2;
		vo.setAddr(addr);
		
		// 3단계. 다형성 적용, 싱글톤 방식으로 객체 생성
		MemberDAO dao = MemberDAOImpl.getInstance();
		
		// 4단계. 모델을 사용하여 요청한 기능을 수행한다.
		// 4단계. DAO에서 vo 바구니를 dao 파라미터에 넘겨서 해당 SQL문 호출
		
		int cnt = dao.updateMember(vo);
		System.out.println("cnt : " + cnt);
		
		// 5단계. request나 session에 처리 결과를 저장하면 jsp에서 받는다.
		req.setAttribute("cnt", cnt);
		
	}

	@Override
	public void delete(HttpServletRequest req, Model model) {
		String id = (String) req.getSession().getAttribute("memId");
		
		MemberDAO dao=MemberDAOImpl.getInstance();
		
		dao.delete(id);
	}

	@Override
	public void cartList(HttpServletRequest req, Model model) {
		
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
		Map map = new HashMap();
		//dao 객체생성(싱글톤, 다형성)
		MemberDAO dao=MemberDAOImpl.getInstance();
		String id = (String) req.getSession().getAttribute("memId");
		String ip = req.getRemoteAddr();
		//글갯수 구하기
		if(id==null) {
			cnt=dao.getMemberCnt(ip);
		}else {
			cnt=dao.getMemberCnt(id);
		}
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
			ArrayList<cartVo> dtos=null;
			if(id==null) {
				map.put("start", start);
				map.put("end", end);
				map.put("id", ip);
				dtos=dao.getCartList(map);
			}else {
				map.put("start", start);
				map.put("end", end);
				map.put("id", id);
				dtos=dao.getCartList(map);
			}
			req.setAttribute("dtos", dtos);
			
		}
		
		startPage=(currentPage/pageBlock)*pageBlock+1; //시작페이지=(현재 페이지/한 블럭당 페이지 갯수)*한 블럭당 페이지 갯수+1;
		if(currentPage % pageBlock ==0)startPage -= pageBlock; //(현재페이지%한 블럭당 페이지 갯수==0)
		
		endPage=startPage+pageBlock-1; //마지막페이지=시작페이지+한 블럭당 페이지 갯수-1;
		if(endPage > pageCount)endPage = pageCount; 
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
	public void cartInsert(HttpServletRequest req, Model model) {
		String memId=(String)req.getSession().getAttribute("memId");
		int code=Integer.parseInt(req.getParameter("code"));
		int amount=Integer.parseInt(req.getParameter("amount"));
		Map map =new HashMap();
		String ip=req.getRemoteAddr();
		MemberDAO dao=MemberDAOImpl.getInstance();
		
		bookListVo dto=dao.serchBook(code);
		dto.setAmount(amount);
		
		int cnt1=0;
		if(memId==null) {
			map.put("id", ip);
			map.put("dto", dto);
			cnt1=dao.insertChat(map);
		}else {
			map.put("id", memId);
			map.put("dto", dto);
			cnt1=dao.insertChat(map);
		}
		req.setAttribute("cnt1", cnt1);
		
	}

	@Override
	public void cartDelete(HttpServletRequest req, Model model) {
		int num=Integer.parseInt(req.getParameter("num"));
		
		MemberDAO dao=MemberDAOImpl.getInstance();
		
		int cartCnt=dao.cartDelete(num);
		
		req.setAttribute("cartCnt", cartCnt);
		
	}

	@Override
	public void buyBook(HttpServletRequest req, Model model) {
		int num=Integer.parseInt(req.getParameter("num"));
		int code=Integer.parseInt(req.getParameter("code"));//책의 구매 코드
		int amount=Integer.parseInt(req.getParameter("amount"));//구매한 수량
		int price=Integer.parseInt(req.getParameter("price"));//합계 총 구매가격
		String title=req.getParameter("title");
		System.out.println("title:"+title);
				
		String id = (String) req.getSession().getAttribute("memId");
		String ip = req.getRemoteAddr();
		
		int buyBookCnt=0;
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

			buyBookCnt=dao.DeliveryInsert(dto);

		}else { //로그인한 상태
			//member정보를 가져와서 order정보에 저장할 값을 가져온다.
			Map map = new HashMap();
			map.put("amount", amount);
			map.put("price", price);
			map.put("title", title);
			map.put("id",id);
			orderListVo MDto=dao.memberSerch(map);

			buyBookCnt=dao.DeliveryInsert(MDto);
		}
		//재고수량 - 구매수량
		int Camount=dao.bookAmount(code)-amount;		
		//재고목록에 수량수정(update)
		MemberDAO dao1=MemberDAOImpl.getInstance();
		//재고목록에 수량수정(update)
		Map map2=new HashMap();
		map2.put("code", code);
		map2.put("amount", Camount);
		
		dao1.BookUpdate(map2);
		

		//장바구니 삭제
		MemberDAO dao2=MemberDAOImpl.getInstance();
		dao2.cartDelete(num);
		//insert성공 여부 넘기기
		req.setAttribute("buyBookCnt", buyBookCnt);
		
	}

	@Override
	public void AmountTest(HttpServletRequest req, Model model) {
		int cnt=0;
		int amount=Integer.parseInt(req.getParameter("amount"));
		int code=Integer.parseInt(req.getParameter("code"));
		
		MemberDAO dao=MemberDAOImpl.getInstance();
		int Bamount=dao.bAmount(code);
		System.out.println("Bamount:"+Bamount);
		if(amount>Bamount) {
			cnt=1;
		}
		System.out.println("cnt:"+cnt);
		req.setAttribute("cnt", cnt);
		
	}

	@Override
	public void buyList(HttpServletRequest req, Model model) {
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
		MemberDAO dao=MemberDAOImpl.getInstance();
		String id = (String) req.getSession().getAttribute("memId");
		String ip = req.getRemoteAddr();
		//글갯수 구하기
		if(id==null) {
			cnt=dao.getbuyListCnt(ip);
		}else {
			cnt=dao.getbuyListCnt(id);
		}
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
		Map map=new HashMap();
		if(cnt>0) {
			//게시글 목록 조회
			ArrayList<buyListVo> dtos=null;
			if(id==null) {
				map.put("start", start);
				map.put("end", end);
				map.put("id", ip);
				dtos=dao.getbuyList(map);
			}else {
				map.put("start", start);
				map.put("end", end);
				map.put("id", id);
				dtos=dao.getbuyList(map);
			}
			req.setAttribute("dtos", dtos);
			
		}
		
		startPage=(currentPage/pageBlock)*pageBlock+1; //시작페이지=(현재 페이지/한 블럭당 페이지 갯수)*한 블럭당 페이지 갯수+1;
		if(currentPage % pageBlock ==0)startPage -= pageBlock; //(현재페이지%한 블럭당 페이지 갯수==0)
		
		endPage=startPage+pageBlock-1; //마지막페이지=시작페이지+한 블럭당 페이지 갯수-1;
		if(endPage > pageCount)endPage = pageCount; 
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
	public void refund(HttpServletRequest req, Model model) {
		int num=Integer.parseInt(req.getParameter("num"));
		String refund="환불신청";
		MemberDAO dao=MemberDAOImpl.getInstance();
		//delivary테이블의 refund(환불여부)변경 , 구매->환불신청
		Map map=new HashMap();
		map.put("num", num);
		map.put("refund", refund);
		int refundCnt=dao.refundUpdate(map);
		//closing테이블의 refundCnt 1 증가
		dao.closingRefundUpdate();
		
		req.setAttribute("refundCnt", refundCnt);
	}

	@Override
	public void Confirmation(HttpServletRequest req, Model model) {
		int num=Integer.parseInt(req.getParameter("num"));
		String Confirmation="구매확정";
		MemberDAO dao=MemberDAOImpl.getInstance();
		Map map=new HashMap();
		map.put("num", num);
		map.put("refund", Confirmation);
		int ConfirmationCnt=dao.refundUpdate(map);
		
		req.setAttribute("ConfirmationCnt", ConfirmationCnt);
	}

	@Override
	public void serch(HttpServletRequest req, Model model) {
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
		String serch=req.getParameter("serch");
		
		//dao 객체생성(싱글톤, 다형성)
		MemberDAO dao=new MemberDAOImpl();
		
		//글갯수 구하기		
		String serchLike="%"+serch+"%";
		cnt=dao.getBookListCnt(serchLike);
		
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
			Map map=new HashMap();
			String LikeSerch="%"+serch+"%";
			map.put("start", start);
			map.put("end", end);
			map.put("serch", LikeSerch);
			ArrayList<bookListVo> dtos=dao.getbookList(map);
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
		req.setAttribute("serch", serch);
		if(cnt>0) {
			req.setAttribute("startPage", startPage); //시작 페이지
			req.setAttribute("endPage", endPage); //마지막 페이지
			req.setAttribute("pageBlock", pageBlock); //출력할 페이지 갯수
			req.setAttribute("pageCount", pageCount); //페이지 갯수
			req.setAttribute("currentPage",currentPage);//현재 페이지
		}
		
	}

	@Override
	public void amountChange(HttpServletRequest req, Model model) {
		int amount=Integer.parseInt(req.getParameter("amount"));
		int num=Integer.parseInt(req.getParameter("num"));
		int amountChangeCnt=0;
		MemberDAO dao=MemberDAOImpl.getInstance();
		
		Map map=new HashMap();
		map.put("amount", amount);
		map.put("num", num);
		amountChangeCnt=dao.amountChange(map);
		
		req.setAttribute("amountChangeCnt",amountChangeCnt);
	}

	@Override
	public void confirmEmail(HttpServletRequest req, Model model) {
		String email=req.getParameter("email");
		
		MemberDAO dao=MemberDAOImpl.getInstance();
		
		int emailCheck=dao.confirmEmail(email);
		int result=0;
		if(emailCheck==0) {
			Properties p = System.getProperties();
			p.put("mail.smtp.starttls.enable", "true"); // gmail TRUE 고정
			p.put("mail.smtp.host", "smtp.gmail.com"); // SMTP 서버 주소
			p.put("mail.smtp.auth","true"); // gmail TRUE 고정
			p.put("mail.smtp.port", "587"); // gmail 포트
			// smtp.naver.com SMTP 포트번호 : 465 // naver 포트
				
			Authenticator auth = new MyAuthentication();

			// session / MimeMessage 생성
			Session session = Session.getDefaultInstance(p, auth);
			MimeMessage msg = new MimeMessage(session);

			try{
				// 송신 날짜
				msg.setSentDate(new Date());

				InternetAddress from = new InternetAddress() ;

				from = new InternetAddress("qapl503@gmail.com");

				// 발신자 이메일
				msg.setFrom(from);

				// 수신자 이메일
				InternetAddress to = new InternetAddress(email);
				msg.setRecipient(Message.RecipientType.TO, to);

				Random rnd = new Random();
				result = rnd.nextInt(10000)+1000;
				 
				if(result>10000){
				    result = result - 1000;
				}
				
				// 이메일 제목
				msg.setSubject("BKsT. 회원가입 이메일 인증번호입니다.", "UTF-8");

				// 이메일 내용
				msg.setText("인증번호 :" + result + "입니다.", "UTF-8");

				// 이메일 헤더
				msg.setHeader("content-Type", "text/html");
				
				// 메일 발신
				javax.mail.Transport.send(msg);
				

			}catch (AddressException addr_e) {
				addr_e.printStackTrace();
			}catch (MessagingException msg_e) {
				msg_e.printStackTrace();
			}

		}
		
		req.setAttribute("result",result);
		req.setAttribute("emailCheck", emailCheck);
		req.setAttribute("email", email);
		
		
	}
	
	public class MyAuthentication extends Authenticator{
		PasswordAuthentication pa;
		public MyAuthentication(){


			String id = "qapl503@gmail.com";       // 구글 ID
			String pw = "gus857955";          // 구글 비밀번호

			// ID와 비밀번호를 입력한다.
			pa = new PasswordAuthentication(id, pw);

		}
		// 시스템에서 사용하는 인증정보
		public PasswordAuthentication getPasswordAuthentication() {
			return pa;
			
		}
	}

	@Override
	public void idSearch(HttpServletRequest req, Model model) {
		String email=req.getParameter("email");
		String id=null;
		int cnt=1;
		MemberDAO dao=MemberDAOImpl.getInstance();
		int result=0;
		id=dao.idSearch(email);
		if(id!=null) {
			Properties p = System.getProperties();
			p.put("mail.smtp.starttls.enable", "true"); // gmail TRUE 고정
			p.put("mail.smtp.host", "smtp.gmail.com"); // SMTP 서버 주소
			p.put("mail.smtp.auth","true"); // gmail TRUE 고정
			p.put("mail.smtp.port", "587"); // gmail 포트
			// smtp.naver.com SMTP 포트번호 : 465 // naver 포트
				
			Authenticator auth = new MyAuthentication();

			// session / MimeMessage 생성
			Session session = Session.getDefaultInstance(p, auth);
			MimeMessage msg = new MimeMessage(session);

			try{
				// 송신 날짜
				msg.setSentDate(new Date());

				InternetAddress from = new InternetAddress() ;

				from = new InternetAddress("qapl503@gmail.com");

				// 발신자 이메일
				msg.setFrom(from);

				// 수신자 이메일
				InternetAddress to = new InternetAddress(email);
				msg.setRecipient(Message.RecipientType.TO, to);

				Random rnd = new Random();
				result = rnd.nextInt(10000)+1000;
				 
				if(result>10000){
				    result = result - 1000;
				}
				
				// 이메일 제목
				msg.setSubject("BKsT. 회원가입 이메일 인증번호입니다.", "UTF-8");

				// 이메일 내용
				msg.setText("인증번호 :" + result + "입니다.", "UTF-8");

				// 이메일 헤더
				msg.setHeader("content-Type", "text/html");
				
				// 메일 발신
				javax.mail.Transport.send(msg);
				

			}catch (AddressException addr_e) {
				addr_e.printStackTrace();
			}catch (MessagingException msg_e) {
				msg_e.printStackTrace();
			}
			cnt=2;
		}
		req.setAttribute("result",result);
		req.setAttribute("cnt", cnt);
		req.setAttribute("email", email);
		req.setAttribute("id", id);
		
	}

	@Override
	public void pwdSearch(HttpServletRequest req, Model model) {
		String id=req.getParameter("id");
		MemberDAO dao=MemberDAOImpl.getInstance();
		String pwd=dao.pwdSearch(id);
		String email=dao.emailSearch(id);
		int cnt=1;
		int result=0;
		if(pwd!=null) {
			Properties p = System.getProperties();
			p.put("mail.smtp.starttls.enable", "true"); // gmail TRUE 고정
			p.put("mail.smtp.host", "smtp.gmail.com"); // SMTP 서버 주소
			p.put("mail.smtp.auth","true"); // gmail TRUE 고정
			p.put("mail.smtp.port", "587"); // gmail 포트
			// smtp.naver.com SMTP 포트번호 : 465 // naver 포트
				
			Authenticator auth = new MyAuthentication();

			// session / MimeMessage 생성
			Session session = Session.getDefaultInstance(p, auth);
			MimeMessage msg = new MimeMessage(session);

			try{
				// 송신 날짜
				msg.setSentDate(new Date());

				InternetAddress from = new InternetAddress() ;

				from = new InternetAddress("qapl503@gmail.com");

				// 발신자 이메일
				msg.setFrom(from);

				// 수신자 이메일
				InternetAddress to = new InternetAddress(email);
				msg.setRecipient(Message.RecipientType.TO, to);

				Random rnd = new Random();
				result = rnd.nextInt(10000)+1000;
				 
				if(result>10000){
				    result = result - 1000;
				}
				
				// 이메일 제목
				msg.setSubject("BKsT. 비밀번호 찾기 인증번호입니다.", "UTF-8");

				// 이메일 내용
				msg.setText("인증번호 :" + result + "입니다.", "UTF-8");

				// 이메일 헤더
				msg.setHeader("content-Type", "text/html");
				
				// 메일 발신
				javax.mail.Transport.send(msg);
				
			}catch (AddressException addr_e) {
				addr_e.printStackTrace();
			}catch (MessagingException msg_e) {
				msg_e.printStackTrace();
			}
			cnt=2;
		}
		req.setAttribute("pwd", pwd);
		req.setAttribute("email", email);
		req.setAttribute("result", result);
		req.setAttribute("cnt", cnt);
	}
	
	
}
