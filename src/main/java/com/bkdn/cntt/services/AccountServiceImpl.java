package com.bkdn.cntt.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkdn.cntt.entities.AccountEntity;
import com.bkdn.cntt.models.Account;
import com.bkdn.cntt.models.create.CreateAccount;
import com.bkdn.cntt.models.general.ApiResponse;
import com.bkdn.cntt.models.general.ErrorResponse;
import com.bkdn.cntt.models.general.ServerErrorCode;
import com.bkdn.cntt.repositories.AccountRepository;
import com.bkdn.cntt.utils.EncryptedPasswordUtils;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accRepo;

	@Override
	public ArrayList<ApiResponse> create(Account m) {
		var rs = new ArrayList<ApiResponse>();

		if (accRepo.findByUsername(m.username) != null) {
			rs.add(new ApiResponse(
					new ErrorResponse(ServerErrorCode.Signup.UsernameExists, "Username already exists!")));
		} else {
			AccountEntity e = new AccountEntity(m);
			e.password = EncryptedPasswordUtils.encode(e.password);
			e = accRepo.save(e);
			m.configure(e);
			rs.add(new ApiResponse(true, e));
		}

		return rs;
	}

	@Override
	public ArrayList<ApiResponse> create(CreateAccount m) {
		var rs = new ArrayList<ApiResponse>();

		ArrayList<Integer> watched = new ArrayList<Integer>();
		watched.add(0);
		for (int i = 0, j = m.start; i < m.count; i++, j += m.step) {
			try {
				var username = String.format(m.username, j);
				if (accRepo.findByUsername(username) != null) {
					rs.add(new ApiResponse(
							new ErrorResponse(ServerErrorCode.Signup.UsernameExists, "Username already exists!")));
				} else {
					AccountEntity e = new AccountEntity();
					e.username = username;
					e.password = EncryptedPasswordUtils.encode(String.format(m.password, j));
					e.email = String.format(m.email, j);
					e.role = m.role;
					e.blocked = false;
					e.name = e.username;
					e.verify = true;
					e = accRepo.save(e);

					rs.add(new ApiResponse(true, e));
				}
			} catch (Exception e) {
				rs.add(new ApiResponse(new ErrorResponse(-1, e.getMessage())));
			}
		}

		return rs;
	}

	@Override
	public Account sendVerifyEmail(String username, String email) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account updateEmail(String token, String email) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account sendResetPwEmail(String email) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account resetPassword(String token, String password) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account getUserById(Integer id) throws Exception {
		var op = accRepo.findById(id);
		if (op.isEmpty()) {
			throw new ErrorResponse(ServerErrorCode.AccountNotExists, "Account does not exits!");
		}
		AccountEntity e = op.get();
		Account m = new Account(e);
		return m;
	}

	@Override
	public Account getUser(String username) throws Exception {
		AccountEntity e = accRepo.findByUsername(username);
		if (e == null) {
			throw new ErrorResponse(ServerErrorCode.AccountNotExists, "Account does not exits!");
		}
		Account m = new Account(e);
		return m;
	}

	@Override
	public Account update(Integer id, Account m) throws Exception {
		var op = accRepo.findById(id);
		if (op.isEmpty()) {
			throw new ErrorResponse(ServerErrorCode.AccountNotExists, "Account does not exits!");
		}
		var e = op.get();
		e.update(m);
		e = accRepo.save(e);
		return m;
	}

	@Override
	public Boolean updatePassword(String username, String oldPass, String newPass) throws Exception {
		AccountEntity e = accRepo.findByUsername(username);
		if (e == null) {
			throw new ErrorResponse(ServerErrorCode.UsernameNotExists, "Username does not exist!");
		}
		if (!EncryptedPasswordUtils.matches(oldPass, e.password)) {
			throw new ErrorResponse(ServerErrorCode.PasswordIncorrect, "Password incorrect!");
		}

		e.password = EncryptedPasswordUtils.encode(newPass);
		accRepo.save(e);

		return true;
	}

	@Override
	public Boolean delete(Integer id, String password) throws Exception {
		var op = accRepo.findById(id);
		if (op.isEmpty()) {
			throw new ErrorResponse(ServerErrorCode.AccountNotExists, "Account does not exits!");
		}
		var e = op.get();
		if (!EncryptedPasswordUtils.matches(password, e.password)) {
			throw new ErrorResponse(ServerErrorCode.PasswordIncorrect, "Password incorrect!");
		}
		accRepo.deleteById(id);
		return true;
	}

}
