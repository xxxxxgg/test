package com.study.legacy.persistence;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.study.legacy.domain.LexiconVO;

public interface LexiconDAO {
	public List<LexiconVO> findLexicon(Map<String, List<String>> map) throws Exception;	
	
	public void createLexiconByBatch(Map<String, List<LexiconVO>> map) throws Exception;
}
