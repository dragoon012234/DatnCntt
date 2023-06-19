package com.bkdn.cntt.entities;

import com.bkdn.cntt.models.Topic;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "topic")
public class TopicEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;

	@Column(nullable = false)
	public String topic;

	@Column(nullable = false)
	public Integer theme;

	@Column(nullable = false)
	public Integer user;

	@Column(nullable = false)
	public Long createTime;

	@Column(nullable = false, columnDefinition = "boolean default false")
	public Boolean closed;

	public TopicEntity() {
	}

	public TopicEntity(Integer id, String topic, Integer theme, Integer user, Long createTime, Boolean closed) {
		this.id = id;
		this.topic = topic;
		this.theme = theme;
		this.user = user;
		this.createTime = createTime;
		this.closed = closed;
	}

	public TopicEntity(Topic model) {
		this(model.id, model.topic, model.theme, model.user, model.createTime, model.closed);
	}

}
