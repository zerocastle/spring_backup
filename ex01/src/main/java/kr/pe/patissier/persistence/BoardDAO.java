package kr.pe.patissier.persistence;

import java.util.List;
import java.util.Map;

import kr.pe.patissier.domain.BoardVO;

public interface BoardDAO {

	public void create(BoardVO vo) throws Exception;

	public BoardVO read(Integer bno) throws Exception;

	public void update(BoardVO vo) throws Exception;

	public void delete(Map bno) throws Exception;

	public List<BoardVO> listAll() throws Exception;

}
