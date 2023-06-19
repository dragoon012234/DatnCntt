package com.bkdn.cntt.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "notification")
public class NotificationEntity {

	@Id
	@GeneratedValue
	public Integer id;

	@Column(nullable = false)
	public Integer topic;

	@Column(nullable = false)
	public Integer user;

}
