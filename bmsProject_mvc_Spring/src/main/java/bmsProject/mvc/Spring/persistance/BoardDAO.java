package bmsProject.mvc.Spring.persistance;

import java.util.ArrayList;
import java.util.Map;

import bmsProject.mvc.Spring.vo.BoardVO;

public interface BoardDAO {
	//글 갯수 구하기
	public int getArticleCnt();
	//검색한값의 글 갯수 구하기
	public int getSArticleCnt(String boardSerch);
	//게시글 목록 조회
	public ArrayList<BoardVO> getArticleList(Map<String, Integer> map);
	//검색한 값에 맞는 게시글 목록 조회
	public ArrayList<BoardVO> getSArticleList(Map map);
	//상세페이지 정보 조회
	public BoardVO getArticleContent(int num);
	
	//조회수 증가
	public void addReadCnt(int num);
	
	//비밀번호 체크
	public int pwdCheck(Map map);
	
	public BoardVO Check(Map map);
	
	//게시판 글 수정
	public int updateModify(BoardVO dto);
	
	public int checkReply(BoardVO vo);
	
	public void updateRef(BoardVO vo);
	
	public void updateReply(BoardVO vo);
	
	//게시판 글 생성
	public int insertBoard(BoardVO dto);
	
	//게시글 삭제
	public int deleteBoard(int num);
}
