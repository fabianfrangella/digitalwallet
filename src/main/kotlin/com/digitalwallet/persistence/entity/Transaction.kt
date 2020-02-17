package com.digitalwallet.persistence.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "transaction")
class Transaction(@Id @GeneratedValue val transaction_id: Long? = null,
				  var amount: Long? = null,
				  var accountFrom: Long? = null,
				  var accountTo: Long? = null
)

