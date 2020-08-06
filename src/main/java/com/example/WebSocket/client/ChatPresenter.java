package com.example.WebSocket.client;

import java.util.List;
import java.util.logging.Logger;

import com.example.WebSocket.shared.Message;
import com.example.WebSocket.shared.RegisteredUser;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ChatPresenter implements ChatView.Presenter, WebSocketHandler {

	private ChatView view;
	private String room;
	private WebSocketConnection webSocket;
	static final Logger logger = Logger.getLogger(ChatPresenter.class.getName());
	private final DBConnectionAsync dbService = GWT.create(DBConnection.class);

	public ChatPresenter(ChatView view, WebSocketConnection webSocket, String room) {
		super();
		this.room = room;
		this.view = view;
		this.webSocket = webSocket;
		view.setPresenter(this);
		webSocket.addWebSocketHandler(this);
	}

	@Override
	public void sendMessage(Message message) {
		logger.fine("on send message  " + message.getUsername());
		webSocket.sendMessae(message);
	}

	@Override
	public void closeConnection() {
		webSocket.close();
	}

	@Override
	public void onClose() {
		view.setInfoMessage("Log out", true);
	}

	@Override
	public void onError(String error) {
		view.setInfoMessage(error, true);

	}

	@Override
	public void onMessage(Message msg) {
		view.addMessage(msg);

	}

	@Override
	public void onOpen() {
		getChatHistory(room);
		logger.fine("on open ::" + view);
		view.setInfoMessage("Web Socket Open", true);
	}

	private void getChatHistory(String chatRoom) {
		dbService.getChatHistory(chatRoom,  new AsyncCallback<List<RegisteredUser>>() {
			@Override
			public void onFailure(Throwable caught) {
				logger.fine("getChatHistory faliure");
			}

			@Override
			public void onSuccess(List<RegisteredUser> result) {
				for (RegisteredUser registeredUser : result) {
					Message registeredUserMessage = new Message();
					registeredUserMessage.setUsername(registeredUser.getUsername());
					registeredUserMessage.setData(registeredUser.getMessage());
					registeredUserMessage.setTime(registeredUser.getDate());
					view.addMessage(registeredUserMessage);
				}
			}
		});
	}
}