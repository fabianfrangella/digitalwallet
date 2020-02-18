package com.digitalwallet.persistence.dto

import java.util.*

class TransactionDTO {
	var accountFrom: Long? = null
	var cvuTo: Long? = null
	var amount: Long? = null
	var date: Date? = Date()
	var description: String? = ""
	var isCashIn: Boolean? = null
}