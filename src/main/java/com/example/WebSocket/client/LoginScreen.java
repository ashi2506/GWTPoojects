package com.example.WebSocket.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginScreen extends Composite {

	private static LoginScreenUiBinder uiBinder = GWT.create(LoginScreenUiBinder.class);
	private final DBConnectionAsync dbService = GWT.create(DBConnection.class);
	static final Logger logger = Logger.getLogger(LoginScreen.class.getName());

	@UiField TextBox passwordTextBox, userNameTextBox;
	@UiField Label username, password, Error;
	@UiField Button ok, register;
	@UiField ListBox chatroom;
	public String user;
	public String room;

	interface LoginScreenUiBinder extends UiBinder<Widget, LoginScreen> {
	}

	public LoginScreen() {
		LoginResource.LOGINRESOURCE.style().ensureInjected();
		initWidget(uiBinder.createAndBindUi(this));
		room = "aurdino";
		registerEvents();
	}
	
	private void registerEvents() {
		chatroom.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				room = chatroom.getSelectedValue();
				logger.fine("on chat room change " + room);
			}
		});
	}

	@UiHandler("userNameTextBox")
	void onKeyUp1(KeyUpEvent event) {
		if ("".equals(userNameTextBox.getValue())) {
			ok.setEnabled(false);
		} else {
			ok.setEnabled(true);
		}
	}

	@UiHandler("userNameTextBox")
	public void onKeyPress(KeyPressEvent event) {
		if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER && !"".equals(userNameTextBox.getValue())) {
			userNameTextBox.setFocus(true);
		}
	}

	@UiHandler("passwordTextBox")
	public void onKeyUp(KeyUpEvent event) {
		if ("".equals(passwordTextBox.getValue())) {
			ok.setEnabled(false);
		} else {
			ok.setEnabled(true);
		}
	}
	
	@UiHandler("passwordTextBox")
	void onKeyPress1(KeyPressEvent event) {
		if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER && !"".equals(passwordTextBox.getValue())) {
			passwordTextBox.setFocus(true);
		}
	}
	

	@UiHandler("register")
	public void onRegisterClick(ClickEvent event) {
		dbService.register(userNameTextBox.getValue(), passwordTextBox.getValue(), new AsyncCallback<User>() {
			@Override
			public void onFailure(Throwable caught) {
				Error.setText("Username already exist");
			}

			@Override
			public void onSuccess(User result) {
				if (result.getUsername() != null) {
					Error.setText(userNameTextBox.getValue() + " You are registered. Click on Ok to proceed");
				} else {
					Error.setText("Username already exist");
				}
			}
		});
	}

	@UiHandler("ok")
	public void onOkClick(ClickEvent event) {
		dbService.generateToken(userNameTextBox.getValue(), passwordTextBox.getValue(), room, new AsyncCallback<String>() {
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("on faliure");
			}

			@Override
			public void onSuccess(String token) {
				if (token != null) {
					user = userNameTextBox.getValue();
					logger.fine("user name on login screen is ::: " + user);
					String url = "ws://" + Window.Location.getHostName() + ":8885/chat/" + room;
					logger.fine("url is  ::: " + url);
					GWT.log("on module load");
					WebSocketConnection webSocket = new WebSocketConnection(url);
					ChatWidget view = new ChatWidget(user, room);
					ChatPresenter presenter = new ChatPresenter(view, webSocket, room);
					try {
						webSocket.open();
						

					} catch (WebSocketNotSupportedException e) {
						Window.alert("Sorry your browser dosen't support Web Socket");
					}
					RootPanel.get("application").clear();
					RootPanel.get("application").add(view);
				} else {
					Error.setText("Incorrect username Password");
				}
			}
		});
	}
}
