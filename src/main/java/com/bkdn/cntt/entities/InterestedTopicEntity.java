package com.bkdn.cntt.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "interested_topic")
public class InterestedTopicEntity {

	@Id
	@GeneratedValue
	public Integer id;

	@Column(nullable = false)
	public Integer topic;

	@Column(nullable = false)
	public Integer user;

	public InterestedTopicEntity() {
	}

	public InterestedTopicEntity(Integer id, Integer topic, Integer user) {
		this.id = id;
		this.topic = topic;
		this.user = user;
	}

}
