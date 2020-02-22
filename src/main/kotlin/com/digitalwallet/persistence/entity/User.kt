package com.digitalwallet.persistence.entity

import javax.persistence.*

@Entity
@Table(name = "app_user")
class User(
        @Id @GeneratedValue
        var user_id: Long? = null,
        @OneToOne(mappedBy = "user_id")
        var account: Account? = null,
        @OneToMany(mappedBy = "user_id")
        var cards: List<DigitalWalletCard>? = null,
        @Column(name = "username")
        var username: String? = null,
        @Column(name = "email")
        var email: String? = null,
        @Column(name = "cvu")
        var cvu: Long? = null,
        @Column(name = "idCard")
        var idCard: Long? = null,
        @Column(name = "password")
        var password: String? = null)