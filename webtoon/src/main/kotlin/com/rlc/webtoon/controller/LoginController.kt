package com.rlc.webtoon.controller

import com.rlc.webtoon.dto.common.ResponseResult
import com.rlc.webtoon.dto.response.JwtDto
import com.rlc.webtoon.service.LoginService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/**
 * 로그인 관련 API 처리
 * JWT 적용
 */
@RestController
@RequestMapping("/rlc")
class LoginController(
    private val loginService: LoginService
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    /**
     * 사용자 로그인.
     * @param id 사용자 아이디
     * @param password 사용자 비밀번호
     *
     * TODO id, password Base64 방식 인코딩하여 헤더값으로 받기.
     * ref: https://velog.io/@tosspayments/Basic-%EC%9D%B8%EC%A6%9D%EA%B3%BC-Bearer-%EC%9D%B8%EC%A6%9D%EC%9D%98-%EB%AA%A8%EB%93%A0-%EA%B2%83
     */
    @PostMapping("/login")
    fun login(id: String, password: String): ResponseResult<JwtDto> {
        log.info("login() id:$id")
        return ResponseResult(loginService.login(id, password))
    }

    /**
     * 사용자 토큰 재발급.
     * accessToken 재발급과 함께 기존의  refreshToken 갱신된다.
     * @param uuid 사용자 고유 아이디
     * @param refreshToken 리프레쉬토큰
     */
    @PostMapping("/refresh")
    fun refreshToken(uuid: String, @RequestHeader("refreshToken") refreshToken: String): ResponseResult<JwtDto> {
        log.info("refreshToken() uuid:$uuid, refreshToken:$refreshToken")
        return ResponseResult(loginService.refreshToken(uuid, refreshToken))
    }
}

