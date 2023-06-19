package com.bkdn.cntt.models.general;

public class ServerErrorCode {

	public static final int PasswordIncorrect = 0;

	public static final int EmailNotExists = 1;
	public static final int UsernameNotExists = 2;
	public static final int Verified = 3;
	public static final int NotVerified = 4;
	public static final int AccountNotExists = 5;

	// Token
	public static final int Expired = 6;
	public static final int WrongToken = 7;

	public class Signup {
		public static final int EmailExists = 8;
		public static final int UsernameExists = 9;
	}
	
	public static final int LoginFail = 10;
	
	public static final int Unauthorized = 11;

}
