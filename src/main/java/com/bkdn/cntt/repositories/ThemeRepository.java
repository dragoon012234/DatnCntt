package com.bkdn.cntt.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.bkdn.cntt.entities.ThemeEntity;

public interface ThemeRepository extends CrudRepository<ThemeEntity, Integer> {

	public ArrayList<ThemeEntity> findAll();

}
