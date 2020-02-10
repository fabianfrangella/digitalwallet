package com.digitalwallet.persistence.entity

import javax.persistence.*

@Entity
@Table(name="account")
data class Account(@Id @GeneratedValue val account_id: Long? = null,
                   var balance: Long? = null,
                   var user_id: Long? = null,
                   var isBlocked: Boolean? = null)

