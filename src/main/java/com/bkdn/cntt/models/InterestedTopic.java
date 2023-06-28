package com.bkdn.cntt.models;

import com.bkdn.cntt.entities.InterestedTopicEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InterestedTopic {

	public Integer id;
	public Integer topic;
	public Integer user;
	public Boolean interested;
	public Long createTime;

	public InterestedTopic() {
	}

	public InterestedTopic(Integer id, Integer topic, Integer user, Boolean interested, Long createTime) {
		this.id = id;
		this.topic = topic;
		this.user = user;
		this.interested = interested;
		this.createTime = createTime;
	}

	public InterestedTopic(InterestedTopicEntity o) {
		this.id = o.id;
		this.topic = o.topic;
		this.user = o.user;
		this.interested = o.interested;
		this.createTime = o.createTime;
	}

}
