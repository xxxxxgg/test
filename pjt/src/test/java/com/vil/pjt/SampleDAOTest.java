package com.vil.pjt;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vil.pjt.domain.SampleVO;
import com.vil.pjt.persistence.SampleDAO;
import com.vil.pjt.service.SampleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })
public class SampleDAOTest {
	@Inject
	private SampleDAO dao;
	
	@Inject
	private SampleService service;
	
	//@Test
	public void testTime() throws Exception {
		System.out.println(dao.getTime());
	}
	
	/**
	 * mysql connector-java version 8.0.11
	 * @throws Exception
	 */
	//@Test
	public void testInsertMember() throws Exception {
		SampleVO vo = new SampleVO();
		vo.setMid("user001");
		vo.setMpw("user001");
		vo.setMname("USER001");
		vo.setMemail("user001@aaa.com");
		
		dao.insertSample(vo);
	}
	
	//@Test
	public void testSelectSample() throws Exception {
		System.out.println(dao.selectSample("user00"));
	}
	
	//@Test
	public void testReadWithPw() throws Exception {
		System.out.println(dao.readWithPw("user00", "user00"));
	}
	
	//@Test
	public void testUpdateSample() throws Exception {
		SampleVO vo = new SampleVO();
		vo.setMid("user001");
		vo.setMpw("user001");
		vo.setMname("USER001");
		vo.setMemail("user001@aaa.com");
		
		service.modify(vo);
	}
	
	//@Test
	public void testDeleteSample() throws Exception {
		String id = "user001"; 
		service.remove(id);
	}
	
	@Test
	public void testSelectList() throws Exception {
		service.listAll();
	}
}
