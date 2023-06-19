package com.bkdn.cntt.models;

import com.bkdn.cntt.entities.PostEntity;

public class Post {

	public Integer id;
	public Integer topic;
	public String content;
	public Integer user;
	public Integer replyOn;
	public Long createTime;

	public Post() {
	}

	public Post(Integer id, Integer topic, String content, Integer user, Integer replyOn, Long createTime) {
		this.id = id;
		this.topic = topic;
		this.content = content;
		this.user = user;
		this.replyOn = replyOn;
		this.createTime = createTime;
	}

	public Post(PostEntity entity) {
		this(entity.id, entity.topic, entity.content, entity.user, entity.replyOn, entity.createTime);
	}

}
