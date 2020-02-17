package com.digitalwallet.service.service

import com.digitalwallet.persistence.dto.TransactionDTO
import com.digitalwallet.persistence.entity.Transaction
import com.digitalwallet.service.exception.TransferException
import com.digitalwallet.service.exception.TransferNegativeAmountException
import com.digitalwallet.service.exception.TransferToYourselfException
import com.digitalwallet.service.repository.AccountRepository
import com.digitalwallet.service.repository.TransactionRepository
import com.digitalwallet.service.repository.UserRepository
import org.hibernate.TransactionException
import org.postgresql.util.PSQLException
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

    private fun getAccountToTransfer(transactionDTO: TransactionDTO): Long {
        var userId = userRepository.getUserByCvu(transactionDTO.cvuTo!!)
        return accountRepository.getUserAccountId(userId)
    }

    fun transfer(transactionDTO: TransactionDTO) {
        var transaction = Transaction()
        try {
            transaction.accountTo = getAccountToTransfer(transactionDTO)
            if (transactionDTO.accountFrom == transaction.accountTo) {
                throw TransferToYourselfException("You can't transfer to yourself!")
            } else if (transactionDTO.amount!! <= 0) {
                throw TransferNegativeAmountException("You can't transfer a negative amount!")
            }
            transaction.accountFrom = transactionDTO.accountFrom
            transaction.amount = transactionDTO.amount
            transaction.date = transactionDTO.date
            transaction.description = transactionDTO.description
            transactionRepository.save(transaction)
        } catch(ex:Exception) {
            when(ex) {
                is TransferToYourselfException,
                is TransferNegativeAmountException -> {
                    throw ex
                }
                else -> throw TransferException("The CVU ${transactionDTO.cvuTo} does not exist")
            }
        }
    }

    fun getTransactions(accountId: Long): List<Transaction> {
        return transactionRepository.findTransactions(accountId)
    }
}