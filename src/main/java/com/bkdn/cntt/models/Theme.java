package com.bkdn.cntt.models;

import com.bkdn.cntt.entities.ThemeEntity;

public class Theme {

	public Integer id;
	public String theme;
	public String description;
	public Integer parent;

	public Theme() {
	}

	public Theme(Integer id, String theme, String description, Integer parent) {
		this.id = id;
		this.theme = theme;
		this.description = description;
		this.parent = parent;
	}

	public Theme(ThemeEntity entity) {
		this(entity.id, entity.theme, entity.description, entity.parent);
	}

}
