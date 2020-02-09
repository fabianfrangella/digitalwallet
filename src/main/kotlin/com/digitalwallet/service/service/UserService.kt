package com.digitalwallet.service.service

import org.springframework.beans.factory.annotation.Autowired
import com.digitalwallet.persistence.dto.LoginUserDTO
import com.digitalwallet.persistence.dto.UserDTO
import com.digitalwallet.persistence.dto.UserRegisterDTO
import com.digitalwallet.persistence.entity.User
import com.digitalwallet.service.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    private lateinit var userRepository: UserRepository;

    fun login(loginUser: LoginUserDTO)
            = !userRepository.validateUser(loginUser.username,loginUser.password).isNullOrEmpty()

    fun register(userDTO: UserRegisterDTO) : User{
        var user = buildUser(userDTO)
        userRepository.save(user)
        return user
    }

    /**
     * builds a user from a userDTO
     */
    private fun buildUser(userDTO: UserRegisterDTO) : User {
        var user = User();
        user.username = userDTO.username
        user.email = userDTO.email
        user.idCard = userDTO.idCard
        user.password = userDTO.password
        return user;
    }
}