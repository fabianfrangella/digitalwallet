package com.digitalwallet.service.service

import com.digitalwallet.persistence.dto.TransactionDTO
import com.digitalwallet.persistence.entity.Transaction
import com.digitalwallet.service.repository.AccountRepository
import com.digitalwallet.service.repository.TransactionRepository
import org.springframework.stereotype.Service

@Service
class TransactionService {
	
	private lateinit var transactionRepository: TransactionRepository
	private lateinit var accountRepository: AccountRepository
	
	fun transfer(transactionDTO: TransactionDTO){
		var transaction = Transaction()
		transaction.accountFrom = transactionDTO.accountFrom
		transaction.accountTo = transactionDTO.accountTo
		transaction.amount = transactionDTO.amount
		transactionRepository.save(transaction)
		
		
	}
}