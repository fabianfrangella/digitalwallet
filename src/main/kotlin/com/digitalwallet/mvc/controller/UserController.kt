package com.digitalwallet.mvc.controller

import com.digitalwallet.persistence.dto.LoginUserDTO
import com.digitalwallet.persistence.dto.UserDataDTO
import com.digitalwallet.persistence.dto.UserRegisterDTO
import com.digitalwallet.service.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@CrossOrigin(origins= ["http://localhost:3000"])
@RequestMapping("/")
class UserController {

    @Autowired
    private lateinit var userService: UserService;

    @PostMapping(path = ["/register"],
            consumes = [MediaType.APPLICATION_JSON_VALUE],
            produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus (HttpStatus.CREATED)
    @ResponseBody
    fun register(@RequestBody userRegister: UserRegisterDTO) = userService.register(userRegister);

    @PostMapping(path = ["/login"],
            consumes = [MediaType.APPLICATION_JSON_VALUE],
            produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus (HttpStatus.ACCEPTED)
    @ResponseBody
    fun login(@RequestBody userLogin: LoginUserDTO) = userService.login(userLogin);

    @GetMapping(path= ["/account/{userId}"])
    @ResponseBody
    fun getUserAccount(@PathVariable userId: String): UserDataDTO {
       return userService.getUserData(userId.toLong())
    }

    @GetMapping(path=["/account"])
    @ResponseBody
    fun getUserAccountId(@RequestParam userId: Long): Long {
        return userService.getUserAccountId(userId)
    }
}