package com.sjprogramming.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjprogramming.bank.entity.Account;
import com.sjprogramming.bank.repo.AccountReposiitory;
@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountReposiitory repo;
	 
	@Override
	public Account createAccount(Account account) {  
		Account account_saved= repo.save(account);
		return account_saved;
	}

	@Override
	public Account getAccoutDetailsByAccountNumber(Long accountNumber) {
		Optional<Account> account= repo.findById(accountNumber);
		if(account.isEmpty()) {
			throw new RuntimeException("Account is not present ");
		}
		Account account_found = account.get(); 
		return account_found;
	}

	@Override
	public List<Account> getAllAccountDetails() {
	    List<Account> ListOfAccounts = repo.findAll();
		return ListOfAccounts;
	}

	@Override
	public Account depositAmount(Long accountNumber, Double amount) {
		Optional<Account> account = repo.findById(accountNumber);
		if(account.isEmpty()) {
			throw new RuntimeException("Account is not present");
		}
		Account accountPresent=account.get();
		Double totalBalance = accountPresent.getAccount_balance()+amount;
		accountPresent.setAccount_balance(totalBalance);
		repo.save(accountPresent);
		
		return accountPresent;
	}

	@Override
	public Account withdrwaAmount(Long accountNumber, Double amount) {
		Optional<Account> account = repo.findById(accountNumber);
		if(account.isEmpty()) {
			throw new RuntimeException("Account is not present");
		}
		Account accountPresent=account.get();
		
		Double accountBalance = accountPresent.getAccount_balance()-amount;
		accountPresent.setAccount_balance(accountBalance);
		repo.save(accountPresent);
		
		return accountPresent;
	}

	@Override
	public void closeAccount(Long accountNumber) {
		
		getAccoutDetailsByAccountNumber(accountNumber);
		repo.deleteById(accountNumber);
		

	}

}
