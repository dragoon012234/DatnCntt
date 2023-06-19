package com.bkdn.cntt.models.create;

import com.bkdn.cntt.models.Post;
import com.bkdn.cntt.models.Topic;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePost {

	public Topic topic;
	public Post post;

	public CreatePost() {
	}

	public CreatePost(Topic topic, Post post) {
		this.topic = topic;
		this.post = post;
	}

}
