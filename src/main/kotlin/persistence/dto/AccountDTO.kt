package persistence.dto

import persistence.entity.User

class AccountDTO(val id: Long, val balance: Long, val user: User, var isBlocked: Boolean)