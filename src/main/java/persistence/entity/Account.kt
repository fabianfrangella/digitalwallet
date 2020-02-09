package persistence.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="accounts")
data class Account(@Id val id: Long, val balance: Long, val user: User, var isBlocked: Boolean)