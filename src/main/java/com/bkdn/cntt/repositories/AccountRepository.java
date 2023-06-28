package com.bkdn.cntt.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.bkdn.cntt.entities.AccountEntity;

public interface AccountRepository extends CrudRepository<AccountEntity, Integer> {

	AccountEntity findByUsername(String username);

	ArrayList<AccountEntity> findAllByWatcher(Boolean watcher);

}
