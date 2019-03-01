package kr.pe.patissier.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.pe.patissier.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private SqlSession session;

	@Override
	public void create(BoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.insert("board.create", vo);

	}

	@Override 
	public BoardVO read(Integer bno) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne("board.read", bno);
	}

	@Override
	public void update(BoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		session.update("board.update", vo);

	}

	@Override
	public void delete(Integer bno) throws Exception {
		// TODO Auto-generated method stub
		session.delete("board.delete", bno);

	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		// TODO Auto-generated method stub

		return session.selectList("board.listAll");
	}

	@Override
	public void delete2(Map map) throws Exception {
		// TODO Auto-generated method stub
		session.delete("board.delete2", map);
	}

	@Override
	public void update2(Map map) throws Exception {
		// TODO Auto-generated method stub
		session.update("board.update2",map);
		
	}

}
