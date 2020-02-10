package com.digitalwallet.persistence.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="app_user")
class User(
        @Id @GeneratedValue var user_id: Long? = null,
        var username: String? = null,
        var email: String? = null,
        var cvu: Long? = null,
        var idCard: Long? = null,
        var password: String? = null)