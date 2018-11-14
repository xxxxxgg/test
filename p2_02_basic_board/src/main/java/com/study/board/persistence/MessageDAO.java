package com.study.board.persistence;

import com.study.board.domain.MessageVO;

public interface MessageDAO {
	public void createMessage(MessageVO vo) throws Exception;
	
	public MessageVO readMessage(Integer mno) throws Exception;
	
	public void updateState(Integer mno) throws Exception;
}
