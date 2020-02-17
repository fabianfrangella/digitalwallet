package com.digitalwallet.service.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import com.digitalwallet.persistence.entity.User

@Repository
interface UserRepository : JpaRepository<User, Long> {

    @Query("SELECT u.user_id FROM User u where u.email = :email AND u.password = :password")
    fun validateUser(@Param("email") username: String, @Param("password") password: String): Long

    @Query("SELECT max(cvu) FROM User")
    fun getLastCVU() : Long
}