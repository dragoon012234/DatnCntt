package com.bkdn.cntt.entities;

import com.bkdn.cntt.models.Theme;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "theme")
public class ThemeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Integer id;

	@Column
	public String theme;

	@Column
	public String description;

	@Column
	public Integer parent;

	@Column
	public Integer user;

	@Column
	public Long createTime;

	@Column
	public Long updateTime;

	public ThemeEntity() {
	}

	public ThemeEntity(Integer id, String theme, String description, Integer parent, Integer user, Long createTime,
			Long updateTime) {
		this.id = id;
		this.theme = theme;
		this.description = description;
		this.parent = parent;
		this.user = user;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public ThemeEntity(Theme model) {
		this(model.id, model.theme, model.description, model.parent, model.user, model.createTime, model.updateTime);
	}

}
