package com.mine.tradechamp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mine.tradechamp.model.Account;

// all the DB methods are already created automatically from this class
public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByAccountId(Long accountId); 
}
