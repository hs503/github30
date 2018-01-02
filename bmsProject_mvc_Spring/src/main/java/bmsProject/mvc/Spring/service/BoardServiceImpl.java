package bmsProject.mvc.Spring.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import bmsProject.mvc.Spring.persistance.BoardDAO;
import bmsProject.mvc.Spring.persistance.BoardDAOImpl;
import bmsProject.mvc.Spring.vo.BoardVO;
@Service
public class BoardServiceImpl implements BoardService{

	//�۸��
	
		@Override
		public void BoardList(HttpServletRequest req, Model model) {
			int pageSize  = 4;	//�� �������� ����� �� ����
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
			BoardDAO dao=new BoardDAOImpl();
			
			//�۰��� ���ϱ�		
			cnt=dao.getArticleCnt();
			
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
				Map<String,Integer> map=new HashMap<String, Integer>();
				map.put("start", start);
				map.put("end", end);
				ArrayList<BoardVO> dtos=dao.getArticleList(map);
				req.setAttribute("dtosB", dtos);
				
			}
			
			startPage=(currentPage/pageBlock)*pageBlock+1; //����������=(���� ������/�� ���� ������ ����)*�� ���� ������ ����+1;
			if(currentPage % pageBlock ==0)startPage -= pageBlock; //(����������%�� ���� ������ ����==0)
			
			endPage=startPage+pageBlock-1; //������������=����������+�� ���� ������ ����-1;
			if(endPage > pageCount)endPage = pageCount; 
			
			req.setAttribute("cntB", cnt); //�۰���
			req.setAttribute("numberB", number);//�۹�ȣ
			req.setAttribute("pageNumB", pageNum); //��������ȣ
			
			if(cnt>0) {
				req.setAttribute("startPageB", startPage); //���� ������
				req.setAttribute("endPageB", endPage); //������ ������
				req.setAttribute("pageBlockB", pageBlock); //����� ������ ����
				req.setAttribute("pageCountB", pageCount); //������ ����
				req.setAttribute("currentPageB",currentPage);//���� ������
			}
		}

		//�� ������
		@Override
		public void contentForm(HttpServletRequest req, Model model) {
			int num=Integer.parseInt(req.getParameter("num"));
			int pageNum=Integer.parseInt(req.getParameter("pageNum"));
			int number=Integer.parseInt(req.getParameter("number"));
			BoardVO dto=new BoardVO();
			
			//dao ����(�̱���, ������)
			BoardDAO dao=new BoardDAOImpl();
			
			//��ȸ�� ����
			dao.addReadCnt(num);
			
			//�������� �������� ..1��
			dto=dao.getArticleContent(num);
					
			//jsp�� ���� �ѱ��. dto, pageNum, number
			req.setAttribute("dto", dto);
			req.setAttribute("pageNum", pageNum);
			req.setAttribute("number", number); //db ��ȣ�� �ƴ� �������� ���� ������ ��ȣ
		}

		// �ۼ��� �� ������
		@Override
		public void modifyView(HttpServletRequest req, Model model) {
			//�Ѱܹ��� �� �����´�.
			int num=Integer.parseInt(req.getParameter("num"));
			int pageNum=Integer.parseInt(req.getParameter("pageNum"));
			String pwd=req.getParameter("pwd");
			BoardVO dto=new BoardVO();

			//dao ��ü ����(�̱���, ������ ����)
			BoardDAO dao=new BoardDAOImpl();
			
			//�н����尡 ��ġ�ϸ� selectCnt=1, ����ġ�� selectCnt =0;
			Map map = new HashMap();
			map.put("num", num);
			map.put("pwd", pwd);
			
			
			int selectCnt = dao.pwdCheck(map);
			
			System.out.println("selectCnt:"+selectCnt);
			
			//�н����尡 ��ġ�ϸ� num�� ��ġ�ϴ� �Խñ� 1���� �о�´�.
			if(selectCnt==1) {
				System.out.println("selectCnt:"+selectCnt);
				dto=dao.getArticleContent(num);
			
				//jsp�� ������ �ѱ��.
				req.setAttribute("dto", dto);
			}
			req.setAttribute("selectCnt", selectCnt);
			req.setAttribute("num", num);
			req.setAttribute("pageNum", pageNum);
			
		}

		@Override
		public void modifyPro(HttpServletRequest req, Model model) {
			
			//�Ѱܹ��� �� �����´�.
			int cnt=0;
			int num=Integer.parseInt(req.getParameter("num"));
			int pageNum=Integer.parseInt(req.getParameter("pageNum"));
			String subject=req.getParameter("subject");
			String content=req.getParameter("content");
			String pwd=req.getParameter("pwd");
			
			//�ٱ��� ����
			BoardVO dto=new BoardVO();
			
			//�ٱ��Ͽ� ȭ�鿡�� �Է¹��� ������ ��´�.
			dto.setNum(num);
			dto.setSubject(subject);
			dto.setContent(content);
			dto.setPwd(pwd);
			
			//dao ��ü ����(�̱���, ������ ����)
			BoardDAO dao=new BoardDAOImpl();
			
			//����ó��
			dao.updateModify(dto);
			
			//jsp�� ���� �ѱ��.
			req.setAttribute("num", num);
			req.setAttribute("pageNum", pageNum);
			req.setAttribute("dto", dto);
		}

		@Override
		public void writePro(HttpServletRequest req, Model model) {
			//�Ѱܹ��� �� �����´�.
			int num=Integer.parseInt(req.getParameter("num"));
			int ref=Integer.parseInt(req.getParameter("ref"));
			int ref_step=Integer.parseInt(req.getParameter("ref_step"));
			int ref_level=Integer.parseInt(req.getParameter("ref_level"));
			
			String id=(String) req.getSession().getAttribute("memId");
			String writer=req.getParameter("writer");
			String subject=req.getParameter("subject");
			String pwd=req.getParameter("pwd");
			String content=req.getParameter("content");
			
			//�ٱ��� ����
			BoardVO dto=new BoardVO();
			
			//�ٱ��Ͽ� ȭ�鿡�� �Է¹��� ������ ��´�.
			dto.setNum(num);
			dto.setId(id);
			dto.setRef(ref);
			dto.setRef_step(ref_step);
			dto.setRef_level(ref_level);
			dto.setWriter(writer);
			dto.setPwd(pwd);
			dto.setSubject(subject);
			dto.setContent(content);
			
			
			//dao ��ü ����(�̱���, ������ ����)
			BoardDAO dao=new BoardDAOImpl();
			
			//db�� ����(insert)
			int cnt=dao.insertBoard(dto);
			
			//jsp�� ���� �ѱ��.
			req.setAttribute("dto", dto);
			req.setAttribute("cnt", cnt);
		}

		@Override
		public void deletePro(HttpServletRequest req, Model model) {
			//�Ѱܹ��� �� �����´�.
			int num=Integer.parseInt(req.getParameter("num"));
			int pageNum=Integer.parseInt(req.getParameter("pageNum"));
			String pwd=req.getParameter("pwd");
		
			//dao ��ü ����(�̱���, ������ ����)
			BoardDAO dao=new BoardDAOImpl();
			Map map = new HashMap();
			map.put("num", num);
			map.put("pwd", pwd);
			//num�� �����ͺ��̽��� ������ ��й�ȣ�� ��ġ�ϴ��� Ȯ��
			BoardVO dto=new BoardVO();
			
			int selectCnt=dao.pwdCheck(map);
			
			/*
			 * deleteCnt = -1; : ����� �ִ� ��� ���� ����
			 * deleteCnt =  0; : ����� ���� ��� ���� ����
			 * deleteCnt =  1; : ����� ���� ��� ���� ����
			 */
			if(selectCnt==1) {
				int deleteCnt=dao.deleteBoard(num);
				req.setAttribute("deleteCnt", deleteCnt);
			}
			req.setAttribute("selectCnt", selectCnt);
			req.setAttribute("pageNum", pageNum);
		}

		@Override
		public void BoardSerch(HttpServletRequest req, Model model) {
			String serch=req.getParameter("serch");
			int pageSize  = 4;	//�� �������� ����� �� ����
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
			BoardDAO dao=new BoardDAOImpl();
			
			//�۰��� ���ϱ�	
			String boardSerch="%"+serch+"%";
			cnt=dao.getSArticleCnt(boardSerch);
			
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
				map.put("boardSerch", boardSerch);
				ArrayList<BoardVO> dtos=dao.getSArticleList(map);
				req.setAttribute("dtosB", dtos);
				
			}
			
			startPage=(currentPage/pageBlock)*pageBlock+1; //����������=(���� ������/�� ���� ������ ����)*�� ���� ������ ����+1;
			if(currentPage % pageBlock ==0)startPage -= pageBlock; //(����������%�� ���� ������ ����==0)
			
			endPage=startPage+pageBlock-1; //������������=����������+�� ���� ������ ����-1;
			if(endPage > pageCount)endPage = pageCount; 
			
			req.setAttribute("cntB", cnt); //�۰���
			req.setAttribute("numberB", number);//�۹�ȣ
			req.setAttribute("pageNumB", pageNum); //��������ȣ
			
			if(cnt>0) {
				req.setAttribute("startPageB", startPage); //���� ������
				req.setAttribute("endPageB", endPage); //������ ������
				req.setAttribute("pageBlockB", pageBlock); //����� ������ ����
				req.setAttribute("pageCountB", pageCount); //������ ����
				req.setAttribute("currentPageB",currentPage);//���� ������
			}
			
		}	
	}
