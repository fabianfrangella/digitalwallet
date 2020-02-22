package com.digitalwallet.persistence.dto

import com.digitalwallet.persistence.entity.DigitalWalletCard
import com.digitalwallet.persistence.entity.User

class DigitalWalletCardDTO(
        var card_id: Long? = null,
        var user_id: Long? = null,
        var cardNumber: Long? = null,
        var securityNumber: Long? = null,
        var amountLimit: Long? = null) {

    constructor(card: DigitalWalletCard) : this() {
        card_id = card.card_id
        user_id = card.user_id!!.user_id
        cardNumber = card.cardNumber
        securityNumber = card.securityNumber
        amountLimit = card.amountLimit
    }
}
