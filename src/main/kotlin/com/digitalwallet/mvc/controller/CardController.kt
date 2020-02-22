package com.digitalwallet.mvc.controller

import com.digitalwallet.persistence.dto.DigitalWalletCardDTO
import com.digitalwallet.persistence.entity.DigitalWalletCard
import com.digitalwallet.service.service.DigitalWalletCardService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@CrossOrigin(origins= ["http://localhost:3000"])
@RequestMapping("/card")
class CardController {

    @Autowired
    private lateinit var digitalWalletCardService : DigitalWalletCardService

    @PostMapping(path = ["/create-card"],
            produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    fun createCard(@RequestParam userId: Long){
        digitalWalletCardService.createCard(userId)
    }

    @GetMapping(path=["/user-cards"],
                produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    fun getCards(@RequestParam userId: Long): List<DigitalWalletCardDTO> {
        return digitalWalletCardService.getCards(userId)
    }
}