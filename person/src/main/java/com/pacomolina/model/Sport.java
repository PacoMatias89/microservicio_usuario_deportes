package com.pacomolina.model;

public class Sport {

	private String sport;

	private String type;
	
	private int userId;

	public Sport() {
	}

	
	public Sport(String sport,
			String type, 
			int userId) {
		super();
		this.sport = sport;
		this.type = type;
		this.userId = userId;
	}


	public String getSport() {
		return sport;
	}

	public void setSport(String sport) {
		this.sport = sport;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "Sport [sport=" + sport + ", type=" + type + ", userId=" + userId + "]";
	}
	
	

	
}
