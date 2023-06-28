package com.bkdn.cntt.models;

import java.util.ArrayList;

import com.bkdn.cntt.entities.PostEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {

	public Integer id;
	public Integer topic;
	public String content;
	public Integer user;
	public Integer replyOn;
	public Long createTime;
	public Long updateTime;
	public ArrayList<LikedPost> likes = new ArrayList<LikedPost>();

	public Post() {
	}

	public Post(Integer id, Integer topic, String content, Integer user, Integer replyOn, Long createTime,
			Long updateTime) {
		this.id = id;
		this.topic = topic;
		this.content = content;
		this.user = user;
		this.replyOn = replyOn;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public Post(PostEntity entity) {
		this(entity.id, entity.topic, entity.content, entity.user, entity.replyOn, entity.createTime,
				entity.updateTime);
	}

	public void configure(ArrayList<LikedPost> likes) {
		this.likes = likes;
	}

}
