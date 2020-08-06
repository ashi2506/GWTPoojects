package com.example.WebSocket.server;

import java.security.Key;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.spec.SecretKeySpec;

import com.example.WebSocket.client.DBConnection;
import com.example.WebSocket.client.User;
import com.example.WebSocket.shared.Message;
import com.example.WebSocket.shared.RegisteredUser;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class MySqlConnection extends RemoteServiceServlet implements DBConnection {
	private static Connection conn = null;
	private String url = "jdbc:mysql://localhost:3306/test";
	private String user = "root";
	private String pass = "root";
	String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";
	Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
			SignatureAlgorithm.HS256.getJcaName());

	public MySqlConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
		}
	}

	public boolean authenticateUser(String username, String pasword) {
		boolean flag = true;
		try {
			PreparedStatement ps = conn.prepareStatement("Select * from user where Username=? AND password = ?");
			ps.setString(1, username);
			ps.setString(2, pasword);
			ResultSet result = ps.executeQuery();
			System.out.println("on aunthenticate user");
			flag = result.next();
			result.close();
			ps.close();
		} catch (SQLException sqle) {
			System.out.println("Exception: " + sqle.getMessage());
		}
		return flag;
	}

	public User register(String username, String password) {
		System.out.println("on register");
		PreparedStatement ps = null;
		ResultSet rs = null;
		User userRegistered = new User();
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			conn = DriverManager.getConnection(url, user, pass);
			ps = conn.prepareStatement("Insert into user values(?,?)");
			ps.setString(1, username);
			ps.setString(2, password);
			ps.executeUpdate();
			userRegistered.setUsername(username);
			userRegistered.setPassword(password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userRegistered;
	}

	@Override
	public String generateToken(String username, String password , String room) throws IllegalArgumentException {
		String jws = null;
		boolean validUser = authenticateUser(username, password);
		if (validUser) {
			Map<String, Object> claims = new HashMap<>();
			jws = doGenerateToken(claims, username);
			SaveVerficationToken(username, password, jws, room);
		} else {
			System.out.println("Please enter correct user name and password");
		}
		return jws;
	}

	private void SaveVerficationToken(String username, String password, String jws, String room) {
		System.out.println("on register");
		PreparedStatement ps = null;
		ResultSet rs = null;
		User userRegistered = new User();
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			conn = DriverManager.getConnection(url, user, pass);
			ps = conn.prepareStatement("Insert into verificationtoken values(?,?,?,?)");
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, jws);
			ps.setString(4, room);
			ps.executeUpdate();
			userRegistered.setUsername(username);
			userRegistered.setPassword(password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private String doGenerateToken(Map<String, Object> claims, String username) {
		long endTime = System.currentTimeMillis() + 1000 * 60;
		System.out.println("on doGenerateToken");
		String jws = Jwts.builder()
				.setId(username).setExpiration(new Date(endTime))
				.signWith(hmacKey).compact();
		return jws;
	}

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(hmacKey).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	@Override
	public Message getMessage(Message message) {
		return message;
	}

	public void sendMessageHistory(String username, String data, String date, String room) {
		PreparedStatement ps = null;
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			conn = DriverManager.getConnection(url, user, pass);
			ps = conn.prepareStatement("Insert into userstatus values(?,?,?,?)");
			ps.setString(1, username);
			ps.setString(2, data);
			ps.setString(3, date);
			ps.setString(4, room);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<RegisteredUser> getMessageHistory() {
		List<RegisteredUser> users = new ArrayList<>();

		try {
			PreparedStatement ps = conn.prepareStatement("Select * from userstatus");
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				RegisteredUser user = new RegisteredUser();
				user.setUsername(result.getString(1));
				user.setMessage(result.getString(2));
				users.add(user);
			}
			result.close();
			ps.close();
		} catch (SQLException sqle) {
			System.out.println("Exception: " + sqle.getMessage());
		}
		return users;
	}

	@Override
	public boolean validateToken(String username) {
		String token = getToken(username);
		return (username.equals(username) && !isTokenExpired(token));
	}

	private String getToken(String username) {
		String token = null;
		try {
			PreparedStatement ps = conn.prepareStatement("Select jws from verificationtoken");
			ResultSet result = ps.executeQuery();
			System.out.println("on aunthenticate user");
			while (result.next()) {
				token = result.getString(1);
			}
			result.close();
			ps.close();
		} catch (SQLException sqle) {
			System.out.println("Exception: " + sqle.getMessage());
		}
		return token;
	}

	@Override
	public List<RegisteredUser> getChatHistory(String room) {
		List<RegisteredUser> users = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement("Select * from userstatus where room = ?" );
			ps.setString(1, room);
			ResultSet result = ps.executeQuery();
			System.out.println("on aunthenticate user");
			while (result.next()) {
				RegisteredUser user = new RegisteredUser();
				user.setUsername(result.getString(1));
				user.setMessage(result.getString(2));
				user.setDate(result.getString(3));
				user.setRoom(result.getString(4));
				users.add(user);
			}
			result.close();
			ps.close();
		} catch (SQLException sqle) {
			System.out.println("Exception: " + sqle.getMessage());
		}
		return users;
	}
}
