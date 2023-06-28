package com.bkdn.cntt.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bkdn.cntt.configs.models.AccountModel;
import com.bkdn.cntt.enums.NotificationType;
import com.bkdn.cntt.enums.PostType;
import com.bkdn.cntt.enums.Role;
import com.bkdn.cntt.models.general.ApiResponse;

@RestController
@RequestMapping(path = "/api")
public class HomeController {

//	@Autowired
//	private MailService mailService;
//
//	@GetMapping(path = "/api/test/mail/hello")
//	public ResponseEntity<ApiResponse> mailHello(@RequestParam String email) {
//		mailService.sendHello(email);
//		return ResponseEntity.ok(new ApiResponse(true, "Send hello email to " + email));
//	}

	@GetMapping(path = "/api/test/auth")
	@PreAuthorize(value = "hasAnyAuthority('ADMIN', 'LECTURER', 'STUDENT')")
	public ResponseEntity<ApiResponse> testAuth() {
		AccountModel accountModel = getAccount();
		return ResponseEntity
				.ok(new ApiResponse(true, "Phai sign in thi moi thay! Role: " + accountModel.getAccount().role));
	}

//	@GetMapping(path = "/test/mail/signup")
//	public String testSignup(ModelMap map) {
//		map.addAttribute("user", new Account(null, null, null, null, null, "token"));
//		return "mail/signup";
//	}

	@GetMapping(path = "/allroles")
	public ResponseEntity<ApiResponse> getAllRoles() {
		try {
			Role[] roles = Role.class.getEnumConstants();
			return ResponseEntity.ok(new ApiResponse(true, roles));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponse(false, e.getMessage()));
		}
	}

	@GetMapping(path = "/allposttypes")
	public ResponseEntity<ApiResponse> getAllPostTypes() {
		try {
			PostType[] posttypes = PostType.class.getEnumConstants();
			return ResponseEntity.ok(new ApiResponse(true, posttypes));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponse(false, e.getMessage()));
		}
	}

	@GetMapping(path = "/allnotitypes")
	public ResponseEntity<ApiResponse> getAllNotiTypes() {
		try {
			NotificationType[] posttypes = NotificationType.class.getEnumConstants();
			return ResponseEntity.ok(new ApiResponse(true, posttypes));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(new ApiResponse(false, e.getMessage()));
		}
	}

	@GetMapping(path = "/hello")
	public ResponseEntity<ApiResponse> getHello() {
		return ResponseEntity.ok(new ApiResponse(true, "Hello Client!"));
	}

	@PostMapping(path = "/hello")
	public ResponseEntity<ApiResponse> postHello(@RequestBody String body) {
		return ResponseEntity.ok(new ApiResponse(true, String.format("Hello %1$s!", body)));
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
