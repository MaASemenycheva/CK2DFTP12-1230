package com.cbr.ilk.camundakotlin

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@EnableProcessApplication
@SpringBootApplication
class ProcessApplication

fun main(args: Array<String>) {
    SpringApplication.run(ProcessApplication::class.java)
}

