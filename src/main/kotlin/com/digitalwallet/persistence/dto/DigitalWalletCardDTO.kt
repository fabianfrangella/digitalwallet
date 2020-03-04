package com.digitalwallet.persistence.dto

import com.digitalwallet.persistence.entity.DigitalWalletCard

class DigitalWalletCardDTO(
        var card_id: Long? = null,
        var user_id: Long? = null,
        var cardNumber: Long? = null,
        var securityNumber: Long? = null,
        var amountLimit: Long? = null,
        var firstname: String? = null,
        var lastname: String? = null,
        var dueDate: String? = null
        ) {

    constructor(card: DigitalWalletCard) : this() {
        card_id = card.card_id
        user_id = card.user_id!!.user_id
        cardNumber = card.cardNumber
        securityNumber = card.securityNumber
        amountLimit = card.amountLimit
        firstname = card!!.user_id!!.firstname
        lastname = card!!.user_id!!.lastname
        dueDate = card.dueDate!!.monthValue.toString() + "/" + card.dueDate!!.year.toString()
    }

    override fun toString() : String = "card_id: $card_id user_id: $user_id cardNumber: $cardNumber"
}
