package com.vil.pjt.service;

import java.util.List;

import com.vil.pjt.domain.SampleVO;

public interface SampleService {
	public void regist(SampleVO vo) throws Exception;
	public SampleVO read(String id) throws Exception;
	public void modify(SampleVO vo) throws Exception;
	public void remove(String id) throws Exception;
	public List<SampleVO> listAll() throws Exception;
	public void addTransaction(SampleVO vo) throws Exception;
}
