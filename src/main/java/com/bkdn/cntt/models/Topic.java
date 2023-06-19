package com.bkdn.cntt.models;

import com.bkdn.cntt.entities.TopicEntity;

public class Topic {

	public Integer id;
	public Integer theme;
	public Integer user;
	public String topic;
	public Long createTime;
	public Boolean closed;
	
	public Topic() {
	}

	public Topic(Integer id, String topic, Integer theme, Integer user, Long createTime, Boolean closed) {
		super();
		this.id = id;
		this.topic = topic;
		this.theme = theme;
		this.user = user;
		this.createTime = createTime;
		this.closed = closed;
	}

	public Topic(TopicEntity entity) {
		this(entity.id, entity.topic, entity.theme, entity.user, entity.createTime, entity.closed);
	}

}
