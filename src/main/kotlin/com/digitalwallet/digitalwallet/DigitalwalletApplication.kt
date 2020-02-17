package com.digitalwallet.digitalwallet

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@ComponentScan(basePackages = ["com.digitalwallet"])
@EntityScan(basePackages = ["com.digitalwallet" ])
@EnableJpaRepositories(basePackages = ["com.digitalwallet"])
@SpringBootApplication(scanBasePackages = ["com.digitalwallet"])
open class DigitalwalletApplication

fun main(args: Array<String>) {
	runApplication<DigitalwalletApplication>(*args)
}
