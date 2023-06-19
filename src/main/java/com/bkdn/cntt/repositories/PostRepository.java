package com.bkdn.cntt.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.bkdn.cntt.entities.PostEntity;

public interface PostRepository extends CrudRepository<PostEntity, Integer> {

	ArrayList<PostEntity> findAll();

	ArrayList<PostEntity> findAllByTopic(Integer topic);
	
	ArrayList<PostEntity> deleteAllByUser(Integer user);

}
