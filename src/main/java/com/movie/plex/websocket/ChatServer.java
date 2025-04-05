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
		System.out.println("Ŭ���̾�Ʈ�� �����߽��ϴ�.");
	}
	
	@OnMessage
	public void handleMasseage(String msg, Session session) {
		System.out.println(msg);
		
		Gson gson = new Gson();
		
	 	Message message =  gson.fromJson(msg, Message.class);
		
	 	if (message.getCode().equals("1")) {
//	 		int usergrade = getUserGradeFromSession(session);
//	 		userGrades.put(session, usergrade);
//	 		System.out.println("����� ��� ����: " + usergrade);
//	 		
//	        if (usergrade != 4) { // �����ڰ� �ƴ� ���
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
	 		//���� ������� ������ ������� �����Ѵ�.
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
//	        // HttpSession�� Ȱ���� ����� ������ �������� ���
//	        // WebSocket������ ���� HttpSession�� ������ �� �����Ƿ�, ����Ʈ���忡�� usergrade�� �����ϵ��� �ؾ� ��
//	        return 0; // �⺻�� (�����ڰ� �ƴ�)
//	    }
//	  
//	  private void sendToAdmins(String message) {
//		    for (Session s : sessionList) {
//		        if (userGrades.getOrDefault(s, 0) == 4) { // usergrade == 4 (������)
//		            try {
//		                s.getBasicRemote().sendText(message);
//		            } catch (Exception e) {
//		                e.printStackTrace();
//		            }
//		        }
//		    }
//		}
	
	//�����ڸ� Ȯ���ϴ� �޼���
	private void checkSessionList() {
		System.out.println();
		System.out.println("[Session List]");
		for (Session session : sessionList) {
			System.out.println(session.getId());
		}
		System.out.println();
	}
	
	private void clearSessionList() {
		
		//List �迭�� �÷����� ���� for�� ������ ��� �߰�/�����ϴ� �ൿ�� �� �� ����.
		//������ ����� 1. �Ϲ� forans, 2. Interator ����� �ִ�.
		Iterator<Session> iter = sessionList.iterator();
		
		while(iter.hasNext()) {
			if(!(iter.next()).isOpen()) {
				//Ȥ�� ������ ������ ������ ������ ����Ʈ���� �����Ѵ�.
				iter.remove();
			}
		}
		
	}
	
	@OnError
	public void handleError(Throwable e) {
		System.out.println("���� �߻� " + e.getMessage());
	}
}
