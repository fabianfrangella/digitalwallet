package com.digitalwallet.service.service

import com.digitalwallet.persistence.dto.DigitalWalletCardDTO
import com.digitalwallet.persistence.entity.DigitalWalletCard
import com.digitalwallet.service.repository.DigitalWalletCardRepository
import com.digitalwallet.service.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class DigitalWalletCardService {

    @Autowired
    private lateinit var digitalWalletCardRepository: DigitalWalletCardRepository
    @Autowired
    private lateinit var userRepository : UserRepository

    fun createCard(userId: Long) {
        var user = userRepository.findByIdOrNull(userId)
        var newCard = DigitalWalletCard()
        newCard.user_id = user
        newCard.amountLimit = 30000
        if (digitalWalletCardRepository.findAll().isNullOrEmpty()){
            newCard.cardNumber = 4256545418821110
        } else {
            newCard.cardNumber = digitalWalletCardRepository.findLastCardNumber() + 1
        }
        newCard.securityNumber = (100..999).random().toLong()
        newCard.dueDate = LocalDate.of(LocalDate.now().year + 5,LocalDate.now().month,LocalDate.now().dayOfMonth)
        digitalWalletCardRepository.save(newCard)
    }

    fun getCards(userId: Long): List<DigitalWalletCardDTO> {
        var user = userRepository.findByIdOrNull(userId)
        var cards = digitalWalletCardRepository.findByUserId(user!!)
        return cards.map { card -> DigitalWalletCardDTO(card)}
    }
}