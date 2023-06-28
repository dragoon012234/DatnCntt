package com.bkdn.cntt.entities;

import com.bkdn.cntt.enums.NotificationType;
import com.bkdn.cntt.models.Notification;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "notification")
public class NotificationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Integer id;

	@Column
	public Integer theme;

	@Column
	public Integer topic;

	@Column
	public Integer post;

	@Column
	public Integer actor;

	@Column
	public Integer user;

	@Column
	public NotificationType type;

	@Column
	public Long createTime;

	@Column
	public Boolean readed = false;

	public NotificationEntity() {
	}

	public NotificationEntity(Integer id, Integer theme, Integer topic, Integer post, Integer actor, Integer user,
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

	public NotificationEntity(Notification entity) {
		this(entity.id, entity.theme, entity.topic, entity.post, entity.actor, entity.user, entity.type,
				entity.createTime, entity.readed);
	}

	public void update(Notification model) {
		if (model.readed) {
			this.readed = true;
		}
	}

}
