package com.ems.domain.model;

import java.util.Date;
import java.util.Map;

public class ErrorMessage {

	private Date timestamp;
	
	private Map<String,String> message;
	
	public ErrorMessage() {
		
	}
	
	public ErrorMessage(Date timeStamp, Map<String,String> message) {
		this.timestamp = timeStamp;
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Map<String,String> getMessage() {
		return message;
	}

	public void setMessage(Map<String,String> message) {
		this.message = message;
	}
}
