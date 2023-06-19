package com.bkdn.cntt.services;

import java.util.ArrayList;

import com.bkdn.cntt.models.Account;
import com.bkdn.cntt.models.create.CreateAccount;
import com.bkdn.cntt.models.general.ApiResponse;

public interface AccountService {

	ArrayList<ApiResponse> create(Account m);

	ArrayList<ApiResponse> create(CreateAccount m);

	Account sendVerifyEmail(String username, String email) throws Exception;

	Account updateEmail(String token, String email) throws Exception;

	Account sendResetPwEmail(String email) throws Exception;

	Account resetPassword(String token, String password) throws Exception;

	Account getUserById(Integer id) throws Exception;

	Account getUser(String username) throws Exception;

	Account update(Integer id, Account m) throws Exception;

	Boolean updatePassword(String username, String oldPass, String newPass) throws Exception;

	Boolean delete(Integer id, String password) throws Exception;

}
