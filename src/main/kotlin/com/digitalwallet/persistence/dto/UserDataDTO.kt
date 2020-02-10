package com.digitalwallet.persistence.dto

data class UserDataDTO(var user_id: Long? = null, var username: String? = null, var email: String? = null,
                       var cvu: Long? = null, var idCard: Long? = null, var balance: Long? = null) {

}