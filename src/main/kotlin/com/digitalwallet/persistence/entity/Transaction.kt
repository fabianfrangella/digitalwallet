package com.digitalwallet.persistence.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.Table

@Entity
@Table(name="transactions")
data class Transaction(@Id val transaction_id: Long,
                       @JoinColumn(name="id")
                       val accountTo: Account,
                       @JoinColumn(name="id")
                       val accountFrom: Account,
                       val amount: Long)