package kr.pe.patissier.persistence;

import java.util.List;
import java.util.Map;

import kr.pe.patissier.domain.BoardVO;
import kr.pe.patissier.domain.Criteria;

public interface BoardDAO {

	public void create(BoardVO vo) throws Exception;

	public BoardVO read(Integer bno) throws Exception;

	public void update(BoardVO vo) throws Exception;

	public void update2(Map map) throws Exception;

	public void delete(Integer bno) throws Exception;

	public void delete2(Map map) throws Exception;

	public List<BoardVO> listAll() throws Exception;

	public List<BoardVO> listPage(int page) throws Exception;

	public List<BoardVO> listCriteria(Criteria cri) throws Exception;

	public int countPagin(Criteria cri) throws Exception;

}
