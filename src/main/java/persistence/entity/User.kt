package persistence.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="users")
data class User(@Id val id: Long, val username: String, val email: String,
                val cvu: Long, val idCard: Long, val password: String)