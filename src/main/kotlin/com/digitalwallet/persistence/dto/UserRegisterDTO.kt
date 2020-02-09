package com.digitalwallet.persistence.dto

data class UserRegisterDTO(val username: String,val email: String,
                      val idCard: Long, val password: String) {
}