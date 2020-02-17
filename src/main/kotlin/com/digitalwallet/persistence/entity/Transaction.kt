package com.digitalwallet.persistence.entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "transaction")
class Transaction(@Id @GeneratedValue val transaction_id: Long? = null,
				  @Column
				  var amount: Long? = null,
				  @Column
				  var accountFrom: Long? = null,
				  @Column
				  var accountTo: Long? = null,
				  @Column
				  var date: Date? = null,
				  @Column
				  var description: String? = null
)

