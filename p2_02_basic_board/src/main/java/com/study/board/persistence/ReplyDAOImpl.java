package com.study.board.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.study.board.domain.ReplyVO;
import com.study.board.framework.paging.PageCriteria;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	@Inject
	private SqlSession session;
	
	private static String namespace = "p2_02_basic_board.mapper.replyMapper";
	
	@Override
	public List<ReplyVO> listReplyAll(Integer bno) throws Exception {
		return session.selectList(namespace + ".listReplyAll", bno);
	}
	@Override
	public List<ReplyVO> listReplyPage(Integer bno, PageCriteria cri) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("bno", bno);
		paramMap.put("cri", cri);
		
		return session.selectList(namespace + ".listReplyPage", paramMap);
	}
	@Override
	public int replyCount(Integer bno) throws Exception {
		return session.selectOne(namespace + ".replyCount", bno);
	}

	@Override
	public void createReply(ReplyVO vo) throws Exception {
		session.insert(namespace + ".createReply", vo);
	}

	@Override
	public void updateReply(ReplyVO vo) throws Exception {
		session.update(namespace + ".updateReply", vo);
	}

	@Override
	public void deleteReply(Integer rno) throws Exception {
		session.delete(namespace + ".deleteReply", rno);
	}
}
