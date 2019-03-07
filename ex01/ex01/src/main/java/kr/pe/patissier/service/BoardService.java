package kr.pe.patissier.service;

import java.util.List;
import java.util.Map;

import kr.pe.patissier.domain.BoardVO;
import kr.pe.patissier.domain.Criteria;

public interface BoardService {

	public void regist(BoardVO board) throws Exception;

	public BoardVO read(Integer bno) throws Exception;

	public void modify(BoardVO board) throws Exception;

	public void modify2(Map map) throws Exception;

	public void remove(Integer bno) throws Exception;

	public void remove2(Map map) throws Exception;

	public List<BoardVO> listAll() throws Exception;

	public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	
	public int listCountCriteria(Criteria cri) throws Exception;

}
