package com.digitalwallet.mvc.controller

import com.digitalwallet.service.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class UserController {
    @Autowired
    private lateinit var userService: UserService;
}