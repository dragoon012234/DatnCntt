package com.bkdn.cntt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bkdn.cntt.configs.models.AccountModel;
import com.bkdn.cntt.jwt.JwtTokenProvider;
import com.bkdn.cntt.models.Account;
//import com.bkdn.cntt.service.UserService;
import com.bkdn.cntt.models.general.ApiResponse;
import com.bkdn.cntt.models.general.ErrorResponse;
import com.bkdn.cntt.models.general.ServerErrorCode;
import com.bkdn.cntt.services.AccountService;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthenticationController {

	private static final String errorBlank = "%1$s must not be blank!";

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private AccountService accService;
	@Autowired
	private JwtTokenProvider tokenProvider;

//	@Autowired
//	private UserService userService;

	@GetMapping(path = { "/login", "/signin" })
	public ResponseEntity<ApiResponse> apiSignin(@RequestParam String username, @RequestParam String password) {
		return signin(username, password);
	}

	@PostMapping(path = { "/login", "/signin" })
	public ResponseEntity<ApiResponse> apiSignin(@RequestParam @Nullable String username,
			@RequestParam @Nullable String password, @RequestBody @Nullable Account account) {
		if (account == null) {
			return apiSignin(username, password);
		}
		return apiSignin(account.username, account.password);
	}

	private ResponseEntity<ApiResponse> signin(String username, String password) {
		if (!StringUtils.hasText(username)) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, errorBlank.formatted("Username")));
		}
		if (!StringUtils.hasText(password)) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, errorBlank.formatted("Password")));
		}
		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			AccountModel principal = (AccountModel) authentication.getPrincipal();
			if (!principal.getAccount().verify) {
				return ResponseEntity.badRequest().body(new ApiResponse(
						new ErrorResponse(ServerErrorCode.NotVerified, "Not Verified", new Account(principal))));
			}
			String token = tokenProvider.generateToken(principal);
			Account account = new Account(principal);
			account.token = token;
			return ResponseEntity.ok(new ApiResponse(true, account));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().body(new ApiResponse(new ErrorResponse(ServerErrorCode.LoginFail, e.getMessage())));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(new ApiResponse(new ErrorResponse(ServerErrorCode.LoginFail, e.getMessage())));
		}
	}

//	@GetMapping(path = { "/logup", "/signup" })
//	public ResponseEntity<ApiResponse> apiSignup(@RequestParam @Nullable String username, @RequestParam String email,
//			@RequestParam String password, @RequestParam String name, @RequestParam @Nullable Role role) {
//		if (role == null) {
//			role = Role.SingleUser;
//		}
//		if (!StringUtils.hasText(username)) {
//			username = email.substring(0, email.indexOf('@'));
//		}
//		return signup(username, email, password, name, role);
//	}
//
//	@PostMapping(path = { "/logup", "/signup" })
//	public ResponseEntity<ApiResponse> apiSignup(@RequestParam @Nullable String username,
//			@RequestParam @Nullable String email, @RequestParam @Nullable String password,
//			@RequestParam @Nullable Role role, @RequestParam @Nullable String name,
//			@RequestBody @Nullable Account account) {
//		if (account == null) {
//			return apiSignup(username, email, password, name, role);
//		}
//		return apiSignup(account.getUsername(), account.getEmail(), account.getPassword(), account.getName(),
//				account.getRole());
//	}
//
//	private ResponseEntity<ApiResponse> signup(String username, String email, String password, String name, Role role) {
//		if (!StringUtils.hasText(email)) {
//			return ResponseEntity.badRequest().body(new ApiResponse(false, errorBlank.formatted("Email")));
//		}
//		if (!StringUtils.hasText(password)) {
//			return ResponseEntity.badRequest().body(new ApiResponse(false, errorBlank.formatted("Password")));
//		}
////		if (!StringUtils.hasText(name)) {
////			return ResponseEntity.badRequest().body(new ApiResponse(false, errorBlank.formatted("Name")));
////		}
//		if (role == Role.Admin) {
//			return ResponseEntity.badRequest().body(new ApiResponse(false, errorBlank.formatted("Role")));
//		}
//		try {
//			Account account = new Account(username, email, password, name, role, null);
//			account = userService.signup(account);
//			return ResponseEntity.ok(new ApiResponse(true, account));
//		} catch (ErrorResponse e) {
//			return ResponseEntity.badRequest().body(new ApiResponse(e));
//		} catch (Exception e) {
//			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
//		}
//	}

//	@GetMapping(path = { "/signup/verify", "/logup/verify" })
//	public ResponseEntity<ApiResponse> apiSignupFinnish(@RequestParam String token) {
//		return signupFinish(token);
//	}
//
//	@PostMapping(path = { "/signup/verify", "/logup/verify" })
//	public ResponseEntity<ApiResponse> apiSignupFinnish(@RequestParam @Nullable String token,
//			@RequestBody @Nullable Account account) {
//		if (account == null) {
//			return apiSignupFinnish(token);
//		}
//		return apiSignupFinnish(account.token);
//	}
//
//	private ResponseEntity<ApiResponse> signupFinish(String token) {
//		if (!StringUtils.hasText(token)) {
//			return ResponseEntity.badRequest().body(new ApiResponse(false, errorBlank.formatted("Token")));
//		}
//		try {
//			Account account = userService.signupVerify(token);
//			return ResponseEntity.ok(new ApiResponse(true, account));
//		} catch (ErrorResponse e) {
//			return ResponseEntity.badRequest().body(new ApiResponse(e));
//		} catch (Exception e) {
//			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
//		}
//	}

//	@GetMapping(path = { "/signup/sendverify", "/logup/sendverify" })
//	public ResponseEntity<ApiResponse> apiSignupRe(@RequestParam String username) {
//		return signupRe(username);
//	}
//
//	@PostMapping(path = { "signup/sendverify", "/logup/sendverify" })
//	public ResponseEntity<ApiResponse> apiSignupRe(@RequestParam @Nullable String username,
//			@RequestBody @Nullable Account account) {
//		if (account == null) {
//			return apiSignupRe(username);
//		}
//		return apiSignupRe(account.getUsername());
//	}
//
//	private ResponseEntity<ApiResponse> signupRe(String username) {
//		if (!StringUtils.hasText(username)) {
//			return ResponseEntity.badRequest().body(new ApiResponse(false, errorBlank.formatted("Username")));
//		}
//		try {
//			Account account = userService.sendVerifyEmail(username);
//			return ResponseEntity.ok(new ApiResponse(true, account));
//		} catch (ErrorResponse e) {
//			return ResponseEntity.badRequest().body(new ApiResponse(e));
//		} catch (Exception e) {
//			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
//		}
//	}

	@GetMapping(path = "/forgotpassword")
	public ResponseEntity<ApiResponse> apiForgotpassword(@RequestParam String email) {
		return forgotpassword(email);
	}

	@PostMapping(path = "/forgotpassword")
	public ResponseEntity<ApiResponse> apiForgotpassword(@RequestParam @Nullable String email,
			@RequestBody @Nullable Account account) {
		if (account == null) {
			return apiForgotpassword(email);
		}
		return apiForgotpassword(account.email);
	}

	private ResponseEntity<ApiResponse> forgotpassword(String email) {
		if (!StringUtils.hasText(email)) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, errorBlank.formatted("Email")));
		}
		try {
			var m = accService.sendResetPwEmail(email);
			return ResponseEntity.ok(new ApiResponse(true, m));
		} catch (ErrorResponse e) {
			return ResponseEntity.badRequest().body(new ApiResponse(e));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
		}
	}

	@GetMapping(path = "/forgotpassword/reset")
	public ResponseEntity<ApiResponse> apiForgotpasswordReset(@RequestParam String token,
			@RequestParam String password) {
		return forgotpasswordReset(token, password);
	}

	@PostMapping(path = "/forgotpassword/reset")
	public ResponseEntity<ApiResponse> apiForgotpasswordReset(@RequestParam @Nullable String token,
			@RequestParam @Nullable String password, @RequestBody @Nullable Account account) {
		if (account == null) {
			return apiForgotpasswordReset(token, password);
		}
		return apiForgotpasswordReset(account.token, account.password);
	}

	private ResponseEntity<ApiResponse> forgotpasswordReset(String token, String password) {
		if (!StringUtils.hasText(token)) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, errorBlank.formatted("Token")));
		}
		if (!StringUtils.hasText(password)) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, errorBlank.formatted("Email")));
		}
		try {
			var m = accService.resetPassword(token, password);
			return ResponseEntity.ok(new ApiResponse(true, m));
		} catch (ErrorResponse e) {
			return ResponseEntity.badRequest().body(new ApiResponse(e));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
		}
	}

}
