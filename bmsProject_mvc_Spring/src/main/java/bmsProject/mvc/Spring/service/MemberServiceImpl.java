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

	// �ߺ�Ȯ�� ó��
		@Override
		public void confirmId(HttpServletRequest req, Model model) {
			// 3�ܰ�. ȭ�����κ��� �Է¹��� ���� �޾ƿ´�.
			String strId = req.getParameter("id");
			
			// 4�ܰ�. ������ ����, �̱��� ������� ��ü ����
			MemberDAO dao = MemberDAOImpl.getInstance();
				
			// 5�ܰ�. �ߺ��� id�� �ִ� �� Ȯ��
			int cnt = dao.idCheck(strId);
			
			// 6�ܰ�. request�� session�� ó�� ����� ����	
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
		// 5�ܰ�. request�� session�� ó�� ����� �����ϸ� jsp���� �޴´�.
		req.setAttribute("cnt", cnt);
		req.setAttribute("name", name);

				
	}

	@Override
	public void loginPro(HttpServletRequest req, Model model) {
		// 1�ܰ�. ȭ�����κ��� ���̵�, �н����� �޾ƿ´�.
				String strId = req.getParameter("id");
				String strPwd = req.getParameter("pwd");
				
				// 2-1�ܰ�. dao ��ü����(�̱���, ������ ����)
				MemberDAO dao = MemberDAOImpl.getInstance();
				
				// 2-2�ܰ�. ���� ����Ͽ� ��û�� ����� �����Ѵ�.
				Map map = new HashMap();
				map.put("strId", strId);
				map.put("strPwd", strPwd);
				int cnt = dao.check(map);
				
				String name=dao.checkName(strId);
				// 3�ܰ�. request�� session�� ó�� ����� �����ϸ� jsp���� �޴´�.
				// �α��� ����(cnt==1)�ϸ� ����id, cnt:1�� �ѱ��.
				// �α��� ����(��й�ȣ ����ġ cnt:-1 || ���̵� �������� ���� �� cnt:0
				if(cnt == 1) {
					//memId ��ҹ��� ����
						req.getSession().setAttribute("memId", strId);
						req.getSession().setAttribute("memName", name);
				}
				req.setAttribute("cnt", cnt);
		
	}

	@Override
	public void modifyView(HttpServletRequest req, Model model) {
		// 1�ܰ� . ȭ������ ���̵�, �н����� ���� �޾ƿ´�.
		String strId = (String) req.getSession().getAttribute("memId");
		String strPwd = req.getParameter("pwd");
		
		// 2-1�ܰ�. dao ��ü����(�̱���, ������ ����)
		MemberDAO dao = MemberDAOImpl.getInstance();
		
		// 2-2�ܰ�. ���� ����Ͽ� ��û�� ����� �����Ѵ�.
		/* 
		 * ���̵�� �н����尡 ��ġ�ϸ� (selectCnt == 1) �Է��� ȸ�������� �о�´�.
		 * �н����尡 ��ġ���� ������ (selectCnt == -1) | ���̵� �������� ������ (selectCnt == 0)
		 */	
		Map map=new HashMap();
		map.put("strId", strId);
		map.put("strPwd",strPwd);
		
		int selectCnt = dao.check(map);
		
		// ���̵�� �н����尡 ��ġ�ϸ�, �����ϱ� ���ؼ�, �Է��� ������ �о�´�.
		if(selectCnt == 1) {
			MemberVo vo = dao.getMemberInfo(strId);
			req.setAttribute("vo", vo);
		}

		System.out.println("selectCnt -->" + selectCnt);
				  
		// 3�ܰ�. request�� session�� ó�� ����� �����ϸ� jsp���� �޴´�.
		req.setAttribute("selectCnt", selectCnt);
		
	}

	@Override
	public void modifyPro(HttpServletRequest req, Model model) {
		// 1�ܰ�. ���� �̿��ؼ� ��û�� ����� �����Ѵ�.
		// vo �ٱ��� ����
		MemberVo vo = new MemberVo();	
				
		// 2�ܰ�. vo �ٱ��Ͽ� ��´�(ȭ�鿡�� �Ѿ�� ����).
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
				
		// �ʼ��Է� �׸��� �ƴϹǷ� null üũ���� ������ insert�ϸ� null pointer Exception �߻�
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
		
		// 3�ܰ�. ������ ����, �̱��� ������� ��ü ����
		MemberDAO dao = MemberDAOImpl.getInstance();
		
		// 4�ܰ�. ���� ����Ͽ� ��û�� ����� �����Ѵ�.
		// 4�ܰ�. DAO���� vo �ٱ��ϸ� dao �Ķ���Ϳ� �Ѱܼ� �ش� SQL�� ȣ��
		
		int cnt = dao.updateMember(vo);
		System.out.println("cnt : " + cnt);
		
		// 5�ܰ�. request�� session�� ó�� ����� �����ϸ� jsp���� �޴´�.
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
		
		int pageSize  = 5;	//�� �������� ����� �� ����
		int pageBlock = 3;	//�� ���� ������ ����
		
		int cnt   = 0; 			//�۰���
		int start = 0; 			//���� ������ �۽��� ��ȣ
		int end   = 0;			//���� ������ �۸����� ��ȣ
		int number = 0;			//����� �۹�ȣ
		String pageNum = null;	//��������ȣ
		int currentPage = 0;	//���� ������
		
		int pageCount = 0;		//������ ����
		int startPage = 0;		//���� ������
		int endPage = 0;		//������ ������
		Map map = new HashMap();
		//dao ��ü����(�̱���, ������)
		MemberDAO dao=MemberDAOImpl.getInstance();
		String id = (String) req.getSession().getAttribute("memId");
		String ip = req.getRemoteAddr();
		//�۰��� ���ϱ�
		if(id==null) {
			cnt=dao.getMemberCnt(ip);
		}else {
			cnt=dao.getMemberCnt(id);
		}
		pageNum=req.getParameter("pageNum");
		
		if(pageNum == null) {
			pageNum="1"; //ù�������� 1�������� ����
		}
		
		currentPage =Integer.parseInt(pageNum);//���� ������
		//pageCnt = 12/5+1; //������ 2���� 1�������� �Ҵ�ǹǷ� 3������
		pageCount = (cnt/pageSize)+(cnt%pageSize>0?1:0);//������ ����=(�۰���/�������� �۰���)+(�������� ������1 �ƴϸ�0)
		
		//���� ������ 1=(1-1)*5+1
		//6=(2-1)*5+1
		//11=(3-1)*5+1
		//21=(5-1)*5+1
		
		start = (currentPage -1 )*pageSize +1;//���� ������ ���۹�ȣ
	
		//5=1+5-1;
		
		end = start + pageSize -1; //���� ������ ����ȣ
	
		System.out.println("start:" + start);
		System.out.println("end:" + end);
		
		if(end>cnt)end=cnt;
		
		// 21 - (5-1)*5;
		number=cnt-(currentPage -1)*pageSize; //����� �۹�ȣ.. �ֽű�(ū������)����� �۹�ȣ
		System.out.println("number:" + number);
		System.out.println("cnt:" + cnt);
		System.out.println("currentPage:" + currentPage);
		System.out.println("pageSize:" + pageSize);
		
		if(cnt>0) {
			//�Խñ� ��� ��ȸ
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
		
		startPage=(currentPage/pageBlock)*pageBlock+1; //����������=(���� ������/�� ���� ������ ����)*�� ���� ������ ����+1;
		if(currentPage % pageBlock ==0)startPage -= pageBlock; //(����������%�� ���� ������ ����==0)
		
		endPage=startPage+pageBlock-1; //������������=����������+�� ���� ������ ����-1;
		if(endPage > pageCount)endPage = pageCount; 
		req.setAttribute("cnt", cnt); //�۰���
		req.setAttribute("number", number);//�۹�ȣ
		req.setAttribute("pageNum", pageNum); //��������ȣ

		if(cnt>0) {
			req.setAttribute("startPage", startPage); //���� ������
			req.setAttribute("endPage", endPage); //������ ������
			req.setAttribute("pageBlock", pageBlock); //����� ������ ����
			req.setAttribute("pageCount", pageCount); //������ ����
			req.setAttribute("currentPage",currentPage);//���� ������
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
		int code=Integer.parseInt(req.getParameter("code"));//å�� ���� �ڵ�
		int amount=Integer.parseInt(req.getParameter("amount"));//������ ����
		int price=Integer.parseInt(req.getParameter("price"));//�հ� �� ���Ű���
		String title=req.getParameter("title");
		System.out.println("title:"+title);
				
		String id = (String) req.getSession().getAttribute("memId");
		String ip = req.getRemoteAddr();
		
		int buyBookCnt=0;
		BookDAO dao=new BookDAOImpl();

		orderListVo dto=new orderListVo();
		
		if(id==null) { //�α������� ���� ����
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
			 	dto.setNum(num);//�������� �Է�
				dto.setPublisher(publisher);//������ ����ð��� �Է�(sysdate)
			*/

			buyBookCnt=dao.DeliveryInsert(dto);

		}else { //�α����� ����
			//member������ �����ͼ� order������ ������ ���� �����´�.
			Map map = new HashMap();
			map.put("amount", amount);
			map.put("price", price);
			map.put("title", title);
			map.put("id",id);
			orderListVo MDto=dao.memberSerch(map);

			buyBookCnt=dao.DeliveryInsert(MDto);
		}
		//������ - ���ż���
		int Camount=dao.bookAmount(code)-amount;		
		//����Ͽ� ��������(update)
		MemberDAO dao1=MemberDAOImpl.getInstance();
		//����Ͽ� ��������(update)
		Map map2=new HashMap();
		map2.put("code", code);
		map2.put("amount", Camount);
		
		dao1.BookUpdate(map2);
		

		//��ٱ��� ����
		MemberDAO dao2=MemberDAOImpl.getInstance();
		dao2.cartDelete(num);
		//insert���� ���� �ѱ��
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
		int pageSize  = 5;	//�� �������� ����� �� ����
		int pageBlock = 3;	//�� ���� ������ ����
		
		int cnt   = 0; 			//�۰���
		int start = 0; 			//���� ������ �۽��� ��ȣ
		int end   = 0;			//���� ������ �۸����� ��ȣ
		int number = 0;			//����� �۹�ȣ
		String pageNum = null;	//��������ȣ
		int currentPage = 0;	//���� ������
		
		int pageCount = 0;		//������ ����
		int startPage = 0;		//���� ������
		int endPage = 0;		//������ ������

		//dao ��ü����(�̱���, ������)
		MemberDAO dao=MemberDAOImpl.getInstance();
		String id = (String) req.getSession().getAttribute("memId");
		String ip = req.getRemoteAddr();
		//�۰��� ���ϱ�
		if(id==null) {
			cnt=dao.getbuyListCnt(ip);
		}else {
			cnt=dao.getbuyListCnt(id);
		}
		pageNum=req.getParameter("pageNum");
		
		if(pageNum == null) {
			pageNum="1"; //ù�������� 1�������� ����
		}
		
		currentPage =Integer.parseInt(pageNum);//���� ������
		//pageCnt = 12/5+1; //������ 2���� 1�������� �Ҵ�ǹǷ� 3������
		pageCount = (cnt/pageSize)+(cnt%pageSize>0?1:0);//������ ����=(�۰���/�������� �۰���)+(�������� ������1 �ƴϸ�0)
		
		//���� ������ 1=(1-1)*5+1
		//6=(2-1)*5+1
		//11=(3-1)*5+1
		//21=(5-1)*5+1
		
		start = (currentPage -1 )*pageSize +1;//���� ������ ���۹�ȣ
	
		//5=1+5-1;
		
		end = start + pageSize -1; //���� ������ ����ȣ
	
		System.out.println("start:" + start);
		System.out.println("end:" + end);
		
		if(end>cnt)end=cnt;
		
		// 21 - (5-1)*5;
		number=cnt-(currentPage -1)*pageSize; //����� �۹�ȣ.. �ֽű�(ū������)����� �۹�ȣ
		System.out.println("number:" + number);
		System.out.println("cnt:" + cnt);
		System.out.println("currentPage:" + currentPage);
		System.out.println("pageSize:" + pageSize);
		Map map=new HashMap();
		if(cnt>0) {
			//�Խñ� ��� ��ȸ
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
		
		startPage=(currentPage/pageBlock)*pageBlock+1; //����������=(���� ������/�� ���� ������ ����)*�� ���� ������ ����+1;
		if(currentPage % pageBlock ==0)startPage -= pageBlock; //(����������%�� ���� ������ ����==0)
		
		endPage=startPage+pageBlock-1; //������������=����������+�� ���� ������ ����-1;
		if(endPage > pageCount)endPage = pageCount; 
		req.setAttribute("cnt", cnt); //�۰���
		req.setAttribute("number", number);//�۹�ȣ
		req.setAttribute("pageNum", pageNum); //��������ȣ

		if(cnt>0) {
			req.setAttribute("startPage", startPage); //���� ������
			req.setAttribute("endPage", endPage); //������ ������
			req.setAttribute("pageBlock", pageBlock); //����� ������ ����
			req.setAttribute("pageCount", pageCount); //������ ����
			req.setAttribute("currentPage",currentPage);//���� ������
		}
		
	}

	@Override
	public void refund(HttpServletRequest req, Model model) {
		int num=Integer.parseInt(req.getParameter("num"));
		String refund="ȯ�ҽ�û";
		MemberDAO dao=MemberDAOImpl.getInstance();
		//delivary���̺��� refund(ȯ�ҿ���)���� , ����->ȯ�ҽ�û
		Map map=new HashMap();
		map.put("num", num);
		map.put("refund", refund);
		int refundCnt=dao.refundUpdate(map);
		//closing���̺��� refundCnt 1 ����
		dao.closingRefundUpdate();
		
		req.setAttribute("refundCnt", refundCnt);
	}

	@Override
	public void Confirmation(HttpServletRequest req, Model model) {
		int num=Integer.parseInt(req.getParameter("num"));
		String Confirmation="����Ȯ��";
		MemberDAO dao=MemberDAOImpl.getInstance();
		Map map=new HashMap();
		map.put("num", num);
		map.put("refund", Confirmation);
		int ConfirmationCnt=dao.refundUpdate(map);
		
		req.setAttribute("ConfirmationCnt", ConfirmationCnt);
	}

	@Override
	public void serch(HttpServletRequest req, Model model) {
		int pageSize  = 5;	//�� �������� ����� �� ����
		int pageBlock = 3;	//�� ���� ������ ����
		
		int cnt   = 0; 			//�۰���
		int start = 0; 			//���� ������ �۽��� ��ȣ
		int end   = 0;			//���� ������ �۸����� ��ȣ
		int number = 0;			//����� �۹�ȣ
		String pageNum = null;	//��������ȣ
		int currentPage = 0;	//���� ������
		
		int pageCount = 0;		//������ ����
		int startPage = 0;		//���� ������
		int endPage = 0;		//������ ������
		
		int Hmember=0;
		String serch=req.getParameter("serch");
		
		//dao ��ü����(�̱���, ������)
		MemberDAO dao=new MemberDAOImpl();
		
		//�۰��� ���ϱ�		
		String serchLike="%"+serch+"%";
		cnt=dao.getBookListCnt(serchLike);
		
		pageNum=req.getParameter("pageNum");
		
		if(pageNum == null) {
			pageNum="1"; //ù�������� 1�������� ����
		}
		
		currentPage =Integer.parseInt(pageNum);//���� ������
		//pageCnt = 12/5+1; //������ 2���� 1�������� �Ҵ�ǹǷ� 3������
		pageCount = (cnt/pageSize)+(cnt%pageSize>0?1:0);//������ ����=(�۰���/�������� �۰���)+(�������� ������1 �ƴϸ�0)
		
		//���� ������ 1=(1-1)*5+1
		//6=(2-1)*5+1
		//11=(3-1)*5+1
		//21=(5-1)*5+1
		
		start = (currentPage -1 )*pageSize +1;//���� ������ ���۹�ȣ
	
		//5=1+5-1;
		
		end = start + pageSize -1; //���� ������ ����ȣ
	
		System.out.println("start:" + start);
		System.out.println("end:" + end);
		
		if(end>cnt)end=cnt;
		
		// 21 - (5-1)*5;
		number=cnt-(currentPage -1)*pageSize; //����� �۹�ȣ.. �ֽű�(ū������)����� �۹�ȣ
		System.out.println("number:" + number);
		System.out.println("cnt:" + cnt);
		System.out.println("currentPage:" + currentPage);
		System.out.println("pageSize:" + pageSize);
		
		if(cnt>0) {
			//�Խñ� ��� ��ȸ
			Map map=new HashMap();
			String LikeSerch="%"+serch+"%";
			map.put("start", start);
			map.put("end", end);
			map.put("serch", LikeSerch);
			ArrayList<bookListVo> dtos=dao.getbookList(map);
			req.setAttribute("dtos", dtos);			
		}
		
		startPage=(currentPage/pageBlock)*pageBlock+1; //����������=(���� ������/�� ���� ������ ����)*�� ���� ������ ����+1;
		if(currentPage % pageBlock ==0)startPage -= pageBlock; //(����������%�� ���� ������ ����==0)
		
		endPage=startPage+pageBlock-1; //������������=����������+�� ���� ������ ����-1;
		if(endPage > pageCount)endPage = pageCount; 
		Hmember=1;
		req.setAttribute("cnt", cnt); //�۰���
		req.setAttribute("number", number);//�۹�ȣ
		req.setAttribute("pageNum", pageNum); //��������ȣ
		req.setAttribute("Hmember", Hmember);
		req.setAttribute("serch", serch);
		if(cnt>0) {
			req.setAttribute("startPage", startPage); //���� ������
			req.setAttribute("endPage", endPage); //������ ������
			req.setAttribute("pageBlock", pageBlock); //����� ������ ����
			req.setAttribute("pageCount", pageCount); //������ ����
			req.setAttribute("currentPage",currentPage);//���� ������
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
			p.put("mail.smtp.starttls.enable", "true"); // gmail TRUE ����
			p.put("mail.smtp.host", "smtp.gmail.com"); // SMTP ���� �ּ�
			p.put("mail.smtp.auth","true"); // gmail TRUE ����
			p.put("mail.smtp.port", "587"); // gmail ��Ʈ
			// smtp.naver.com SMTP ��Ʈ��ȣ : 465 // naver ��Ʈ
				
			Authenticator auth = new MyAuthentication();

			// session / MimeMessage ����
			Session session = Session.getDefaultInstance(p, auth);
			MimeMessage msg = new MimeMessage(session);

			try{
				// �۽� ��¥
				msg.setSentDate(new Date());

				InternetAddress from = new InternetAddress() ;

				from = new InternetAddress("qapl503@gmail.com");

				// �߽��� �̸���
				msg.setFrom(from);

				// ������ �̸���
				InternetAddress to = new InternetAddress(email);
				msg.setRecipient(Message.RecipientType.TO, to);

				Random rnd = new Random();
				result = rnd.nextInt(10000)+1000;
				 
				if(result>10000){
				    result = result - 1000;
				}
				
				// �̸��� ����
				msg.setSubject("BKsT. ȸ������ �̸��� ������ȣ�Դϴ�.", "UTF-8");

				// �̸��� ����
				msg.setText("������ȣ :" + result + "�Դϴ�.", "UTF-8");

				// �̸��� ���
				msg.setHeader("content-Type", "text/html");
				
				// ���� �߽�
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


			String id = "qapl503@gmail.com";       // ���� ID
			String pw = "gus857955";          // ���� ��й�ȣ

			// ID�� ��й�ȣ�� �Է��Ѵ�.
			pa = new PasswordAuthentication(id, pw);

		}
		// �ý��ۿ��� ����ϴ� ��������
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
			p.put("mail.smtp.starttls.enable", "true"); // gmail TRUE ����
			p.put("mail.smtp.host", "smtp.gmail.com"); // SMTP ���� �ּ�
			p.put("mail.smtp.auth","true"); // gmail TRUE ����
			p.put("mail.smtp.port", "587"); // gmail ��Ʈ
			// smtp.naver.com SMTP ��Ʈ��ȣ : 465 // naver ��Ʈ
				
			Authenticator auth = new MyAuthentication();

			// session / MimeMessage ����
			Session session = Session.getDefaultInstance(p, auth);
			MimeMessage msg = new MimeMessage(session);

			try{
				// �۽� ��¥
				msg.setSentDate(new Date());

				InternetAddress from = new InternetAddress() ;

				from = new InternetAddress("qapl503@gmail.com");

				// �߽��� �̸���
				msg.setFrom(from);

				// ������ �̸���
				InternetAddress to = new InternetAddress(email);
				msg.setRecipient(Message.RecipientType.TO, to);

				Random rnd = new Random();
				result = rnd.nextInt(10000)+1000;
				 
				if(result>10000){
				    result = result - 1000;
				}
				
				// �̸��� ����
				msg.setSubject("BKsT. ȸ������ �̸��� ������ȣ�Դϴ�.", "UTF-8");

				// �̸��� ����
				msg.setText("������ȣ :" + result + "�Դϴ�.", "UTF-8");

				// �̸��� ���
				msg.setHeader("content-Type", "text/html");
				
				// ���� �߽�
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
			p.put("mail.smtp.starttls.enable", "true"); // gmail TRUE ����
			p.put("mail.smtp.host", "smtp.gmail.com"); // SMTP ���� �ּ�
			p.put("mail.smtp.auth","true"); // gmail TRUE ����
			p.put("mail.smtp.port", "587"); // gmail ��Ʈ
			// smtp.naver.com SMTP ��Ʈ��ȣ : 465 // naver ��Ʈ
				
			Authenticator auth = new MyAuthentication();

			// session / MimeMessage ����
			Session session = Session.getDefaultInstance(p, auth);
			MimeMessage msg = new MimeMessage(session);

			try{
				// �۽� ��¥
				msg.setSentDate(new Date());

				InternetAddress from = new InternetAddress() ;

				from = new InternetAddress("qapl503@gmail.com");

				// �߽��� �̸���
				msg.setFrom(from);

				// ������ �̸���
				InternetAddress to = new InternetAddress(email);
				msg.setRecipient(Message.RecipientType.TO, to);

				Random rnd = new Random();
				result = rnd.nextInt(10000)+1000;
				 
				if(result>10000){
				    result = result - 1000;
				}
				
				// �̸��� ����
				msg.setSubject("BKsT. ��й�ȣ ã�� ������ȣ�Դϴ�.", "UTF-8");

				// �̸��� ����
				msg.setText("������ȣ :" + result + "�Դϴ�.", "UTF-8");

				// �̸��� ���
				msg.setHeader("content-Type", "text/html");
				
				// ���� �߽�
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
