package com.digitalwallet.persistence.dto

import com.digitalwallet.persistence.entity.Transaction
import java.util.*

class TransactionDTO(
	var accountFrom: Long? = null,
	var cvuTo: Long? = null,
	var amount: Long? = null,
	var date: Date? = Date(),
	var description: String? = "",
	var isCashIn: Boolean? = null) {

	constructor(transaction: Transaction, cashIn: Boolean) : this() {
		accountFrom = transaction.accountFrom!!.account_id
		cvuTo = transaction.accountTo!!.user_id!!.cvu
		amount = transaction.amount
		date = transaction.date
		description = transaction.description
		isCashIn = cashIn
	}

	override fun toString(): String {
		return "accountFrom: $accountFrom cvuTo: $cvuTo amount: $amount date: $date description: $description isCashIn: $isCashIn"
	}
}