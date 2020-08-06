package com.example.WebSocket.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public class User  implements IsSerializable {  
	 private String username;  
	 private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	}  
