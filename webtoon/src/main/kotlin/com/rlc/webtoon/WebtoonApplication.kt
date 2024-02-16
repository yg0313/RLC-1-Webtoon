package com.rlc.webtoon

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebtoonApplication

fun main(args: Array<String>) {
	runApplication<WebtoonApplication>(*args)
}