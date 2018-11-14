package com.study.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.study.board.domain.ReplyVO;
import com.study.board.framework.paging.PageCriteria;
import com.study.board.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Inject
	private ReplyDAO dao;
	
	@Override
	public void addReply(ReplyVO vo) throws Exception {
		dao.createReply(vo);
	}

	@Override
	public List<ReplyVO> listReplyAll(Integer bno) throws Exception {
		return dao.listReplyAll(bno);
	}
	@Override
	public List<ReplyVO> listReplyPage(Integer bno, PageCriteria cri) throws Exception {
		return dao.listReplyPage(bno, cri);
	}
	@Override
	public int replyCount(Integer bno) throws Exception {
		return dao.replyCount(bno);
	}

	@Override
	public void modifyReply(ReplyVO vo) throws Exception {
		dao.updateReply(vo);
	}

	@Override
	public void removeReply(Integer rno) throws Exception {
		dao.deleteReply(rno);
	}


}
