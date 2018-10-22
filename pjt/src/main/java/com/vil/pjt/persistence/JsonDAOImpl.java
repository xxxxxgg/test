package com.vil.pjt.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class JsonDAOImpl implements JsonDAO {
	@Inject
	private SqlSession session;
	
	private static String namespace = "com.vil.pjt.mappers.SampleMapper";
	
	@Override
	public void updateJson(String mid, int point) throws Exception {
		Map<String, Object> map = new HashMap<>();
		
		map.put("mid", mid);
		map.put("point", point);
		
		session.update(namespace + ".updatePoint", map);
	}

}
