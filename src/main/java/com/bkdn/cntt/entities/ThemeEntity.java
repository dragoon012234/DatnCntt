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
	public Integer id;

	@Column(nullable = false)
	public String theme;

	@Column(nullable = true)
	public String description;

	@Column(nullable = true)
	public Integer parent;

	public ThemeEntity() {
	}

	public ThemeEntity(Integer id, String theme, String description, Integer parent) {
		this.id = id;
		this.theme = theme;
		this.description = description;
		this.parent = parent;
	}

	public ThemeEntity(Theme model) {
		this(model.id, model.theme, model.description, model.parent);
	}

}
