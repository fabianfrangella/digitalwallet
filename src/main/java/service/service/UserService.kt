package service.service

import org.springframework.beans.factory.annotation.Autowired
import persistence.dto.LoginUserDTO
import persistence.dto.UserDTO
import persistence.entity.User
import service.repository.UserRepository

class UserService {

    @Autowired
    private lateinit var userRepository: UserRepository;

    fun login(loginUser: LoginUserDTO)
            = !userRepository.validateUser(loginUser.username,loginUser.password).isNullOrEmpty()
}