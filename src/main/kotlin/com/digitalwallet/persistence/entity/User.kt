package com.digitalwallet.persistence.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="app_user")
data class User(@Id val user_id: Long, val username: String, val email: String,
                val cvu: Long, val idCard: Long, val password: String)