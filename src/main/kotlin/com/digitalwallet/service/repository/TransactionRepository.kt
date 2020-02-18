package com.digitalwallet.service.repository

import com.digitalwallet.persistence.entity.Account
import com.digitalwallet.persistence.entity.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TransactionRepository : JpaRepository<Transaction,Long> {

    fun findByAccountFrom(@Param("accountFrom") accountFrom: Optional<Account>) : List<Transaction>

    fun findByAccountTo(@Param("accountTo") accountTo: Optional<Account>) : List<Transaction>
}