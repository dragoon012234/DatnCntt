package com.bkdn.cntt.models;

import com.bkdn.cntt.entities.NotificationEntity;
import com.bkdn.cntt.enums.NotificationType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Notification {

	public Integer id;

	public Integer theme;

	public Integer topic;

	public Integer post;

	public Integer actor;

	public Integer user;

	public NotificationType type;

	public Long createTime;

	public Boolean readed = false;

	public Notification() {
	}

	public Notification(Integer id, Integer theme, Integer topic, Integer post, Integer actor, Integer user,
			NotificationType type, Long createTime, Boolean readed) {
		this.id = id;
		this.theme = theme;
		this.topic = topic;
		this.post = post;
		this.actor = actor;
		this.user = user;
		this.type = type;
		this.createTime = createTime;
		this.readed = readed;
	}

	public Notification(NotificationEntity entity) {
		this(entity.id, entity.theme, entity.topic, entity.post, entity.actor, entity.user, entity.type,
				entity.createTime, entity.readed);
	}

}
