package com.example.WebSocket.client;

import com.example.WebSocket.shared.Message;

public interface ChatView {

	interface Presenter {
		void sendMessage(Message message);

		void closeConnection();

	}
	
	void addMessage(Message message);
	
	void setPresenter(Presenter presenter);
	
	void setInfoMessage(String message, boolean error);

}
