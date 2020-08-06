package com.example.WebSocket.shared;

import java.io.Serializable;

public class RegisteredUser implements Serializable {

	public RegisteredUser() {
	}

	public String username;
	public String message;
	public String date;
	public String room;

	public String getDate() {
		return date;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
