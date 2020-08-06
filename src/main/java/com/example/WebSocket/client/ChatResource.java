package com.example.WebSocket.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ClientBundle.Source;

public interface ChatResource extends ClientBundle {
	public static final ChatResource CHATRESOURCE = GWT.create(ChatResource.class);
	
	@Source("com/example/WebSocket/client/resources/css/chatApplication.css")
	@CssResource.NotStrict
	public CssResource style();
}