package com.digitalwallet.persistence.entity

import javax.persistence.*

@Entity
@Table(name="account")
data class Account(@Id @GeneratedValue val account_id: Long? = null,
                   var balance: Long? = null,
                   @OneToOne
                   @JoinColumn(name = "user_id")
                   var user_id: User? = null,
                   @Column
                   var isBlocked: Boolean? = null,
                   @OneToMany(mappedBy = "accountFrom")
                   var cashOutTransactions: List<Transaction>? = null,
                   @OneToMany(mappedBy = "accountTo")
                   var cashInTransactions: List<Transaction>? = null
                   )

