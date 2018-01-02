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

	//�Խñ� ��� ��ȸ
	@Override
	public ArrayList<BoardVO> getArticleList(Map<String, Integer> map) {
		
		ArrayList<BoardVO> dtos=null; //ū �ٱ���
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

	// �亯���� ���
	@Override
	public void updateReply(BoardVO vo) {
		// ������ �ۺ��� �Ʒ��� �۵��� update
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
			
			// �亯���� �ƴ� ���(������� ���)
			if(num == 0) {
				// sql = "SELECT MAX(num) FROM mvc_board"; //�ֽűۺ��� �����´�.(�ֽűۺ��� �ѷ��ֹǷ� �Խñ��ۼ��� �۹�ȣ�� �ֽű۹�ȣ�̾�� �Ѵ�.)			
				cnt = getArticleCnt();
								
				// ù���� �ƴ� ���
				if(cnt > 0) {
					ref = cnt + 1; // ref = MAX(num) + 1;
				// ù���� ���
				} else {
					ref = 1;
				}	
				
				ref_step = 0;
				ref_level = 0;
			
			// �亯���� ���
			} else {	
				// ������ �ۺ��� �Ʒ��� �۵��� update
				//sql = "UPDATE mvc_board SET ref_step = ref_step+1 WHERE ref=? AND ref_step > ?";
				updateReply(dto);
				
				ref_step++;
				ref_level++;
			}
			
			// ���� ����
			if(dto.getContent()==null)dto.setContent(" ");
			dto.setRef(ref);
			dto.setRef_step(ref_step);
			dto.setRef_level(ref_level);
		
			cnt=dao.insertBoard(dto);
			
		return cnt;
	}

	//������翩�� Ȯ��
	@Override
	public int checkReply(BoardVO vo) {
		int cnt = 0;
		BoardDAO dao=Configuration.getMapper(BoardDAO.class);
		cnt = dao.checkReply(vo);
		return cnt;
	}
	//ref ������Ʈ
	@Override
	public void updateRef(BoardVO vo) {
		BoardDAO dao=Configuration.getMapper(BoardDAO.class);
		dao.updateRef(vo);
	}
	//�� ���� üũ
	@Override
	public int deleteBoard(int num) {
		System.out.println("BoardDAOImpl - deleteBoard");
		BoardDAO dao=Configuration.getMapper(BoardDAO.class);
		int cnt = 0;
		//����ȸ
		BoardVO vo = dao.getArticleContent(num);
		//����� �����ϴ��� ���� Ȯ�� ->�޼ҵ� �ϳ��� ����ڴ�.
		cnt = dao.checkReply(vo);
		
		//����� �����ϴ� ��� �������� �ʰڴ�.
		if(cnt!=0) {
			cnt = -1;
		}
		//����� ���� ��� ����(�����, ��۾��� �Խñ�)
		else {
			//1.���� �ۺ��� ������ �Ʒ��� �۵��� step-1�� �ؼ� �ø���.
			dao.updateRef(vo);
			//2.���� �۾�
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
		
		ArrayList<BoardVO> dtos=null; //ū �ٱ���
		BoardDAO dao=Configuration.getMapper(BoardDAO.class);
		dtos=dao.getSArticleList(map);
	
		return dtos;
	}


}
