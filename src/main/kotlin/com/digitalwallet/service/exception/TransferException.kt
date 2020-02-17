package com.digitalwallet.service.exception


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "The CVU doesn't exist")
open class TransferException(override val message: String?) : Exception() {
}