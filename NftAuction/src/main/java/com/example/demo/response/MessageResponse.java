package com.example.demo.response;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class MessageResponse {

	private String status;
	private Object obj;
	private HttpStatus httpStatus;

	public MessageResponse(String status, Object obj, HttpStatus httpStatus) {
		this.status = status;
		this.obj = obj;
		this.httpStatus = httpStatus;
	}

}
