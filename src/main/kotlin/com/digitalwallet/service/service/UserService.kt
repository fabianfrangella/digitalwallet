package com.digitalwallet.service.service

import org.springframework.beans.factory.annotation.Autowired
import com.digitalwallet.persistence.dto.LoginUserDTO
import com.digitalwallet.persistence.dto.UserDTO
import com.digitalwallet.persistence.entity.User
import com.digitalwallet.service.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    private lateinit var userRepository: UserRepository;

    fun login(loginUser: LoginUserDTO)
            = !userRepository.validateUser(loginUser.username,loginUser.password).isNullOrEmpty()
}