package com.example.WebSocket.client;

import java.util.Date;
import java.util.logging.Logger;

import com.example.WebSocket.shared.Message;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

public class ChatWidget extends Composite implements ChatView {

	interface ChatWidgetUiBinder extends UiBinder<HTMLPanel, ChatWidget> {
	}

	private final DBConnectionAsync dbService = GWT.create(DBConnection.class);
	private static ChatWidgetUiBinder uiBinder = GWT
			.create(ChatWidgetUiBinder.class);

	static final Logger logger = Logger.getLogger(ChatWidget.class.getName());

	public String username;

	String password;

	@UiField TextBox inputText;

	@UiField Button sendButton, logout;

	@UiField FlexTable table;

	@UiField Label infolabel;

	private Presenter presenter;

	public boolean validToken;

	public String user;
	
	public String room;

	public ChatWidget(String user, String room) {
		this.room = room;
		this.user = user;
		ChatResource.CHATRESOURCE.style().ensureInjected();
		initWidget(uiBinder.createAndBindUi(this));
	}

	public String getUser() {
		return user;
	}

	@Override
	public void addMessage(Message msg) {
		table.insertRow(0);
		Label info = new Label(msg.getTime() + "   "
				+ msg.getUsername() + "   ");
		info.setStyleName("info");
		Label data = new Label(msg.getData());
		data.setStyleName("data");
		table.setWidget(0, 0, info);
		table.setWidget(0, 1, data);
	}

	@Override
	public void setInfoMessage(String message, boolean error) {
		if (error) {
			infolabel.setStyleName("error");
		} else {
			infolabel.setStyleName("info");
		}
		infolabel.setText(message);
		infolabel.setVisible(true);
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@UiHandler("sendButton")
	void handleClick(ClickEvent e) {
		validateToken();
	}

	private void validateToken() {
		dbService.validateToken(user, new AsyncCallback<Boolean>() {
			@Override
			public void onFailure(Throwable caught) {
				setInfoMessage("Token Expired", true);
			}

			@Override
			public void onSuccess(Boolean result) {
				logger.fine("on success");
				sendMessageToThePresenter();
			}
		});
	}

	@UiHandler("logout")
	void handleLogoutClick(ClickEvent e) {
		RootPanel.get("application").clear();
		presenter.closeConnection();
	}

	@UiHandler("inputText")
	void onPasswordTextBoxKeyPress(KeyPressEvent event) {
		if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
			sendMessageToThePresenter();
		}
	}

	private void sendMessageToThePresenter() {
		Message message = new Message();
		logger.fine("on send to presenetr ::: " + username);
		message.setData(inputText.getText());
		Date date = new Date();
		message.setTime(date.toString());
		logger.fine("date is ::: " +date.toString());
		message.setUsername(user);
		presenter.sendMessage(message);
		inputText.setText("");
	}
}
