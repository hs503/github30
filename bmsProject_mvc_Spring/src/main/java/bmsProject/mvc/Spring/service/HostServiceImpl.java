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
		//dao ��ü����(�̱���, ������)
		HostDAO dao=new HostDAOImpl();
		
		//�۰��� ���ϱ�		
		cnt=dao.getMemberCnt();
		
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
			Map<String, Integer> map=new HashMap<String, Integer>();
			map.put("start", start);
			map.put("end", end);
			
			ArrayList<MemberVo> dtos=dao.getMemberList(map);
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
		if(cnt>0) {
			req.setAttribute("startPage", startPage); //���� ������
			req.setAttribute("endPage", endPage); //������ ������
			req.setAttribute("pageBlock", pageBlock); //����� ������ ����
			req.setAttribute("pageCount", pageCount); //������ ����
			req.setAttribute("currentPage",currentPage);//���� ������
		}
	}

	//ȸ������
	@Override
	public void deleteMem(HttpServletRequest req, Model model) {
		String id=req.getParameter("id");
		
		HostDAO dao=new HostDAOImpl();
		
		int cnt=dao.deleteMem(id);
		req.setAttribute("cnt", cnt);
	}

	@Override
	public void bookList(HttpServletRequest req, Model model) {
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
		
		int bookList=0;
		//dao ��ü����(�̱���, ������)
		HostDAO dao=new HostDAOImpl();
		
		//�۰��� ���ϱ�		
		cnt=dao.getBookListCnt();
		
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
			Map<String, Integer> map=new HashMap<String, Integer>();
			map.put("start", start);
			map.put("end", end);
			ArrayList<bookListVo> dtos=dao.getBookList(map);
			req.setAttribute("dtos", dtos);
			
		}
		
		startPage=(currentPage/pageBlock)*pageBlock+1; //����������=(���� ������/�� ���� ������ ����)*�� ���� ������ ����+1;
		if(currentPage % pageBlock ==0)startPage -= pageBlock; //(����������%�� ���� ������ ����==0)
		
		endPage=startPage+pageBlock-1; //������������=����������+�� ���� ������ ����-1;
		if(endPage > pageCount)endPage = pageCount; 
		bookList=1;
		req.setAttribute("cnt", cnt); //�۰���
		req.setAttribute("number", number);//�۹�ȣ
		req.setAttribute("pageNum", pageNum); //��������ȣ
		req.setAttribute("bookList", bookList);
		if(cnt>0) {
			req.setAttribute("startPage", startPage); //���� ������
			req.setAttribute("endPage", endPage); //������ ������
			req.setAttribute("pageBlock", pageBlock); //����� ������ ����
			req.setAttribute("pageCount", pageCount); //������ ����
			req.setAttribute("currentPage",currentPage);//���� ������
		}
	}
	@Override
	public void orderList(HttpServletRequest req, Model model) {
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
		
		int orderList=0;
		//dao ��ü����(�̱���, ������)
		HostDAO dao=new HostDAOImpl();
		
		//�۰��� ���ϱ�		
		cnt=dao.getOrderListCnt();
		
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
			Map<String,Integer> map =new HashMap<String,Integer>();
			map.put("start", start);
			map.put("end", end);
			
			ArrayList<orderListVo> dtos=dao.getOrderList(map);

			req.setAttribute("dtos", dtos);
			
		}
		
		startPage=(currentPage/pageBlock)*pageBlock+1; //����������=(���� ������/�� ���� ������ ����)*�� ���� ������ ����+1;
		if(currentPage % pageBlock ==0)startPage -= pageBlock; //(����������%�� ���� ������ ����==0)
		
		endPage=startPage+pageBlock-1; //������������=����������+�� ���� ������ ����-1;
		if(endPage > pageCount)endPage = pageCount; 
		orderList=1;
		req.setAttribute("cnt", cnt); //�۰���
		req.setAttribute("number", number);//�۹�ȣ
		req.setAttribute("pageNum", pageNum); //��������ȣ
		req.setAttribute("orderList", orderList);
		if(cnt>0) {
			req.setAttribute("startPage", startPage); //���� ������
			req.setAttribute("endPage", endPage); //������ ������
			req.setAttribute("pageBlock", pageBlock); //����� ������ ����
			req.setAttribute("pageCount", pageCount); //������ ����
			req.setAttribute("currentPage",currentPage);//���� ������
		}
		
	}
	@Override
	public void delivaryList(HttpServletRequest req, Model model) {
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
		HostDAO dao=new HostDAOImpl();
		
		//�۰��� ���ϱ�		
		cnt=dao.getdelivaryListCnt();
		
		pageNum=req.getParameter("pageNum1");
		
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
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("start", start);
			map.put("end", end);
			
			ArrayList<DeliveryVo> dtos=dao.getdeliveryList(map);
			
			req.setAttribute("dtos1", dtos);
			
		}
		
		startPage=(currentPage/pageBlock)*pageBlock+1; //����������=(���� ������/�� ���� ������ ����)*�� ���� ������ ����+1;
		if(currentPage % pageBlock ==0)startPage -= pageBlock; //(����������%�� ���� ������ ����==0)
		
		endPage=startPage+pageBlock-1; //������������=����������+�� ���� ������ ����-1;
		if(endPage > pageCount)endPage = pageCount; 

		req.setAttribute("cnt1", cnt); //�۰���
		req.setAttribute("number1", number);//�۹�ȣ
		req.setAttribute("pageNum1", pageNum); //��������ȣ
		if(cnt>0) {
			req.setAttribute("startPage1", startPage); //���� ������
			req.setAttribute("endPage1", endPage); //������ ������
			req.setAttribute("pageBlock1", pageBlock); //����� ������ ����
			req.setAttribute("pageCount1", pageCount); //������ ����
			req.setAttribute("currentPage1",currentPage);//���� ������
		}
		
	}
	
	@Override
	public void insertBook(HttpServletRequest req, Model model) {
		// MultipartRequest Ÿ���� ���� ����
		MultipartRequest mr = null;
		// ���ε��� ������ �ִ� ������(10 * 1024 * 1024 = 10MB)
		int maxSize = 10 * 1024 * 1024;
		// �ӽ� ������ ����Ǵ� ������ ���
		String saveDir = req.getRealPath("/saveImg/");
		// ���ε��� ������ ��ġ�ϰԵ� �������� ���
		String realDir = "C:\\Dev2\\workspace\\bmsProject_mvc_Spring\\src\\main\\webapp\\resources\\saveImg\\";
		// ���ڵ� Ÿ�� : �ѱ� ���ϸ��� ��ȭ�Ǵ� ���� ������
		String encType = "UTF-8";
		try {
		
		/** DefaultFileRenamePolicy() ��ü�� �ߺ��� ���ϸ��� ���� ���, �ڵ����� ���ϸ��� ������
		* (�� : filename.png �� �̹� ������ ���, filename1.png �� ����)*/
		
		mr = new MultipartRequest(req, saveDir, maxSize, encType, new DefaultFileRenamePolicy());
		FileInputStream fis = new FileInputStream(saveDir + mr.getFilesystemName("image"));
		FileOutputStream fos = new FileOutputStream(realDir + mr.getFilesystemName("image"));
		int data = 0;
		// ������ ��ο� ����� �ӽ� ������ �������� ��η� ������
		while((data = fis.read()) != -1) {
		fos.write(data);
		}
		fis.close();
		fos.close();
		
		/** ������ MultipartRequest() ��ü�� �����ؼ� �޴� ��� request ��ü����
		* MultipartRequest Ÿ������ �����ž���
		* (�� : request.getParameter ���� mr.getParameter)*/
		
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
					System.out.println("���ϻ��� ����");
				}else{
	                System.out.println("���ϻ��� ����");
	            }
	        }else{
	            System.out.println("������ �������� �ʽ��ϴ�.");
	        }
		}
	}

	@Override
	public void updateBook(HttpServletRequest req, Model model) {
		// MultipartRequest Ÿ���� ���� ����
		MultipartRequest mr = null;
		// ���ε��� ������ �ִ� ������(10 * 1024 * 1024 = 10MB)
		int maxSize = 10 * 1024 * 1024;
		// �ӽ� ������ ����Ǵ� ������ ���
		String saveDir = req.getRealPath("/saveImg/");
		// ���ε��� ������ ��ġ�ϰԵ� �������� ���
		String realDir = "C:\\Dev2\\workspace\\bmsProject_mvc_Spring\\src\\main\\webapp\\resources\\saveImg\\";
		// ���ڵ� Ÿ�� : �ѱ� ���ϸ��� ��ȭ�Ǵ� ���� ������
		String encType = "UTF-8";
		try {
		
		/** DefaultFileRenamePolicy() ��ü�� �ߺ��� ���ϸ��� ���� ���, �ڵ����� ���ϸ��� ������
		* (�� : filename.png �� �̹� ������ ���, filename1.png �� ����)*/
		
		mr = new MultipartRequest(req, saveDir, maxSize, encType, new DefaultFileRenamePolicy());
		FileInputStream fis = new FileInputStream(saveDir + mr.getFilesystemName("image"));
		FileOutputStream fos = new FileOutputStream(realDir + mr.getFilesystemName("image"));
		int data = 0;
		// ������ ��ο� ����� �ӽ� ������ �������� ��η� ������
		while((data = fis.read()) != -1) {
		fos.write(data);
		}
		fis.close();
		fos.close();
		
		/** ������ MultipartRequest() ��ü�� �����ؼ� �޴� ��� request ��ü����
		* MultipartRequest Ÿ������ �����ž���
		* (�� : request.getParameter ���� mr.getParameter)*/
		
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
		//delevary ���̺��� ȯ�ҿ���(refund)���� '��۴����' -> '�Ǹ�'
		dao.delUpdate(num);
		//1. ������ å�� �Ǹż��� ������ �� å�� ��Ʈ�� ����
		Map map=new HashMap();
		map.put("amount", amount);
		map.put("title", title);
		bookChat=dao.sellCnt(map);
		//2. ������ ��Ʈ�� closing���̺��� �´� ��Ʈ�� �ǸŰ��� ����.+���ݾ� ���ϱ�
		Map map2=new HashMap();
		map2.put("price", price);
		map2.put("bookChat", bookChat);
		map2.put("amount", amount);
		dao.totalSale(map2);
		//3. ����� å�� �ֹ���Ͽ��� ����
		dao.orderDel(num);
		
		req.setAttribute("delivary", delivary);
	}

	@Override
	public void hostMain(HttpServletRequest req, Model model) {
		HostDAO dao=new HostDAOImpl();
		//����Ͽ��� ��� 0�ΰ��� ���� ���ϱ�
		int nullAmountCnt=dao.bookAmountSerch();
		//closing ���̺��� soltOutCnt�� ǰ������ ����
		dao.updateClosing(nullAmountCnt);
		//delivery���̺��� refund�� 'ȯ�ҽ�û'�� ���� ���ϱ�
		int refundCnt=dao.selectRefundCnt();
		//closing ���̺��� refundCnt�� ����
		dao.updateClosingRefund(refundCnt);
		//closing���̺��� ���� �������� dto
		closingVo dto =new closingVo();
		
		dto=dao.closingSerch(dto);
		
		req.setAttribute("dto", dto);
		
	}

	@Override
	public void refund(HttpServletRequest req, Model model) {
		int price=Integer.parseInt(req.getParameter("price"));
		String title=req.getParameter("title");
		int num=Integer.parseInt(req.getParameter("num"));
		String refund="ȯ��";
		int amount=Integer.parseInt(req.getParameter("amount"));
		
		HostDAO dao=new HostDAOImpl();
		MemberDAO dao1=MemberDAOImpl.getInstance();
		//bookList���̺��� bookChat���ϱ�
		String bookChat=dao.serchChat(title);
		//bookList���̺��� sellCnt�� ȯ���� ��ŭ ���ֱ�
		Map map=new HashMap();
		map.put("amount", amount);
		map.put("title", title);
		dao.subSellCnt(map);
		//closing���̺��� ȯ�Ұ�����ŭ chat���ż�������
		Map map2=new HashMap();
		map2.put("bookChat", bookChat);
		map2.put("amount", amount);
		dao.subChat(map2);
		//���Ǹűݾ� - �ϱ�
		dao.subTotalSale(price);
		//closing���̺��� refundCnt -1�ϱ�
		dao.subRefundCnt();
	
		//����Ͽ� ���� +
		dao.bookRefund(map);
		//delivary���̺��� refund 'ȯ��'�� ����
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
