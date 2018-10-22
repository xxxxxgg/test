package com.vil.pjt.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vil.pjt.domain.SampleVO;
import com.vil.pjt.persistence.SampleDAO;
import com.vil.pjt.persistence.JsonDAO;

@Service
public class SampleServiceImpl implements SampleService {
	@Inject
	private SampleDAO dao;
	@Inject
	private JsonDAO jdao;
	
	@Override
	public void regist(SampleVO vo) throws Exception {
		dao.insertSample(vo);
	}

	@Override
	public SampleVO read(String id) throws Exception {
		return dao.selectSample(id);
	}

	@Override
	public void modify(SampleVO vo) throws Exception {
		dao.updateSample(vo);
	}

	@Override
	public void remove(String id) throws Exception {
		dao.deleteSample(id);
	}

	@Override
	public List<SampleVO> listAll() throws Exception {
		return dao.listSample();
	}

	@Transactional
	@Override
	public void addTransaction(SampleVO vo) throws Exception {
		dao.insertSample(vo);
		jdao.updateJson("userj", 10);
	}
}
