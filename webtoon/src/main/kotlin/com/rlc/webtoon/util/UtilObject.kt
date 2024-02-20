package com.rlc.webtoon.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

val jsonMapper = ObjectMapper().registerKotlinModule() //jacksonMapper