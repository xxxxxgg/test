package com.study.board.persistence;

import java.util.List;

import com.study.board.domain.ReplyVO;
import com.study.board.framework.paging.PageCriteria;

public interface ReplyDAO {
	public List<ReplyVO> listReplyAll(Integer bno) throws Exception;
	public List<ReplyVO> listReplyPage(Integer bno, PageCriteria cri) throws Exception;
	public int replyCount(Integer bno) throws Exception;
	
	public void createReply(ReplyVO vo) throws Exception;
	
	public void updateReply(ReplyVO vo) throws Exception;

	public void deleteReply(Integer rno) throws Exception;
}
