package com.vil.pjt.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.vil.pjt.domain.SampleVO;

@Repository
public class SampleDAOImpl implements SampleDAO {
	@Inject
	private SqlSession session;
	
	private final String namespace = "com.vil.pjt.mappers.SampleMapper";
	@Override
	public String getTime() {
		return session.selectOne(namespace + ".getTime");
	}

	@Override
	public void insertSample(SampleVO vo) {
		session.insert(namespace + ".insertSample", vo);
	}

	@Override
	public SampleVO selectSample(String id) throws Exception {
		return session.selectOne(namespace + ".selectSample", id);
	}

	@Override
	public SampleVO readWithPw(String id, String pw) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		map.put("id", id);
		map.put("pw", pw);
		
		return session.selectOne(namespace + ".readWithPw", map);
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void updateSample(SampleVO vo) {
		session.update(namespace + ".updateSample", vo);
	}

	@Override
	public void deleteSample(String id) throws Exception {
		session.delete(namespace + ".deleteSample");
	}

	@Override
	public List<SampleVO> listSample() {
		List<SampleVO> list = session.selectList(namespace + ".selectList");
		
		return list;
	}

}
