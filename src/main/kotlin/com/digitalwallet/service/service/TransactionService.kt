package com.digitalwallet.service.service

import com.digitalwallet.persistence.dto.TransactionDTO
import com.digitalwallet.persistence.entity.Account
import com.digitalwallet.persistence.entity.Transaction
import com.digitalwallet.persistence.entity.User
import com.digitalwallet.service.exception.NotEnoughMoneyException
import com.digitalwallet.service.exception.TransferException
import com.digitalwallet.service.exception.TransferNegativeAmountException
import com.digitalwallet.service.exception.TransferToYourselfException
import com.digitalwallet.service.repository.AccountRepository
import com.digitalwallet.service.repository.TransactionRepository
import com.digitalwallet.service.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class TransactionService {

    companion object {
        val log: Logger = LoggerFactory.getLogger(TransactionService::class.java)
    }
    @Autowired
    private lateinit var transactionRepository: TransactionRepository

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var accountRepository: AccountRepository

    /**
     * function to make a transfer between to accounts
     */
    fun transfer(transactionDTO: TransactionDTO) {
        try {
            log.info("Starting new transfer from: ${transactionDTO.accountFrom} to ${transactionDTO.cvuTo}")
            var transaction = buildTransaction(transactionDTO)
            transactionRepository.save(transaction)
            log.info("Transaction ${transaction.transaction_id} successful")
        } catch (ex: Exception) {
            when (ex) {
                is TransferToYourselfException,
                is TransferNegativeAmountException,
                is NotEnoughMoneyException-> {
                    log.info(ex.message)
                    throw ex
                }
                else -> {
                    val message = "The CVU ${transactionDTO.cvuTo} does not exist"
                    log.info(message)
                    throw TransferException(message)
                }
            }
        }
    }

    /**
     * function to get the transactions of a given account
     */
    fun getTransactions(accountId: Long): List<TransactionDTO> {
        log.info("Getting transactions from account ${accountId}")
        var account: Optional<Account> = accountRepository.findById(accountId)
        var cashInTransactions: List<TransactionDTO> = getCashInTransactions(account).map {
            transaction -> TransactionDTO(transaction,true)
        }
        var cashOutTransactions: List<TransactionDTO> = getCashOutTransactions(account).map {
            transaction -> TransactionDTO(transaction,false)
        }
        var transactions: MutableList<TransactionDTO> = mutableListOf()
        transactions.addAll(cashInTransactions)
        transactions.addAll(cashOutTransactions)
        log.info(transactions.toString())
        return transactions
    }

    private fun getCashInTransactions(account: Optional<Account>) : List<Transaction> {
        return transactionRepository.findByAccountTo(account) as MutableList<Transaction>
    }

    private fun getCashOutTransactions(account: Optional<Account>) : List<Transaction> {
        return transactionRepository.findByAccountFrom(account) as MutableList<Transaction>
    }

    private fun buildTransaction(transactionDTO: TransactionDTO): Transaction {
        var transaction = Transaction()
        if (!isValidAmount(transactionDTO)) {
            throw NotEnoughMoneyException("You don't have enough money!")
        }
        transaction.accountTo = getAccountToTransfer(transactionDTO)
        if (transactionDTO.accountFrom == transaction.accountTo!!.account_id) {
            throw TransferToYourselfException("You can't transfer to yourself!")
        } else if (transactionDTO.amount!! <= 0) {
            throw TransferNegativeAmountException("You can't transfer a negative amount!")
        }
        var accountFrom = accountRepository.findByIdOrNull(transactionDTO.accountFrom)
        transaction.accountFrom = accountFrom
        transaction.amount = transactionDTO.amount
        transaction.date = transactionDTO.date
        transaction.description = transactionDTO.description

        return transaction
    }

    private fun getAccountToTransfer(transactionDTO: TransactionDTO): Account {
        var userId: User = userRepository.findUserByCvu(transactionDTO.cvuTo!!)
        return userId.account!!
    }


    private fun isValidAmount(transactionDTO: TransactionDTO): Boolean {
        return accountRepository.getBalance(transactionDTO.accountFrom) - transactionDTO.amount!! >= 0
    }
}