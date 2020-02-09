package com.digitalwallet.persistence.dto

data class UserDTO(val id: Long, val username: String, val email: String,
                   val cvu: Long, val idCard: Long, val password: String) {
}