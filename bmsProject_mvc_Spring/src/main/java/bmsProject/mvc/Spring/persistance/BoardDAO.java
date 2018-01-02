package bmsProject.mvc.Spring.persistance;

import java.util.ArrayList;
import java.util.Map;

import bmsProject.mvc.Spring.vo.BoardVO;

public interface BoardDAO {
	//�� ���� ���ϱ�
	public int getArticleCnt();
	//�˻��Ѱ��� �� ���� ���ϱ�
	public int getSArticleCnt(String boardSerch);
	//�Խñ� ��� ��ȸ
	public ArrayList<BoardVO> getArticleList(Map<String, Integer> map);
	//�˻��� ���� �´� �Խñ� ��� ��ȸ
	public ArrayList<BoardVO> getSArticleList(Map map);
	//�������� ���� ��ȸ
	public BoardVO getArticleContent(int num);
	
	//��ȸ�� ����
	public void addReadCnt(int num);
	
	//��й�ȣ üũ
	public int pwdCheck(Map map);
	
	public BoardVO Check(Map map);
	
	//�Խ��� �� ����
	public int updateModify(BoardVO dto);
	
	public int checkReply(BoardVO vo);
	
	public void updateRef(BoardVO vo);
	
	public void updateReply(BoardVO vo);
	
	//�Խ��� �� ����
	public int insertBoard(BoardVO dto);
	
	//�Խñ� ����
	public int deleteBoard(int num);
}
