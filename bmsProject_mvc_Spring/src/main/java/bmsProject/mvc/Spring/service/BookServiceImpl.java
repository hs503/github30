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
		int pageSize  = 10;	//�� �������� ����� �� ����
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
		HostDAO dao=new HostDAOImpl();
		
		//�۰��� ���ϱ�		
		cnt=dao.getBookListCnt();
		System.out.println("cnt:"+cnt+"====================================");
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
			Map map =new HashMap();
			map.put("start", start);
			map.put("end", end);
			ArrayList<bookListVo> dtos=dao.getBookList(map);
			req.setAttribute("dtos", dtos);
			
		}
		
		startPage=(currentPage/pageBlock)*pageBlock+1; //����������=(���� ������/�� ���� ������ ����)*�� ���� ������ ����+1;
		if(currentPage % pageBlock ==0)startPage -= pageBlock; //(����������%�� ���� ������ ����==0)
		
		endPage=startPage+pageBlock-1; //������������=����������+�� ���� ������ ����-1;
		if(endPage > pageCount)endPage = pageCount; 
		req.setAttribute("start", start);
		req.setAttribute("end", end);
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
	public void serchBook(HttpServletRequest req, Model model) {
		String image=req.getParameter("image");
		BookDAO dao=new BookDAOImpl();
		
		bookListVo dto=dao.serchBook(image);
		
		req.setAttribute("dto", dto);
	}

	@Override
	public void DirectBy(HttpServletRequest req, Model model) {
		int code=Integer.parseInt(req.getParameter("code"));//å�� ���� �ڵ�
		int amount=Integer.parseInt(req.getParameter("amount"));//������ ����
		int price=Integer.parseInt(req.getParameter("price"));//�հ� �� ���Ű���
		String title=req.getParameter("title");
		System.out.println("title:"+title);
				
		String id = (String) req.getSession().getAttribute("memId");
		String ip = req.getRemoteAddr();
		
		int DirectByCnt=0;
		
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

			DirectByCnt=dao.DeliveryInsert(dto);

		}else { //�α����� ����
			//member������ �����ͼ� order������ ������ ���� �����´�.
			Map map =new HashMap();
			map.put("amount", amount);
			map.put("price", price);
			map.put("title", title);
			map.put("id", id);
			orderListVo MDto=dao.memberSerch(map);

			DirectByCnt=dao.DeliveryInsert(MDto);
		}
		//������ - ���ż���
		int Camount=dao.bookAmount(code)-amount;		
		//����Ͽ� ��������(update)
		MemberDAO dao1=MemberDAOImpl.getInstance();
		//����Ͽ� ��������(update)
		Map map=new HashMap();
		map.put("code", code);
		map.put("amount", Camount);
		dao1.BookUpdate(map);
		
		req.setAttribute("DirectByCnt", DirectByCnt);
	}

}
