package com.bkdn.cntt.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.bkdn.cntt.entities.NotificationEntity;

public interface NotificationRepository extends CrudRepository<NotificationEntity, Integer> {

	ArrayList<NotificationEntity> findAllByUser(Integer user);

	ArrayList<NotificationEntity> deleteAllByUser(Integer user);

}
