package com.rlc.webtoon.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

import jakarta.servlet.http.HttpServletRequest

val jsonMapper = ObjectMapper().registerKotlinModule() //jacksonMapper

const val accessToken = "accessToken"
const val refreshToken = "refreshToken"

const val importAccessToken = "access_token"
fun HttpServletRequest.userAccessToken(): String {
    return this.getHeader("Authorization")
}

fun HttpServletRequest.importAccessToken(): String {
    return this.getHeader(importAccessToken)
}