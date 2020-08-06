package com.example.WebSocket.client;

import java.util.List;

import com.example.WebSocket.shared.Message;
import com.example.WebSocket.shared.RegisteredUser;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("generate")
public interface DBConnection extends RemoteService {

	public User register(String user, String pass);

	public String generateToken(String username, String password, String room) throws IllegalArgumentException;

	Message getMessage(Message message);

	public boolean validateToken(String username);

	public List<RegisteredUser> getChatHistory(String room);

}
