package com.vil.pjt.persistence;

import java.util.List;

import com.vil.pjt.domain.SampleVO;

public interface SampleDAO {
	public String getTime();
	public void insertSample(SampleVO vo);
	public SampleVO selectSample(String id) throws Exception;
	public SampleVO readWithPw(String id, String pw) throws Exception;
	public void updateSample(SampleVO vo) throws Exception;
	public void deleteSample(String id) throws Exception;
	public List<SampleVO> listSample();
}
