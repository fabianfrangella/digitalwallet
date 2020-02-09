package service.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import persistence.entity.Transaction

@Repository
interface TransactionRepository: JpaRepository<Transaction, Long> {
}