package com.bkdn.cntt.models.general;

public class ApiResponse {

	public Boolean success;
	public Object value;
	public Integer code;
	public String message;

	public ApiResponse(Boolean succes) {
		this(succes, null);
	}

	public ApiResponse(Boolean success, Object value) {
		this.success = success;
		this.value = value;
	}

	public ApiResponse(ErrorResponse error) {
		this.success = false;
		this.value = error.value;
		this.code = error.code;
		this.message = error.getMessage();
	}

}
