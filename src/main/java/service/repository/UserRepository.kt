package service.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import persistence.entity.User

interface UserRepository : JpaRepository<User, Long> {
    
    @Query("SELECT u.username FROM User u where u.username = :username AND u.password = :password")
    fun validateUser(@Param("username") username: String, @Param("password") password: String): String
}