package com.study.board.service;

import java.util.List;

import com.study.board.domain.ReplyVO;
import com.study.board.framework.paging.PageCriteria;

public interface ReplyService {
	public void addReply(ReplyVO vo) throws Exception;
	
	public List<ReplyVO> listReplyAll(Integer bno) throws Exception;
	public List<ReplyVO> listReplyPage(Integer bno, PageCriteria cri) throws Exception;
	public int replyCount(Integer bno) throws Exception;
	
	public void modifyReply(ReplyVO vo) throws Exception;
	
	public void removeReply(Integer rno) throws Exception;
}
