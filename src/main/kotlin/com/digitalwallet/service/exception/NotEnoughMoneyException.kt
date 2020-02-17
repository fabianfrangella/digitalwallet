package com.digitalwallet.service.exception

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "You don't have enough money")
class NotEnoughMoneyException(override val message: String?) : TransferException(message){
}