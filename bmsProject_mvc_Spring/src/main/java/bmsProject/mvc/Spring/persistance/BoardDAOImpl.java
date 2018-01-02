package bmsProject.mvc.Spring.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import bmsProject.mvc.Spring.config.Configuration;
import bmsProject.mvc.Spring.vo.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO{

	
	@Override
	public int getArticleCnt() {
		int cnt=0;
		BoardDAO dao=Configuration.getMapper(BoardDAO.class);
		cnt=dao.getArticleCnt();
		return cnt;
	}

	//게시글 목록 조회
	@Override
	public ArrayList<BoardVO> getArticleList(Map<String, Integer> map) {
		
		ArrayList<BoardVO> dtos=null; //큰 바구니
		BoardDAO dao=Configuration.getMapper(BoardDAO.class);
		System.out.println("BoardDAOImpl - getArticleList");
		dtos=dao.getArticleList(map);
	
		return dtos;
	}

	@Override
	public BoardVO getArticleContent(int num) {
		
		BoardDAO dao=Configuration.getMapper(BoardDAO.class);
		BoardVO dto=dao.getArticleContent(num);
		
		return dto;
	}

	@Override
	public void addReadCnt(int num) {
		BoardDAO dao=Configuration.getMapper(BoardDAO.class);
		dao.addReadCnt(num);
		
	}
	@Override
	public int pwdCheck(Map map) {
		BoardDAO dao=Configuration.getMapper(BoardDAO.class);
		BoardVO dto=dao.Check(map);
		int selectCnt=0;
		if(dto.getNum()>0) {
			if(map.get("pwd").equals(dto.getPwd())) {
				selectCnt=1;
			}
		}
		return selectCnt;
	}
	
	@Override
	public BoardVO Check(Map map) {
		
		BoardDAO dao=Configuration.getMapper(BoardDAO.class);
		BoardVO dto=dao.Check(map);
		
		return dto;
		
	}

	@Override
	public int updateModify(BoardVO dto) {
		int cnt=0;
		
		BoardDAO dao=Configuration.getMapper(BoardDAO.class);
		cnt=dao.updateModify(dto);
		
		return cnt;
	}

	// 답변글인 경우
	@Override
	public void updateReply(BoardVO vo) {
		// 삽입할 글보다 아래쪽 글들을 update
		BoardDAO dao=Configuration.getMapper(BoardDAO.class);
		dao.updateReply(vo);
	}
	
	@Override
	public int insertBoard(BoardVO dto) {
		int cnt = 0;
		BoardDAO dao=Configuration.getMapper(BoardDAO.class);
		
			int num = dto.getNum();
			int ref = dto.getRef();
			int ref_step = dto.getRef_step();
			int ref_level = dto.getRef_level();
			
			// 답변글이 아닌 경우(제목글인 경우)
			if(num == 0) {
				// sql = "SELECT MAX(num) FROM mvc_board"; //최신글부터 가져온다.(최신글부터 뿌려주므로 게시글작성시 글번호는 최신글번호이어야 한다.)			
				cnt = getArticleCnt();
								
				// 첫글이 아닌 경우
				if(cnt > 0) {
					ref = cnt + 1; // ref = MAX(num) + 1;
				// 첫글인 경우
				} else {
					ref = 1;
				}	
				
				ref_step = 0;
				ref_level = 0;
			
			// 답변글인 경우
			} else {	
				// 삽입할 글보다 아래쪽 글들을 update
				//sql = "UPDATE mvc_board SET ref_step = ref_step+1 WHERE ref=? AND ref_step > ?";
				updateReply(dto);
				
				ref_step++;
				ref_level++;
			}
			
			// 주의 사항
			if(dto.getContent()==null)dto.setContent(" ");
			dto.setRef(ref);
			dto.setRef_step(ref_step);
			dto.setRef_level(ref_level);
		
			cnt=dao.insertBoard(dto);
			
		return cnt;
	}

	//답글존재여부 확인
	@Override
	public int checkReply(BoardVO vo) {
		int cnt = 0;
		BoardDAO dao=Configuration.getMapper(BoardDAO.class);
		cnt = dao.checkReply(vo);
		return cnt;
	}
	//ref 업데이트
	@Override
	public void updateRef(BoardVO vo) {
		BoardDAO dao=Configuration.getMapper(BoardDAO.class);
		dao.updateRef(vo);
	}
	//글 삭제 체크
	@Override
	public int deleteBoard(int num) {
		System.out.println("BoardDAOImpl - deleteBoard");
		BoardDAO dao=Configuration.getMapper(BoardDAO.class);
		int cnt = 0;
		//상세조회
		BoardVO vo = dao.getArticleContent(num);
		//답글이 존재하는지 여부 확인 ->메소드 하나로 만들겠다.
		cnt = dao.checkReply(vo);
		
		//답글이 존재하는 경우 삭제하지 않겠다.
		if(cnt!=0) {
			cnt = -1;
		}
		//답글이 없는 경우 삭제(제목글, 답글없는 게시글)
		else {
			//1.삭제 글보다 순위가 아래인 글들은 step-1을 해서 올린다.
			dao.updateRef(vo);
			//2.삭제 작업
			cnt = dao.deleteBoard(num);
		}
		return cnt;
	}

	@Override
	public int getSArticleCnt(String boardSerch) {
		int cnt = 0;
		BoardDAO dao=Configuration.getMapper(BoardDAO.class);
		cnt=dao.getSArticleCnt(boardSerch);
		
		return cnt;
	}

	@Override
	public ArrayList<BoardVO> getSArticleList(Map map) {
		
		ArrayList<BoardVO> dtos=null; //큰 바구니
		BoardDAO dao=Configuration.getMapper(BoardDAO.class);
		dtos=dao.getSArticleList(map);
	
		return dtos;
	}


}
