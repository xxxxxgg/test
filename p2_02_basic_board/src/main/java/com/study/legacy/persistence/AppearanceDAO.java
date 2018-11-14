package com.study.legacy.persistence;

import java.util.List;
import java.util.Map;

import com.study.legacy.domain.AppearanceVO;

public interface AppearanceDAO {
	public void createAppearanceByBatch(Map<String, List<AppearanceVO>> paramMap) throws Exception;
}
