package com.bkdn.cntt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bkdn.cntt.configs.models.AccountModel;
import com.bkdn.cntt.entities.AccountEntity;
import com.bkdn.cntt.enums.Role;
import com.bkdn.cntt.models.Account;
import com.bkdn.cntt.models.create.CreateAccount;
import com.bkdn.cntt.models.general.ApiResponse;
import com.bkdn.cntt.models.general.ErrorResponse;
import com.bkdn.cntt.models.update.UpdatePassword;
import com.bkdn.cntt.services.AccountService;
import com.bkdn.cntt.services.PostService;

@RestController
@RequestMapping(path = "/api/account")
public class AccountController {

	private static final String errorBlank = "%1$s must not be blank!";
	private static final String errorRoleAdmin = "Can't create admin account!";

	@Autowired
	private AccountService accService;

	@Autowired
	private PostService postService;

	@PostMapping(path = "/create")
	@PreAuthorize(value = "hasAnyAuthority('ADMIN')")
	public ResponseEntity<ApiResponse> apiCreate(@RequestBody Account m) {
		return createAccount(m);
	}

	private ResponseEntity<ApiResponse> createAccount(Account m) {
		if (!StringUtils.hasText(m.email)) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, errorBlank.formatted("Email")));
		}
		if (!StringUtils.hasText(m.password)) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, errorBlank.formatted("Password")));
		}
		if (!StringUtils.hasText(m.username)) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, errorBlank.formatted("Username")));
		}
		if (m.role == Role.Admin) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, errorRoleAdmin));
		}
		try {
			return ResponseEntity.ok(new ApiResponse(true, accService.create(m)));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
		}
	}

	@PostMapping(path = "/create/multi")
	@PreAuthorize(value = "hasAnyAuthority('ADMIN')")
	public ResponseEntity<ApiResponse> apiCreateMulti(@RequestBody CreateAccount m) {
		return createAccounts(m);
	}

	private ResponseEntity<ApiResponse> createAccounts(CreateAccount m) {
		if (!StringUtils.hasText(m.email)) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, errorBlank.formatted("Email")));
		}
		if (!StringUtils.hasText(m.password)) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, errorBlank.formatted("Password")));
		}
		if (!StringUtils.hasText(m.username)) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, errorBlank.formatted("Username")));
		}
		if (m.role == Role.Admin) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, errorRoleAdmin));
		}
		try {
			var success = accService.create(m);
			return ResponseEntity.ok(new ApiResponse(true, success));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
		}
	}

	@PostMapping(path = "/update/email")
	public ResponseEntity<ApiResponse> apiUpdateEmail(@RequestBody Account m) {
		var acc = getAccount().getAccount();
		m.username = acc.username;
		return updateEmail(m);
	}

	private ResponseEntity<ApiResponse> updateEmail(Account m) {
		try {
			m = accService.sendVerifyEmail(m.username, m.email);
			return ResponseEntity.ok(new ApiResponse(true, m));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
		}
	}

	@PostMapping(path = "/update/email/verify")
	public ResponseEntity<ApiResponse> apiVerifyUpdateEmail(@RequestBody Account m) {
		return verifyUpdateEmail(m);
	}

	private ResponseEntity<ApiResponse> verifyUpdateEmail(Account m) {
		try {
			m = accService.updateEmail(m.token, m.email);
			return ResponseEntity.ok(new ApiResponse(true, m));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
		}
	}

	@GetMapping(path = "/profile")
	public ResponseEntity<ApiResponse> apiProfile(@RequestParam String username) {
		return getUser(username);
	}

	@PostMapping(path = "/profile")
	public ResponseEntity<ApiResponse> apiProfile(@RequestParam @Nullable String username,
			@RequestBody @Nullable Account account) {
		if (account == null) {
			return getUser(username);
		}
		return getUser(account.username);
	}

	private ResponseEntity<ApiResponse> getUser(String username) {
		try {
			var m = accService.getUser(username);
			return ResponseEntity.ok(new ApiResponse(true, m));
		} catch (ErrorResponse e) {
			return ResponseEntity.badRequest().body(new ApiResponse(e));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
		}
	}

	@GetMapping(path = "/profile/id")
	public ResponseEntity<ApiResponse> apiProfileById(@RequestParam Integer userId) {
		return getUserById(userId);
	}

//	@PostMapping(path = "/profile/id")
//	public ResponseEntity<ApiResponse> apiProfileById(@RequestParam @Nullable Integer userId,
//			@RequestBody @Nullable Account account) {
//		if (account == null) {
//			return getUserById(userId);
//		}
//		return getUserById(account.username);
//	}

	private ResponseEntity<ApiResponse> getUserById(Integer userId) {
		try {
			var m = accService.getUserById(userId);
			return ResponseEntity.ok(new ApiResponse(true, m));
		} catch (ErrorResponse e) {
			return ResponseEntity.badRequest().body(new ApiResponse(e));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
		}
	}

	@PostMapping(path = "/profile/update")
	@PreAuthorize(value = "hasAnyAuthority('ADMIN', 'LECTURER', 'STUDENT')")
	public ResponseEntity<ApiResponse> apiUpdateProfile(@RequestBody Account user) {
		return updateUser(user);
	}

	private ResponseEntity<ApiResponse> updateUser(Account user) {
		try {
			AccountEntity accountEntity = getAccount().getAccount();
			user = accService.update(accountEntity.id, user);
			return ResponseEntity.ok(new ApiResponse(true, user));
		} catch (ErrorResponse e) {
			return ResponseEntity.badRequest().body(new ApiResponse(e));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
		}
	}

	@PostMapping(path = "/profile/update/password")
	@PreAuthorize(value = "hasAnyAuthority('ADMIN', 'LECTURER', 'STUDENT')")
	public ResponseEntity<ApiResponse> apiUpdatePassword(@RequestBody UpdatePassword pass) {
		AccountModel accountModel = getAccount();
		return updatePassword(accountModel.getUsername(), pass.oldPassword, pass.newPassword);
	}

	private ResponseEntity<ApiResponse> updatePassword(String username, String oldPass, String newPass) {
		if (!StringUtils.hasText(newPass)) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, errorBlank.formatted("Password")));
		}
		try {
			accService.updatePassword(username, oldPass, newPass);
			return ResponseEntity.ok(new ApiResponse(true));
		} catch (ErrorResponse e) {
			return ResponseEntity.badRequest().body(new ApiResponse(e));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
		}
	}

	@PostMapping(path = "/profile/delete")
	@PreAuthorize(value = "hasAnyAuthority('ADMIN', 'LECTURER', 'STUDENT')")
	public ResponseEntity<ApiResponse> apiDelete(@RequestBody Account user) {
		AccountModel accountModel = getAccount();
		return delete(accountModel.getAccount().id, user.password);
	}

	private ResponseEntity<ApiResponse> delete(Integer id, String pass) {
		try {
			if (accService.delete(id, pass)) {
				postService.deleteUser(id);
			}
			return ResponseEntity.ok(new ApiResponse(true));
		} catch (ErrorResponse e) {
			return ResponseEntity.badRequest().body(new ApiResponse(e));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
		}
	}

	@GetMapping(path = "/profile/myid")
	public ResponseEntity<ApiResponse> getId() {
		try {
			AccountEntity accountEntity = getAccount().getAccount();
			return ResponseEntity.ok(new ApiResponse(true, accountEntity));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponse(false, e.getMessage()));
		}
	}

	private AccountModel getAccount() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		try {
			return (AccountModel) authentication.getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

}
