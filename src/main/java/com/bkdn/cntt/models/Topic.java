package com.bkdn.cntt.models;

import java.util.ArrayList;

import com.bkdn.cntt.entities.TopicEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Topic {

	public Integer id;
	public String topic;
	public Integer theme;
	public Integer user;
	public Long createTime;
	public Long updateTime;
	public Boolean closed;
	public ArrayList<InterestedTopic> interesteds = new ArrayList<InterestedTopic>();

	public Topic() {
	}

	public Topic(Integer id, String topic, Integer theme, Integer user, Long createTime, Long updateTime,
			Boolean closed) {
		this.id = id;
		this.topic = topic;
		this.theme = theme;
		this.user = user;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.closed = closed;
	}

	public Topic(TopicEntity entity) {
		this(entity.id, entity.topic, entity.theme, entity.user, entity.createTime, entity.updateTime, entity.closed);
	}

	public void configure(ArrayList<InterestedTopic> interesteds) {
		this.interesteds = interesteds;
	}

}
