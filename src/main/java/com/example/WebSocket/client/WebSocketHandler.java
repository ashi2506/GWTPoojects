package com.example.WebSocket.client;

import com.example.WebSocket.shared.Message;

public interface WebSocketHandler {

	void onClose();

	void onError(String error);

	void onMessage(Message msg);

	void onOpen();

	void sendMessage(Message message);

}
