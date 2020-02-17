package com.digitalwallet.service.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import com.digitalwallet.persistence.entity.User
import org.springframework.data.jpa.repository.Modifying

@Repository
interface UserRepository : JpaRepository<User, Long> {

    @Query("SELECT u.user_id FROM User u where u.email = :email AND u.password = :password")
    fun validateUser(@Param("email") username: String, @Param("password") password: String): Long

    fun findUserByCvu(@Param ("cvu") cvu: Long) : User

    @Query("SELECT max(cvu) FROM User")
    fun getLastCVU() : Long

    @Modifying
    @Query("UPDATE User u SET u.username = :username, u.email = :email " +
            "WHERE u.user_id = :user_id")
    fun editUser(@Param("email") email: String, @Param ("username") username: String, @Param("user_id") user_id: Long)


}