package kr.pe.patissier.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.pe.patissier.domain.BoardVO;
import kr.pe.patissier.domain.Criteria;

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
		System.out.println("디비 session 으로 넘오 온값 : " + vo.toString());
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
		session.update("board.update2", map);

	}

	@Override
	public List<BoardVO> listPage(int page) throws Exception {
		// TODO Auto-generated method stub
		if (page <= 0) {
			page = 1;
		}
		page = (page - 1) * 10;
		return session.selectList("board.listPage", page);
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList("board.listCriteria", cri);
	}

	@Override
	public int countPagin(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne("board.countPagin",cri);
	}

}
