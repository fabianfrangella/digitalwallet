package com.digitalwallet.service.exception


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "You can't transfer to yourself")
class TransferToYourselfException(override val message: String?) : TransferException(message) {
}