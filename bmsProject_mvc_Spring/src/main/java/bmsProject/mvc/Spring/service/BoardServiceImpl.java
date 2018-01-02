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

	//글목록
	
		@Override
		public void BoardList(HttpServletRequest req, Model model) {
			int pageSize  = 4;	//한 페이지당 출력한 글 갯수
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
			BoardDAO dao=new BoardDAOImpl();
			
			//글갯수 구하기		
			cnt=dao.getArticleCnt();
			
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
				Map<String,Integer> map=new HashMap<String, Integer>();
				map.put("start", start);
				map.put("end", end);
				ArrayList<BoardVO> dtos=dao.getArticleList(map);
				req.setAttribute("dtosB", dtos);
				
			}
			
			startPage=(currentPage/pageBlock)*pageBlock+1; //시작페이지=(현재 페이지/한 블럭당 페이지 갯수)*한 블럭당 페이지 갯수+1;
			if(currentPage % pageBlock ==0)startPage -= pageBlock; //(현재페이지%한 블럭당 페이지 갯수==0)
			
			endPage=startPage+pageBlock-1; //마지막페이지=시작페이지+한 블럭당 페이지 갯수-1;
			if(endPage > pageCount)endPage = pageCount; 
			
			req.setAttribute("cntB", cnt); //글갯수
			req.setAttribute("numberB", number);//글번호
			req.setAttribute("pageNumB", pageNum); //페이지번호
			
			if(cnt>0) {
				req.setAttribute("startPageB", startPage); //시작 페이지
				req.setAttribute("endPageB", endPage); //마지막 페이지
				req.setAttribute("pageBlockB", pageBlock); //출력할 페이지 갯수
				req.setAttribute("pageCountB", pageCount); //페이지 갯수
				req.setAttribute("currentPageB",currentPage);//현재 페이지
			}
		}

		//상세 페이지
		@Override
		public void contentForm(HttpServletRequest req, Model model) {
			int num=Integer.parseInt(req.getParameter("num"));
			int pageNum=Integer.parseInt(req.getParameter("pageNum"));
			int number=Integer.parseInt(req.getParameter("number"));
			BoardVO dto=new BoardVO();
			
			//dao 생성(싱글톤, 다형성)
			BoardDAO dao=new BoardDAOImpl();
			
			//조회수 증가
			dao.addReadCnt(num);
			
			//상세페이지 가져오기 ..1건
			dto=dao.getArticleContent(num);
					
			//jsp로 값을 넘긴다. dto, pageNum, number
			req.setAttribute("dto", dto);
			req.setAttribute("pageNum", pageNum);
			req.setAttribute("number", number); //db 번호가 아닌 순서별로 새로 지정한 번호
		}

		// 글수정 상세 페이지
		@Override
		public void modifyView(HttpServletRequest req, Model model) {
			//넘겨받은 값 가져온다.
			int num=Integer.parseInt(req.getParameter("num"));
			int pageNum=Integer.parseInt(req.getParameter("pageNum"));
			String pwd=req.getParameter("pwd");
			BoardVO dto=new BoardVO();

			//dao 객체 생성(싱글톤, 다형성 적용)
			BoardDAO dao=new BoardDAOImpl();
			
			//패스워드가 일치하면 selectCnt=1, 불일치시 selectCnt =0;
			Map map = new HashMap();
			map.put("num", num);
			map.put("pwd", pwd);
			
			
			int selectCnt = dao.pwdCheck(map);
			
			System.out.println("selectCnt:"+selectCnt);
			
			//패스워드가 일치하면 num과 일치하는 게시글 1건을 읽어온다.
			if(selectCnt==1) {
				System.out.println("selectCnt:"+selectCnt);
				dto=dao.getArticleContent(num);
			
				//jsp에 값들을 넘긴다.
				req.setAttribute("dto", dto);
			}
			req.setAttribute("selectCnt", selectCnt);
			req.setAttribute("num", num);
			req.setAttribute("pageNum", pageNum);
			
		}

		@Override
		public void modifyPro(HttpServletRequest req, Model model) {
			
			//넘겨받은 값 가져온다.
			int cnt=0;
			int num=Integer.parseInt(req.getParameter("num"));
			int pageNum=Integer.parseInt(req.getParameter("pageNum"));
			String subject=req.getParameter("subject");
			String content=req.getParameter("content");
			String pwd=req.getParameter("pwd");
			
			//바구니 생성
			BoardVO dto=new BoardVO();
			
			//바구니에 화면에서 입력받은 값들을 담는다.
			dto.setNum(num);
			dto.setSubject(subject);
			dto.setContent(content);
			dto.setPwd(pwd);
			
			//dao 객체 생성(싱글톤, 다형성 적용)
			BoardDAO dao=new BoardDAOImpl();
			
			//수정처리
			dao.updateModify(dto);
			
			//jsp에 값을 넘긴다.
			req.setAttribute("num", num);
			req.setAttribute("pageNum", pageNum);
			req.setAttribute("dto", dto);
		}

		@Override
		public void writePro(HttpServletRequest req, Model model) {
			//넘겨받은 값 가져온다.
			int num=Integer.parseInt(req.getParameter("num"));
			int ref=Integer.parseInt(req.getParameter("ref"));
			int ref_step=Integer.parseInt(req.getParameter("ref_step"));
			int ref_level=Integer.parseInt(req.getParameter("ref_level"));
			
			String id=(String) req.getSession().getAttribute("memId");
			String writer=req.getParameter("writer");
			String subject=req.getParameter("subject");
			String pwd=req.getParameter("pwd");
			String content=req.getParameter("content");
			
			//바구니 생성
			BoardVO dto=new BoardVO();
			
			//바구니에 화면에서 입력받은 값들을 담는다.
			dto.setNum(num);
			dto.setId(id);
			dto.setRef(ref);
			dto.setRef_step(ref_step);
			dto.setRef_level(ref_level);
			dto.setWriter(writer);
			dto.setPwd(pwd);
			dto.setSubject(subject);
			dto.setContent(content);
			
			
			//dao 객체 생성(싱글톤, 다형성 적용)
			BoardDAO dao=new BoardDAOImpl();
			
			//db에 저장(insert)
			int cnt=dao.insertBoard(dto);
			
			//jsp에 값을 넘긴다.
			req.setAttribute("dto", dto);
			req.setAttribute("cnt", cnt);
		}

		@Override
		public void deletePro(HttpServletRequest req, Model model) {
			//넘겨받은 값 가져온다.
			int num=Integer.parseInt(req.getParameter("num"));
			int pageNum=Integer.parseInt(req.getParameter("pageNum"));
			String pwd=req.getParameter("pwd");
		
			//dao 객체 생성(싱글톤, 다형성 적용)
			BoardDAO dao=new BoardDAOImpl();
			Map map = new HashMap();
			map.put("num", num);
			map.put("pwd", pwd);
			//num이 데이터베이스에 있을때 비밀번호가 일치하는지 확인
			BoardVO dto=new BoardVO();
			
			int selectCnt=dao.pwdCheck(map);
			
			/*
			 * deleteCnt = -1; : 답글이 있는 경우 삭제 안함
			 * deleteCnt =  0; : 답글이 없는 경우 삭제 실패
			 * deleteCnt =  1; : 답글이 없는 경우 삭제 성공
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
			int pageSize  = 4;	//한 페이지당 출력한 글 갯수
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
			BoardDAO dao=new BoardDAOImpl();
			
			//글갯수 구하기	
			String boardSerch="%"+serch+"%";
			cnt=dao.getSArticleCnt(boardSerch);
			
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
				map.put("boardSerch", boardSerch);
				ArrayList<BoardVO> dtos=dao.getSArticleList(map);
				req.setAttribute("dtosB", dtos);
				
			}
			
			startPage=(currentPage/pageBlock)*pageBlock+1; //시작페이지=(현재 페이지/한 블럭당 페이지 갯수)*한 블럭당 페이지 갯수+1;
			if(currentPage % pageBlock ==0)startPage -= pageBlock; //(현재페이지%한 블럭당 페이지 갯수==0)
			
			endPage=startPage+pageBlock-1; //마지막페이지=시작페이지+한 블럭당 페이지 갯수-1;
			if(endPage > pageCount)endPage = pageCount; 
			
			req.setAttribute("cntB", cnt); //글갯수
			req.setAttribute("numberB", number);//글번호
			req.setAttribute("pageNumB", pageNum); //페이지번호
			
			if(cnt>0) {
				req.setAttribute("startPageB", startPage); //시작 페이지
				req.setAttribute("endPageB", endPage); //마지막 페이지
				req.setAttribute("pageBlockB", pageBlock); //출력할 페이지 갯수
				req.setAttribute("pageCountB", pageCount); //페이지 갯수
				req.setAttribute("currentPageB",currentPage);//현재 페이지
			}
			
		}	
	}
