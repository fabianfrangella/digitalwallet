package com.digitalwallet.service.repository

import com.digitalwallet.persistence.entity.DigitalWalletCard
import com.digitalwallet.persistence.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface DigitalWalletCardRepository : JpaRepository<DigitalWalletCard, Long> {

    @Query("SELECT c FROM DigitalWalletCard c WHERE c.user_id = :user_id")
    fun findByUserId(@Param("user_id") user_id: User) : List<DigitalWalletCard>

    @Query("SELECT max(cardNumber) FROM DigitalWalletCard")
    fun findLastCardNumber() : Long

}