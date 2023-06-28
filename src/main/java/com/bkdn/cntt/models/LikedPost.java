package com.bkdn.cntt.models;

import com.bkdn.cntt.entities.LikedPostEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LikedPost {

	public Integer id;
	public Integer post;
	public Integer user;
	public Boolean liked;
	public Long createTime;

	public LikedPost() {
	}

	public LikedPost(Integer id, Integer post, Integer user, Boolean liked, Long createTime) {
		this.id = id;
		this.post = post;
		this.user = user;
		this.liked = liked;
		this.createTime = createTime;
	}

	public LikedPost(LikedPostEntity o) {
		this.id = o.id;
		this.post = o.post;
		this.user = o.user;
		this.liked = o.liked;
		this.createTime = o.createTime;
	}

}
