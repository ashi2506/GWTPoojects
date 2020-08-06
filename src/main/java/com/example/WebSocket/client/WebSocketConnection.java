package com.example.WebSocket.client;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.example.WebSocket.shared.Message;
import com.gargoylesoftware.htmlunit.javascript.host.Console;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptException;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamFactory;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

public class WebSocketConnection {
	private String url;
	private JavaScriptObject ws;
	private ArrayList<WebSocketHandler> handlers = new ArrayList<WebSocketHandler>();
	private SerializationStreamFactory factory;
	private ChatWidget chatPresenter;

	static final Logger logger = Logger.getLogger(WebSocketConnection.class.getName());

	public WebSocketConnection(String url) {
		super();
		this.url = url;
		factory = (SerializationStreamFactory) GWT.create(DBConnection.class);
	}

	public void open() throws WebSocketNotSupportedException {
		try {
			ws = init();
		} catch (JavaScriptException e) {
			throw new WebSocketNotSupportedException();
		}
	}

	private native JavaScriptObject init() /*-{
		if (!$wnd.WebSocket) {
			throw "WebSocket not supported";
		}
		var websocket = new WebSocket(
				this.@com.example.WebSocket.client.WebSocketConnection::url);
		var wrapper = this;
		websocket.onopen = function(evt) {
			wrapper.@com.example.WebSocket.client.WebSocketConnection::onOpen()();
			console.log("on javascript object");
		};
		websocket.onclose = function(evt) {
			wrapper.@com.example.WebSocket.client.WebSocketConnection::onClose()();
		};
		websocket.onmessage = function(evt) {
			wrapper.@com.example.WebSocket.client.WebSocketConnection::onMessage(Ljava/lang/String;)(evt.data);
			console.log("on javascript object message")
		};
		websocket.onerror = function(evt) {
			wrapper.@com.example.WebSocket.client.WebSocketConnection::onError()();

		};
		return websocket;
	}-*/;

	private native void send(String message) /*-{
		this.@com.example.WebSocket.client.WebSocketConnection::ws
				.send(message);
	}-*/;

	private native boolean destroy() /*-{
		this.@com.example.WebSocket.client.WebSocketConnection::ws.close();
	}-*/;

	public void addWebSocketHandler(WebSocketHandler handler) {
		handlers.add(handler);
	}

	public void removeWebSocketHandler(WebSocketHandler handler) {
		handlers.add(handler);
	}

	public void sendMessae(Message message) {
		try {
			final SerializationStreamWriter writer = factory
					.createStreamWriter();
			writer.writeObject(message);
			final String data = writer.toString();
			send(data);
		} catch (final SerializationException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		if (ws != null) {
			destroy();
			ws = null;
		}
	}

	private void onClose() {
		for (WebSocketHandler handler : handlers) {
			handler.onClose();
		}
	}

	private void onError() {
		for (WebSocketHandler handler : handlers) {
			handler.onError("Websocket error");
		}
	}

	private void onMessage(String msg) {
		try {
			final SerializationStreamReader streamReader = factory
					.createStreamReader(msg);
			final Message message = (Message) streamReader.readObject();
			for (WebSocketHandler handler : handlers) {
				handler.onMessage(message);
			}
		} catch (SerializationException e) {
			for (WebSocketHandler handler : handlers) {
				handler.onError("Serialization error");
			}
		}
	}

	public void onOpen() {
		for (WebSocketHandler handler : handlers) {
			handler.onOpen();
		}
	}
}
