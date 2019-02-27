package kr.pe.patissier.service;

import java.util.List;
import java.util.Map;

import kr.pe.patissier.domain.BoardVO;

public interface BoardService {

	public void regist(BoardVO board) throws Exception;

	public BoardVO read(Integer bno) throws Exception;

	public void modify(BoardVO board) throws Exception;

	public void remove(Map bno) throws Exception;

	public List<BoardVO> listAll() throws Exception;

}
