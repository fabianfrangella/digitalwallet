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
		transactionDTO.accountTo?.let { transactionDTO.amount?.let {
			it1 -> accountRepository.updateBalance(it, it1) } }

		transactionDTO.accountFrom?.let { transactionDTO.amount?.times(-1)?.let {
			it1 -> accountRepository.updateBalance(it, it1) } }
	} 
}