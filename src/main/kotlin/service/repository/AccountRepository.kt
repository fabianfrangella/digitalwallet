package service.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import persistence.entity.Account

@Repository
interface AccountRepository : JpaRepository<Account, Long> {
}