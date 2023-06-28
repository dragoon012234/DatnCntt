package com.bkdn.cntt.models;

import com.bkdn.cntt.entities.ThemeEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Theme {

	public Integer id;
	public String theme;
	public String description;
	public Integer parent;
	public Integer user;
	public Long createTime;
	public Long updateTime;

	public Theme() {
	}

	public Theme(Integer id, String theme, String description, Integer parent, Integer user, Long createTime,
			Long updateTime) {
		this.id = id;
		this.theme = theme;
		this.description = description;
		this.parent = parent;
		this.user = user;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public Theme(ThemeEntity entity) {
		this(entity.id, entity.theme, entity.description, entity.parent, entity.user, entity.createTime,
				entity.updateTime);
	}

}
