package com.movie.plex.websocket;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ChatDAO {
	@Autowired
	private SqlSession sqlSession;
	private final String NAMESPACE = "com.movie.plex.websocket.ChatDAO.";
	
	public List<ChatRoom> chatRoomList() throws Exception {
		return sqlSession.selectList(NAMESPACE+"chatRoomList");
	}
	
	public int addChatRoom(ChatRoom chatRoom) throws Exception {
		return sqlSession.insert(NAMESPACE+"addChatRoom", chatRoom);
	}
	
	public Long addCheck(ChatRoomJoin chatRoomJoin) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"addCheck",chatRoomJoin);
	}
	
	public int addChatRoomDetail(ChatRoomJoin chatRoomJoin) throws Exception {
		return sqlSession.insert(NAMESPACE+"addChatRoomDetail", chatRoomJoin);
	}
	
	public List<ChatMessage> chatMessage(int chatRoomNo) throws Exception {
		return sqlSession.selectList(NAMESPACE+"chatMessage", chatRoomNo);
	}
	
	public int insertMessage(ChatMessage chatMessage) throws Exception {
		return sqlSession.insert(NAMESPACE+"insertMessage", chatMessage);
	}
	
	public List<ChatRoom> getChatRoomJoin(ChatRoomJoin chatRoomJoin) throws Exception {
		return sqlSession.selectList(NAMESPACE+"getChatRoomJoin", chatRoomJoin);
	}
	
	public int exitChatRoom(ChatRoomJoin chatRoomJoin) throws Exception {
		return sqlSession.delete(NAMESPACE+"exitChatRoom", chatRoomJoin);
	}
	
	public int closeChatRoom(ChatRoomJoin chatRoomJoin) throws Exception {
		return sqlSession.update(NAMESPACE+"closeChatRoom", chatRoomJoin);
	}
	
	public int countChatRoomMember(ChatRoomJoin chatRoomJoin) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"countChatRoomMember", chatRoomJoin);
	}
}
