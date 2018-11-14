package com.study.board.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.study.board.domain.MessageVO;

@Repository
public class MessageDAOImpl implements MessageDAO {
	@Inject
	private SqlSession session;
	private String namespace = "p2_02_basic_board.mapper.messageMapper";
	
	@Override
	public void createMessage(MessageVO vo) throws Exception {
		session.insert(namespace + ".createMessage", vo);
	}

	@Override
	public MessageVO readMessage(Integer mno) throws Exception {
		return session.selectOne(namespace + ".readMessage", mno);
	}

	@Override
	public void updateState(Integer mno) throws Exception {
		session.update(namespace + ".updateState", mno);
	}

}
