package com.digitalwallet.service.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import com.digitalwallet.persistence.entity.Account
import com.digitalwallet.persistence.entity.User
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

@Repository
interface AccountRepository : JpaRepository<Account, Long>{

    @Query("SELECT a.account_id FROM Account a WHERE a.user_id = :user_id")
    fun getUserAccountId(@Param("user_id") user_id: User) : Long

    @Query("SELECT a.balance FROM Account a WHERE a.account_id = :account_id")
    fun getBalance(@Param("account_id") account_id: Long?) : Long

}