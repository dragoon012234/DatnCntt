package com.bkdn.cntt.models.general;

public class ErrorResponse extends Exception {

	private static final long serialVersionUID = 1L;

	public int code;
	public Object value;

	public ErrorResponse() {
		super();
	}

	public ErrorResponse(int code, String message) {
		super(message);
		this.code = code;
	}

	public ErrorResponse(int code, String message, Object value) {
		super(message);
		this.code = code;
		this.value = value;
	}

}
