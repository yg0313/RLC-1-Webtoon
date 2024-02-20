package com.rlc.webtoon.controller

import com.rlc.webtoon.dto.common.ResponseResult
import com.rlc.webtoon.dto.request.UserRequestDto
import com.rlc.webtoon.dto.response.UserResponseDto
import com.rlc.webtoon.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rlc/user")
class UserController(
    private val userService: UserService
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    /**
     * 사용자 등록.
     */
    @PostMapping("/signup")
    fun signupUser(@RequestBody userRequestDto: UserRequestDto): ResponseResult<UserResponseDto> {
        log.info("signupUser(), userRequestDto:$userRequestDto")

        return ResponseResult(userService.signupUser(userRequestDto))
    }
}