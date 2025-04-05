package com.movie.plex.websocket;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

@ServerEndpoint("/chatServer")
public class ChatServer {
	
	private static List<Session> sessionList = new ArrayList<Session>();
	private static Map<Session, Integer> userGrades = new ConcurrentHashMap();
	
	@OnOpen
	public void handelOpen(Session session) {
		sessionList.add(session);
		checkSessionList();	
		clearSessionList();
	}
	
	@OnClose
	public void handleClose() {
		System.out.println("클라이언트가 종료했습니다.");
	}
	
	@OnMessage
	public void handleMasseage(String msg, Session session) {
		System.out.println(msg);
		
		Gson gson = new Gson();
		
	 	Message message =  gson.fromJson(msg, Message.class);
		
	 	if (message.getCode().equals("1")) {
//	 		int usergrade = getUserGradeFromSession(session);
//	 		userGrades.put(session, usergrade);
//	 		System.out.println("사용자 등급 저장: " + usergrade);
//	 		
//	        if (usergrade != 4) { // 관리자가 아닌 경우
//	            String notification = "New user connected: " + session.getId();
//	            sendToAdmins(notification);
//	        }
	 		for (Session s : sessionList) {
	 			if (s != session) {
	 				try {
	 					s.getBasicRemote().sendText(msg);
					} catch (Exception e) {
						e.printStackTrace();
					}
	 			}
	 		}
	 	} else if (message.getCode().equals("2")) { 
	 		sessionList.remove(session);
	 		for (Session s : sessionList) {
	 			try {
	 				s.getBasicRemote().sendText(msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
	 		}
	 	} else if (message.getCode().equals("3")) {	
	 		//보낸 사람빼고 나머지 사람에게 전달한다.
	 		for (Session s : sessionList) {
	 			if (s != session) { //if (userGrades.getOrDefault(s, 0) == 4)
	 				try {
						s.getBasicRemote().sendText(msg);
					} catch (Exception e) {
						e.printStackTrace();
					}
	 			}
	 		}
	 		
	 	}  
	}
	
//	  private int getUserGradeFromSession(Session session) {
//	        // HttpSession을 활용해 사용자 정보를 가져오는 방법
//	        // WebSocket에서는 직접 HttpSession을 접근할 수 없으므로, 프론트엔드에서 usergrade를 전송하도록 해야 함
//	        return 0; // 기본값 (관리자가 아님)
//	    }
//	  
//	  private void sendToAdmins(String message) {
//		    for (Session s : sessionList) {
//		        if (userGrades.getOrDefault(s, 0) == 4) { // usergrade == 4 (관리자)
//		            try {
//		                s.getBasicRemote().sendText(message);
//		            } catch (Exception e) {
//		                e.printStackTrace();
//		            }
//		        }
//		    }
//		}
	
	//접속자를 확인하는 메서드
	private void checkSessionList() {
		System.out.println();
		System.out.println("[Session List]");
		for (Session session : sessionList) {
			System.out.println(session.getId());
		}
		System.out.println();
	}
	
	private void clearSessionList() {
		
		//List 계열의 컬렉션은 향상된 for문 내에서 요소 추가/삭제하는 행동을 할 수 없다.
		//가능한 방법은 1. 일반 forans, 2. Interator 방법이 있다.
		Iterator<Session> iter = sessionList.iterator();
		
		while(iter.hasNext()) {
			if(!(iter.next()).isOpen()) {
				//혹시 연결이 끊어진 세션이 있으면 리스트에서 제거한다.
				iter.remove();
			}
		}
		
	}
	
	@OnError
	public void handleError(Throwable e) {
		System.out.println("에러 발생 " + e.getMessage());
	}
}
