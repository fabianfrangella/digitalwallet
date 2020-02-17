package com.digitalwallet.service.service

import com.digitalwallet.service.repository.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.digitalwallet.persistence.dto.TransactionDTO

@Service
class AccountService {
	
	@Autowired
	private lateinit var accountRepository: AccountRepository
	
	fun updateBalance(transactionDTO: TransactionDTO){
		accountRepository.updateBalance(transactionDTO.accountTo,transactionDTO.amount)

		accountRepository.updateBalance(transactionDTO.accountFrom, transactionDTO.amount?.times(-1))
	} 
}