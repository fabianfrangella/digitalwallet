package com.digitalwallet.mvc.controller

import com.digitalwallet.persistence.dto.UserDTO
import com.digitalwallet.persistence.dto.UserRegisterDTO
import com.digitalwallet.service.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody


@Controller
class UserController {
    @Autowired
    private lateinit var userService: UserService;

    @PostMapping("/register", consumes = ["application/json"], produces = ["application/json"])
    @ResponseBody
    fun register(@RequestBody user: UserRegisterDTO) : UserRegisterDTO {
        userService.register(user)
        return user;
    }
}