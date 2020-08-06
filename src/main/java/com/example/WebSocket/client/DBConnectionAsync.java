package com.example.WebSocket.client;

import java.util.List;

import com.example.WebSocket.shared.Message;
import com.example.WebSocket.shared.RegisteredUser;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DBConnectionAsync {
	public void register(String user, String pass, AsyncCallback<User> callback);

	void generateToken(String username, String password, String room, AsyncCallback<String> callback)
			throws IllegalArgumentException;

	void getMessage(Message message, AsyncCallback<Message> callback);

	void validateToken(String username, AsyncCallback<Boolean> callback);

	public void getChatHistory(String chatRoom, AsyncCallback<List<RegisteredUser>> asyncCallback);
}