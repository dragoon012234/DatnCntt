package com.bkdn.cntt.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "interested_topic")
public class InterestedTopicEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Integer id;

	@Column
	public Integer topic;

	@Column
	public Integer user;

	@Column
	public Boolean interested;

	@Column
	public Long createTime;

	public InterestedTopicEntity() {
	}

	public InterestedTopicEntity(Integer id, Integer topic, Integer user, Boolean interested, Long createTime) {
		this.id = id;
		this.topic = topic;
		this.user = user;
		this.interested = interested;
		this.createTime = createTime;
	}

}
