package com.sjprogramming.bank.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sjprogramming.bank.entity.Account;

public interface AccountReposiitory extends JpaRepository<Account, Long> {

}
