package com.bkdn.cntt.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.bkdn.cntt.entities.LikedPostEntity;

public interface LikedPostRepository extends CrudRepository<LikedPostEntity, Integer> {

	ArrayList<LikedPostEntity> findAllByPost(Integer post);

	ArrayList<LikedPostEntity> findByPostAndLiked(Integer post, Boolean liked);

	ArrayList<LikedPostEntity> findAllByUser(Integer user);

	LikedPostEntity findByPostAndUser(Integer post, Integer user);

	ArrayList<LikedPostEntity> deleteAllByUser(Integer user);

}
