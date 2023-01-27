package com.mvoleg.hseminorcarserviceback

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.CrossOrigin

@SpringBootApplication
@CrossOrigin("http://localhost:3000")
class HseMinorCarServiceBackApplication

fun main(args: Array<String>) {
    runApplication<HseMinorCarServiceBackApplication>(*args)
}
