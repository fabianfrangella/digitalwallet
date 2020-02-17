package com.digitalwallet.mvc.controller


import com.digitalwallet.persistence.dto.TransactionDTO
import com.digitalwallet.persistence.entity.Transaction
import com.digitalwallet.service.exception.TransferException
import com.digitalwallet.service.service.AccountService
import com.digitalwallet.service.service.TransactionService
import org.hibernate.TransactionException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import javax.transaction.Transactional

@Controller
@CrossOrigin(origins= ["http://localhost:3000"])
@RequestMapping("/transaction")
class TransactionController {
	
	@Autowired
	private lateinit var transactionService: TransactionService
	
	@Autowired
	private lateinit var accountService: AccountService
	
	@PostMapping(path = ["/transfer"],
            consumes = [MediaType.APPLICATION_JSON_VALUE],
            produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
	@Transactional
	@ResponseStatus(value = HttpStatus.ACCEPTED, reason = "Transaction successful!")
	fun transfer(@RequestBody transactionDTO: TransactionDTO) {
		try {
			transactionService.transfer(transactionDTO)
		} catch(ex: TransferException) {
			throw ex
		}
		accountService.updateBalance(transactionDTO)
	}

	@GetMapping(path = ["/transaction-list"])
	@ResponseBody
	fun getTransactions(@RequestParam accountId: Long): List<Transaction> {
		return transactionService.getTransactions(accountId)
	}
}