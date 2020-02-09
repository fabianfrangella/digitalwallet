package persistence.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="transactions")
data class Transaction(@Id val id: Long, val accountTo: Account, val accountFrom: Account, val amount: Long)