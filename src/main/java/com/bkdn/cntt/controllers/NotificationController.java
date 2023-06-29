package com.bkdn.cntt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bkdn.cntt.configs.models.AccountModel;
import com.bkdn.cntt.models.general.ApiResponse;
import com.bkdn.cntt.services.PostService;

@RestController
@RequestMapping(path = "/api/noti")
public class NotificationController {

	@Autowired
	private PostService postService;

	@RequestMapping(path = "/all", method = { RequestMethod.GET, RequestMethod.POST })
	@PreAuthorize(value = "hasAnyAuthority('ADMIN', 'STUDENT', 'LECTURER')")
	public ResponseEntity<ApiResponse> apiAllNoti() {
		return allNoti();
	}

	private ResponseEntity<ApiResponse> allNoti() {
		try {
			var user = getAccount().getAccount();
			return ResponseEntity.ok(new ApiResponse(true, postService.getAllNotification(user.id)));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
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
