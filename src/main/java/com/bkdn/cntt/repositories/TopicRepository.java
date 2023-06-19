package com.bkdn.cntt.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.bkdn.cntt.entities.TopicEntity;

public interface TopicRepository extends CrudRepository<TopicEntity, Integer> {

	ArrayList<TopicEntity> findAll();
	
	ArrayList<TopicEntity> findAllByTheme(Integer theme);
	
	ArrayList<TopicEntity> deleteAllByUser(Integer user);

}
