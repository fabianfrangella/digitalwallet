package com.digitalwallet.service.service

import com.digitalwallet.service.repository.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.digitalwallet.persistence.dto.TransactionDTO
import com.digitalwallet.service.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull

@Service
class AccountService {
	
	@Autowired
	private lateinit var accountRepository: AccountRepository

	@Autowired
	private lateinit var userRepository: UserRepository
	
	fun updateBalance(transactionDTO: TransactionDTO){
		val accountFrom = accountRepository.findByIdOrNull(transactionDTO.accountFrom!!)
		val accountTo = accountRepository.findByIdOrNull(
				accountRepository.getUserAccountId(
				userRepository.findUserByCvu(transactionDTO.cvuTo!!)))
		accountFrom!!.balance = accountFrom.balance?.minus(transactionDTO.amount!!)
		accountTo!!.balance = accountTo.balance?.plus(transactionDTO.amount!!)
		accountRepository.save(accountFrom)
		accountRepository.save(accountTo)
	} 
}