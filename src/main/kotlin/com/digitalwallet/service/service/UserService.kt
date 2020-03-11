package com.digitalwallet.service.service

import com.digitalwallet.persistence.dto.EditUserDTO
import org.springframework.beans.factory.annotation.Autowired
import com.digitalwallet.persistence.dto.LoginUserDTO
import com.digitalwallet.persistence.dto.UserDataDTO
import com.digitalwallet.persistence.dto.UserRegisterDTO
import com.digitalwallet.persistence.entity.Account
import com.digitalwallet.persistence.entity.User
import com.digitalwallet.service.exception.UserException
import com.digitalwallet.service.repository.AccountRepository
import com.digitalwallet.service.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserService {

    companion object {
        val log: Logger = LoggerFactory.getLogger(UserService::class.java)
    }

    @Autowired
    private lateinit var userRepository: UserRepository;

    @Autowired
    private lateinit var accountRepository: AccountRepository;

    @Autowired
    private lateinit var digitalWalletCardService: DigitalWalletCardService;


    fun login(loginUser: LoginUserDTO) : Long {
		var encodedPassword = Base64.getEncoder().encodeToString(loginUser.password.toByteArray())
        var userId: Long?
        try {
            log.info("Login user ${loginUser.email}")
            userId = userRepository.validateUser(loginUser.email, encodedPassword)
        } catch (ex: Exception) {
            log.info(ex.message)
            throw UserException("Wrong email or password!")
        }
        return userId
    }

    fun register(userDTO: UserRegisterDTO) : User{
        val user = buildUser(userDTO)
        val newAccount = buildAccount(user)
        accountRepository.save(newAccount)
        digitalWalletCardService.createCard(user!!.user_id!!)
        return user
    }

    private fun buildAccount(user: User): Account {
        val account = Account()
        account.balance = 200
        account.isBlocked = false
        account.user_id = user
        return account
    }
    /**
     * builds a user from a userDTO
     */
    private fun buildUser(userDTO: UserRegisterDTO) : User {
        var user = User();
        user.username = userDTO.username
        user.email = userDTO.email
        user.idCard = userDTO.idCard
        user.password = Base64.getEncoder().encodeToString(userDTO.password.toByteArray())
        user.firstname = userDTO.firstname
        user.lastname = userDTO.lastname
        if (userRepository.findAll().isNullOrEmpty()){
            user.cvu = 111111
        } else {
            user.cvu = userRepository.getLastCVU() + 1
        }
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
            result.balance = userFound.get().account!!.balance!!
        } catch (e: Exception) {
            throw e
        }
        return result
    }

    fun getUserAccountId(userId: Long) : Long {
        var user = User(user_id = userId)
        return accountRepository.getUserAccountId(user)
    }

    fun editUser(user: EditUserDTO) {
        var editedUser = userRepository.findByIdOrNull(user.userId)
        editedUser!!.email = user.email
        editedUser!!.username = user.username
        userRepository.save(editedUser)
    }
}