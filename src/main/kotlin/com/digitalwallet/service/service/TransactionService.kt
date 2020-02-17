package com.digitalwallet.service.service

import com.digitalwallet.persistence.dto.TransactionDTO
import com.digitalwallet.persistence.entity.Transaction
import com.digitalwallet.service.repository.AccountRepository
import com.digitalwallet.service.repository.TransactionRepository
import com.digitalwallet.service.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TransactionService {

	@Autowired
	private lateinit var transactionRepository: TransactionRepository

	@Autowired
	private lateinit var userRepository: UserRepository

	@Autowired
	private lateinit var accountRepository: AccountRepository

	fun transfer(transactionDTO: TransactionDTO){
		var transaction = Transaction()
		var userId = userRepository.getUserByCvu(transactionDTO.cvuTo!!)
		transaction.accountTo = accountRepository.getUserAccountId(userId)
		transaction.accountFrom = transactionDTO.accountFrom
		transaction.amount = transactionDTO.amount
		transaction.date = transactionDTO.date
		transaction.description = transactionDTO.description
		transactionRepository.save(transaction)
	}

	fun getTransactions(accountId: Long): List<Transaction> {
		return transactionRepository.findTransactions(accountId)
	}
}