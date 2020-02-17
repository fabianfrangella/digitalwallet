package com.digitalwallet.service.service

import com.digitalwallet.persistence.dto.TransactionDTO
import com.digitalwallet.persistence.entity.Transaction
import com.digitalwallet.persistence.entity.User
import com.digitalwallet.service.exception.NotEnoughMoneyException
import com.digitalwallet.service.exception.TransferException
import com.digitalwallet.service.exception.TransferNegativeAmountException
import com.digitalwallet.service.exception.TransferToYourselfException
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

    private fun getAccountToTransfer(transactionDTO: TransactionDTO): Long {
        var userId: User = userRepository.findUserByCvu(transactionDTO.cvuTo!!)
        return userId.account!!.account_id!!
    }

    fun transfer(transactionDTO: TransactionDTO) {
        try {
            var transaction = buildTransaction(transactionDTO)
            transactionRepository.save(transaction)
        } catch (ex: Exception) {
            when (ex) {
                is TransferToYourselfException,
                is TransferNegativeAmountException,
                is NotEnoughMoneyException-> {
                    throw ex
                }
                else -> throw TransferException("The CVU ${transactionDTO.cvuTo} does not exist")
            }
        }
    }

    fun getTransactions(accountId: Long): List<Transaction> {
        return transactionRepository.findTransactions(accountId)
    }

    private fun buildTransaction(transactionDTO: TransactionDTO): Transaction {
        var transaction = Transaction()
        if (!isValidAmount(transactionDTO)) {
            throw NotEnoughMoneyException("You don't have enough money!")
        }
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

        return transaction
    }

    private fun isValidAmount(transactionDTO: TransactionDTO): Boolean {
        return accountRepository.getBalance(transactionDTO.accountFrom) - transactionDTO.amount!! >= 0
    }
}