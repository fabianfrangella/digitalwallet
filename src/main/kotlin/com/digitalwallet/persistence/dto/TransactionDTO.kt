package com.digitalwallet.persistence.dto

import java.util.*

class TransactionDTO {
	var accountFrom: Long? = null
	var accountTo: Long? = null
	var amount: Long? = null
	var date: Date? = Date()
}