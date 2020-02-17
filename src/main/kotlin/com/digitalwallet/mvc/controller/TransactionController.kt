package com.digitalwallet.mvc.controller


import com.digitalwallet.persistence.dto.TransactionDTO
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.beans.factory.annotation.Autowired
import com.digitalwallet.service.service.TransactionService




@Controller
@CrossOrigin(origins= ["http://localhost:3000"])
@RequestMapping("/transaction")
class TransactionController {
	
	@Autowired
	private lateinit var transactionService: TransactionService
	
	@PostMapping(path = ["/transfer"],
            consumes = [MediaType.APPLICATION_JSON_VALUE],
            produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
	fun transfer(@RequestBody transactionDTO: TransactionDTO) {
		transactionService.transfer(transactionDTO)
	}
}