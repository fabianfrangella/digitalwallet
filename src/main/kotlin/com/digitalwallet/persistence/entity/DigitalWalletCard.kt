package com.digitalwallet.persistence.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity
@Table(name="card")
class DigitalWalletCard (@Id @GeneratedValue
                         var card_id: Long? = null,
                         @ManyToOne
                         @JoinColumn(name = "user_id")
                         @JsonIgnore
                         var user_id: User? = null,
                         @Column(name = "card_number")
                         var cardNumber: Long? = null,
                         @Column(name = "security_number")
                         var securityNumber: Long? = null,
                         @Column(name = "amount_limit")
                         var amountLimit: Long? = null,
                         @Column(name = "due_date")
                         var dueDate: LocalDate? = LocalDate.now()
                         )