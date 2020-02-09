package com.digitalwallet.persistence.entity

import javax.persistence.*

@Entity
@Table(name="account")
data class Account(@Id val account_id: Long,
                   val balance: Long,
                   @JoinColumn(name = "user_id") val user: User,
                   var isBlocked: Boolean)