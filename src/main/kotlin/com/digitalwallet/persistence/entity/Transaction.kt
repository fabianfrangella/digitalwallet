package com.digitalwallet.persistence.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "transaction")
class Transaction(@Id @GeneratedValue val transaction_id: Long? = null,
				  @Column
				  var amount: Long? = null,
				  @JsonIgnore
				  @ManyToOne
				  @JoinColumn(name = "accountFrom")
				  var accountFrom: Account? = null,
				  @JsonIgnore
				  @ManyToOne
				  @JoinColumn(name = "accountTo")
				  var accountTo: Account? = null,
				  @Column
				  var date: Date? = null,
				  @Column
				  var description: String? = null
)

