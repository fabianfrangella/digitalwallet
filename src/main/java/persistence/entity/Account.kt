package persistence.entity

import javax.persistence.*

@Entity
@Table(name="accounts")
data class Account(@Id val id: Long,
                   val balance: Long,
                   @JoinColumn(name = "id") val user: User,
                   var isBlocked: Boolean)