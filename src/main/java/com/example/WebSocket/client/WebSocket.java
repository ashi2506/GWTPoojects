package com.example.WebSocket.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class WebSocket implements EntryPoint {
	static final Logger logger = Logger.getLogger(WebSocket.class.getName());

	public void onModuleLoad() {
		LoginScreen screen = new LoginScreen();
		RootPanel.get("application").add(screen);
	}
}
