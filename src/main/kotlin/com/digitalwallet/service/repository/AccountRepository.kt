package com.digitalwallet.service.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.digitalwallet.persistence.entity.Account
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

@Repository
interface AccountRepository : JpaRepository<Account, Long>{

    @Query("SELECT balance FROM Account a WHERE a.user_id = :user_id")
    fun getBalanceByUserId(@Param ("user_id") user_id: Long) : Long
}