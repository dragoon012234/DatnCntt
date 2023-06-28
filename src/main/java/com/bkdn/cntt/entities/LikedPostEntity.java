package com.bkdn.cntt.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "liked_post")
public class LikedPostEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Integer id;

	@Column
	public Integer post;

	@Column
	public Integer user;

	@Column
	public Boolean liked;

	@Column
	public Long createTime;

	public LikedPostEntity() {
	}

	public LikedPostEntity(Integer id, Integer post, Integer user, Boolean liked, Long createTime) {
		this.id = id;
		this.post = post;
		this.user = user;
		this.liked = liked;
		this.createTime = createTime;
	}

}
