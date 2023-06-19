package com.bkdn.cntt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bkdn.cntt.configs.models.AccountModel;
import com.bkdn.cntt.entities.AccountEntity;
import com.bkdn.cntt.enums.PostType;
import com.bkdn.cntt.models.Account;
import com.bkdn.cntt.models.Post;
import com.bkdn.cntt.models.Theme;
import com.bkdn.cntt.models.Topic;
import com.bkdn.cntt.models.create.CreatePost;
import com.bkdn.cntt.models.general.ApiResponse;
import com.bkdn.cntt.models.general.ErrorResponse;
//import com.bkdn.cntt.models.RegisterData;
import com.bkdn.cntt.services.PostService;

@RestController
@RequestMapping(path = "/api/post")
public class PostController {

	@Autowired
	private PostService postService;

	@RequestMapping(path = "/theme/all", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<ApiResponse> apiAllTheme() {
		return allTheme();
	}

	private ResponseEntity<ApiResponse> allTheme() {
		try {
			return ResponseEntity.ok(new ApiResponse(true, postService.getAllTheme()));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
		}
	}

	@RequestMapping(path = "/theme/topic/all", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<ApiResponse> apiAllTopic(@RequestParam @Nullable Integer id,
			@RequestBody @Nullable Theme m) {
		if (m != null) {
			return allTopic(m.id);
		}
		return allTopic(id);
	}

	private ResponseEntity<ApiResponse> allTopic(Integer id) {
		try {
			return ResponseEntity.ok(new ApiResponse(true, postService.getAllTopic(id)));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
		}
	}

	@RequestMapping(path = "/theme/topic/post", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<ApiResponse> apiAllPost(@RequestParam @Nullable Integer id,
			@RequestBody @Nullable Topic m) {
		if (m != null) {
			return allPost(m.id);
		}
		return allPost(id);
	}

	private ResponseEntity<ApiResponse> allPost(Integer id) {
		try {
			return ResponseEntity.ok(new ApiResponse(true, postService.getAllPost(id)));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
		}
	}

//	@RequestMapping(path = "/all/username", method = { RequestMethod.GET, RequestMethod.POST })
//	public ResponseEntity<ApiResponse> apiAllPostByUsername(@RequestBody Account account) {
//		return allPostByUsername(account.getUsername());
//	}
//
//	private ResponseEntity<ApiResponse> allPostByUsername(String username) {
//		try {
//			return ResponseEntity.ok(new ApiResponse(true, postService.getAllByUsername(username)));
//		} catch (ErrorResponse e) {
//			return ResponseEntity.badRequest().body(new ApiResponse(e));
//		} catch (Exception e) {
//			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
//		}
//	}

//	@RequestMapping(path = "/all/userid", method = { RequestMethod.GET, RequestMethod.POST })
//	public ResponseEntity<ApiResponse> apiAllPostByUserId(@RequestBody Post post) {
//		return allPostByUserId(post.getUserId());
//	}
//
//	private ResponseEntity<ApiResponse> allPostByUserId(String userId) {
//		try {
//			return ResponseEntity.ok(new ApiResponse(true, postService.getAllByUserId(userId)));
//		} catch (ErrorResponse e) {
//			return ResponseEntity.badRequest().body(new ApiResponse(e));
//		} catch (Exception e) {
//			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
//		}
//	}

//	@RequestMapping(path = "/interested/userid", method = { RequestMethod.GET, RequestMethod.POST })
//	public ResponseEntity<ApiResponse> apiInterestedPostByUserId(@RequestBody @Nullable Post post) {
//		if (post == null)
//			return interestedPostPostByUserId(getAccount().getAccount().getId());
//		return interestedPostPostByUserId(post.getUserId());
//	}
//
//	private ResponseEntity<ApiResponse> interestedPostPostByUserId(String userId) {
//		try {
//			return ResponseEntity.ok(new ApiResponse(true, postService.getAllInterestedPostByUserId(userId)));
//		} catch (ErrorResponse e) {
//			return ResponseEntity.badRequest().body(new ApiResponse(e));
//		} catch (Exception e) {
//			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
//		}
//	}

	@RequestMapping(path = "/theme/new", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
	@PreAuthorize(value = "hasAnyAuthority('LECTURER')")
	public ResponseEntity<ApiResponse> apiNewTheme(@RequestBody Theme m) {
//		AccountModel accountModel = getAccount();
//		m.user = accountModel.getAccount().id);
		return create(m);
	}

	public ResponseEntity<ApiResponse> create(Theme m) {
		try {
			m = postService.create(m);
			return ResponseEntity.ok(new ApiResponse(true, m));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
		}
	}

	@RequestMapping(path = "/theme/topic/new", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
	@PreAuthorize(value = "hasAnyAuthority('STUDENT', 'LECTURER')")
	public ResponseEntity<ApiResponse> apiNewTopic(@RequestBody CreatePost m) {
		AccountModel accountModel = getAccount();
		m.topic.user = accountModel.getAccount().id;
		m.post.user = accountModel.getAccount().id;
		return create(m);
	}

	public ResponseEntity<ApiResponse> create(CreatePost m) {
		try {
			var t = postService.create(m.topic, m.post);
			m.topic = t;
			return ResponseEntity.ok(new ApiResponse(true, m));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
		}
	}

	@RequestMapping(path = "/theme/topic/post/new", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
	@PreAuthorize(value = "hasAnyAuthority('STUDENT', 'LECTURER')")
	public ResponseEntity<ApiResponse> apiNewPost(@RequestBody Post m) {
		AccountModel accountModel = getAccount();
		m.user = accountModel.getAccount().id;
		return create(m);
	}

	public ResponseEntity<ApiResponse> create(Post m) {
		try {
			m = postService.create(m);
			return ResponseEntity.ok(new ApiResponse(true, m));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
		}
	}

//	@RequestMapping(path = "/get", method = { RequestMethod.GET, RequestMethod.POST })
//	public ResponseEntity<ApiResponse> apiGetAllPost(@RequestParam @Nullable String id,
//			@RequestBody @Nullable Post post) {
//		if (post != null) {
//			return getPost(post.getId());
//		}
//		return getPost(id);
//	}
//
//	private ResponseEntity<ApiResponse> getPost(String id) {
//		try {
//			if (StringUtils.hasText(id))
//				return ResponseEntity.ok(new ApiResponse(true, postService.get(id)));
//			else
//				return ResponseEntity.ok(new ApiResponse(true, postService.getAll()));
//		} catch (Exception e) {
//			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
//		}
//	}

//	@RequestMapping(path = "/close", method = { RequestMethod.GET, RequestMethod.POST })
//	@PreAuthorize(value = "hasAnyAuthority('SINGLEUSER', 'ORGANIZATION')")
//	public ResponseEntity<ApiResponse> apiClosePost(@RequestParam @Nullable String id,
//			@RequestBody @Nullable Post post) {
//		if (post != null) {
//			return closePost(post.getId());
//		}
//		return closePost(id);
//	}
//
//	private ResponseEntity<ApiResponse> closePost(String id) {
//		try {
//			AccountEntity account = getAccount().getAccount();
//			return ResponseEntity.ok(new ApiResponse(true, postService.close(id, account)));
//		} catch (Exception e) {
//			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
//		}
//	}

//	@RequestMapping(path = "/find", method = { RequestMethod.GET, RequestMethod.POST })
//	public ResponseEntity<ApiResponse> apiFindPost(@RequestBody Post post) {
//		return findPost(post);
//	}
//
//	private ResponseEntity<ApiResponse> findPost(Post post) {
//		if (post == null)
//			return ResponseEntity.badRequest().body(new ApiResponse(false, "Use get if you want get all!"));
//		try {
//			return ResponseEntity.ok(new ApiResponse(true, postService.find(post)));
//		} catch (Exception e) {
//			return ResponseEntity.badRequest().body(new ApiResponse(false, e.getMessage()));
//		}
//	}

	private AccountModel getAccount() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		try {
			return (AccountModel) authentication.getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}
