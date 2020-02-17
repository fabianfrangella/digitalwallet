package com.digitalwallet.service.repository

import com.digitalwallet.persistence.entity.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TransactionRepository : JpaRepository<Transaction,Long> {

    @Query("SELECT t FROM Transaction t WHERE t.accountFrom = :accountId OR t.accountTo = :accountId")
    fun findTransactions(@Param("accountId") accountId: Long): List<Transaction>
}