package com.study.board.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.study.board.domain.MessageVO;
import com.study.board.persistence.MessageDAO;
import com.study.board.persistence.UserDAO;

@Service
public class MessageServiceImpl implements MessageService {
	@Inject
	private MessageDAO mdao;
	@Inject
	private UserDAO udao;
	
	@Override
	public void addMessage(MessageVO vo) throws Exception {
		mdao.createMessage(vo);
		udao.updatePoint(vo.getSender(), 10);
	}

	@Override
	public MessageVO readMessage(String uid, Integer mno) throws Exception {
		mdao.updateState(mno);
		udao.updatePoint(uid, 5);
		
		return mdao.readMessage(mno);
	}

}
