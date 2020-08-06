package com.example.WebSocket.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Message implements Serializable {

	private static final long serialVersionUID = -2974784212373773166L;
	private String data;
	private String username;
	private String time;
	Map<String, String> sessionUserMap = new HashMap<String, String>();
	List<Map<String, String>> sessionUserMapList = new ArrayList<>();
	
	public List<Map<String, String>> getSessionUserMapList() {
		return sessionUserMapList;
	}

	public void setSessionUserMapList(List<Map<String, String>> sessionUserMapList) {
		this.sessionUserMapList = sessionUserMapList;
	}

	public Map<String, String> getSessionUserMap() {
		return sessionUserMap;
	}

	public void setSessionUserMap(String session , String user) {
		sessionUserMap.put(session,user);
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	private List<String> oldMessages = new ArrayList<>();

	public String getData() {
		return data;
	}

	public List<String> getOldMessages() {
		return oldMessages;
	}

	public void setOldMessages(List<String> oldMessages) {
		this.oldMessages = oldMessages;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

}