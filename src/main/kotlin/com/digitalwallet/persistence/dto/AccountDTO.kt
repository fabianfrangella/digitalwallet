package com.digitalwallet.persistence.dto

import com.digitalwallet.persistence.entity.User

class AccountDTO(val id: Long, val balance: Long, val user: User, var isBlocked: Boolean)