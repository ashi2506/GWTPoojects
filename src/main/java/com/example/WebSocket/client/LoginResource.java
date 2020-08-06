package com.example.WebSocket.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface LoginResource extends ClientBundle {
	public static final LoginResource LOGINRESOURCE = GWT.create(LoginResource.class);

	@Source("com/example/WebSocket/client/resources/css/login.css")
	@CssResource.NotStrict
	public CssResource style();
}