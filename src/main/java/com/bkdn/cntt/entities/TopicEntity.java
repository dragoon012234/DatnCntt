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
	@Column
	public Integer id;

	@Column
	public String topic;

	@Column
	public Integer theme;

	@Column
	public Integer user;

	@Column
	public Long createTime;

	@Column
	public Long updateTime;

	@Column
	public Boolean closed;

	public TopicEntity() {
	}

	public TopicEntity(Integer id, String topic, Integer theme, Integer user, Long createTime, Long updateTime,
			Boolean closed) {
		this.id = id;
		this.topic = topic;
		this.theme = theme;
		this.user = user;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.closed = closed;
	}

	public TopicEntity(Topic model) {
		this(model.id, model.topic, model.theme, model.user, model.createTime, model.updateTime, model.closed);
	}

}
