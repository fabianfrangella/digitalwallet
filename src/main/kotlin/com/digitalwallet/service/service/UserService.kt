package com.digitalwallet.service.service

import org.springframework.beans.factory.annotation.Autowired
import com.digitalwallet.persistence.dto.LoginUserDTO
import com.digitalwallet.persistence.dto.UserDataDTO
import com.digitalwallet.persistence.dto.UserRegisterDTO
import com.digitalwallet.persistence.entity.Account
import com.digitalwallet.persistence.entity.User
import com.digitalwallet.service.repository.AccountRepository
import com.digitalwallet.service.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserService {

    @Autowired
    private lateinit var userRepository: UserRepository;

    @Autowired
    private lateinit var accountRepository: AccountRepository;

    fun login(loginUser: LoginUserDTO) : String{
        return if (!userRepository.validateUser(loginUser.email,loginUser.password).isNullOrEmpty()) {
            "login successful"
        } else {
            "Email or Password is wrong"
        }
    }

    fun register(userDTO: UserRegisterDTO) : User{
        var user = buildUser(userDTO)
        var newAccount = Account()
        userRepository.save(user)
        newAccount.balance = 200;
        newAccount.isBlocked = false;
        newAccount.user_id = user.user_id;
        accountRepository.save(newAccount)
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
        user.cvu = userRepository.getLastCVU() + 1
        return user;
    }

    fun getUserData(id: Long?): UserDataDTO {
        var result = UserDataDTO()
        var userFound: Optional <User>
        try {
            userFound = id?.let { userRepository.findById(it) } as Optional<User>
            result.username = userFound.get().username
            result.email = userFound.get().email.toString()
            result.user_id = userFound.get().user_id
            result.idCard = userFound.get().idCard!!
            result.cvu = userFound.get().cvu!!
            result.balance = id?.let {accountRepository.getBalanceByUserId(it)}
        } catch (e: Exception) {
            throw e
        }
        return result
    }
}