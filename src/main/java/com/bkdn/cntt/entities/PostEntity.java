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
	public Integer id;

	@Column(nullable = false)
	public Integer topic;

	@Column(nullable = false)
	public String content;

	@Column(nullable = false)
	public Integer user;

	@Column(nullable = true)
	public Integer replyOn;

	@Column(nullable = false)
	public Long createTime;

	public PostEntity() {
	}

	public PostEntity(Integer id, Integer topic, String content, Integer user, Integer replyOn, Long createTime) {
		this.id = id;
		this.topic = topic;
		this.content = content;
		this.user = user;
		this.replyOn = replyOn;
		this.createTime = createTime;
	}

	public PostEntity(Post model) {
		this(model.id, model.topic, model.content, model.user, model.replyOn, model.createTime);
	}

	@Override
	public String toString() {
		return "PostEntity [" + (id != null ? "id=" + id + ", " : "") + (topic != null ? "topic=" + topic + ", " : "")
				+ (content != null ? "content=" + content + ", " : "") + (user != null ? "user=" + user + ", " : "")
				+ (replyOn != null ? "replyOn=" + replyOn + ", " : "")
				+ (createTime != null ? "createTime=" + createTime : "") + "]";
	}

}
