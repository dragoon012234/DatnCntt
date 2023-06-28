package com.bkdn.cntt.entities;

import com.bkdn.cntt.models.Post;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "post")
public class PostEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Integer id;

	@Column
	public Integer topic;

	@Column
	public String content;

	@Column
	public Integer user;

	@Column
	public Integer replyOn;

	@Column
	public Long createTime;

	@Column
	public Long updateTime;

	public PostEntity() {
	}

	public PostEntity(Integer id, Integer topic, String content, Integer user, Integer replyOn, Long createTime,
			Long updateTime) {
		this.id = id;
		this.topic = topic;
		this.content = content;
		this.user = user;
		this.replyOn = replyOn;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public PostEntity(Post model) {
		this(model.id, model.topic, model.content, model.user, model.replyOn, model.createTime, model.updateTime);
	}

}
