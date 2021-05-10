package com.example.ecsitedeveloplearning.ec.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecsitedeveloplearning.ec.account.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	
	// ここはDBに Access するためのRepositoryです。
	// JPaを extendsして設定する必要があります。

}
