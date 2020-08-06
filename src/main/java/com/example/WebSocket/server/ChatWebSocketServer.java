package com.example.WebSocket.server;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.example.WebSocket.shared.Message;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.impl.ServerSerializationStreamReader;
import com.google.gwt.user.server.rpc.impl.ServerSerializationStreamWriter;

@ServerEndpoint(value = "/chat/{room}")
public class ChatWebSocketServer {

	private static Logger logger = Logger.getLogger(ChatWebSocketServer.class
			.getName());
	MySqlConnection mySqlConnection = new MySqlConnection();
	Message sessionUserMessage = new Message();

	@OnMessage
	public void onMessage(String message, Session session) {
		String room = (String) session.getUserProperties().get("room");
		try {
			Message messageDto = deserializeMessage(message);
			String result = serializeMessage(messageDto);
			mySqlConnection.sendMessageHistory(messageDto.getUsername(), messageDto.getData(), messageDto.getTime(),room);
			for (Session s : session.getOpenSessions()) {
				System.out.println("session size:::: " + session.getOpenSessions().size());
				if (s.isOpen() && room.equals(s.getUserProperties().get("room"))) {
					s.getBasicRemote().sendText(result);
				}
			}
		} catch (final Exception e) {
			logger.log(Level.WARNING, "Error on web socket server", e);
		}
	}

	@OnOpen
	public void onOpen(Session session, EndpointConfig conf,@PathParam("room") final String room) {
		session.getUserProperties().put("room", room);
		session.setMaxIdleTimeout(300000);
	}

	private Message deserializeMessage(String data)
			throws SerializationException {
		final ServerSerializationStreamReader streamReader = new ServerSerializationStreamReader(
				Thread.currentThread().getContextClassLoader(),
				new CustomSerializationPolicyProvider());
		streamReader.prepareToRead(data);
		final Message message = (Message) streamReader.readObject();
		return message;
	}

	private String serializeMessage(final Message messageDto)
			throws SerializationException {
		final ServerSerializationStreamWriter serverSerializationStreamWriter = new ServerSerializationStreamWriter(
				new SimpleSerializationPolicy());
		serverSerializationStreamWriter.writeObject(messageDto);
		String result = serverSerializationStreamWriter.toString();
		return result;
	}

	@OnClose
	public void onClose(Session session, EndpointConfig conf) {
		logger.log(Level.INFO, "Commection close for:" + session.getId());
		logger.log(Level.INFO, "Client  Disconnected");
	}
}
