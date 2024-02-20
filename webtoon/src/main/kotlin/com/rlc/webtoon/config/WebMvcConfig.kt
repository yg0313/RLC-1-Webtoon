package com.rlc.webtoon.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig(
    private val webInterceptor: WebInterceptor
): WebMvcConfigurer {

    //accessToken 필요하지 않은 api 목록
    private val excludePathList = listOf(
        "/rlc/user/signup",
        "/rlc/login", "/rlc/refresh"
    )

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(webInterceptor)
            .excludePathPatterns(excludePathList)
    }


}