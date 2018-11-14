package com.study.legacy.persistence;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.study.legacy.domain.AppearanceVO;

@Repository
public class AppearanceDAOImpl implements AppearanceDAO {
	@Inject
	private SqlSession sqlSession;

	private static final String namespace = "p2_02_basic_board.mapper.InvertedIndexMapper";

	@Override
	public void createAppearanceByBatch(Map<String, List<AppearanceVO>> map) throws Exception {
		sqlSession.insert(namespace + ".createAppearanceByBatch", map);
	}
	
}
