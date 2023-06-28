package com.bkdn.cntt.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.bkdn.cntt.entities.InterestedTopicEntity;

public interface InterestedTopicRepository extends CrudRepository<InterestedTopicEntity, Integer> {

	ArrayList<InterestedTopicEntity> findByUser(Integer user);

	ArrayList<InterestedTopicEntity> findByTopic(Integer topic);

	ArrayList<InterestedTopicEntity> findByTopicAndInterested(Integer topic, Boolean interested);

	InterestedTopicEntity findByUserAndTopic(Integer user, Integer topic);

	ArrayList<InterestedTopicEntity> deleteAllByUser(Integer user);

}
