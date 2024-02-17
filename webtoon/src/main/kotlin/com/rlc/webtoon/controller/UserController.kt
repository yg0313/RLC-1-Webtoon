package com.rlc.webtoon.controller

import com.rlc.webtoon.dto.request.UserRequestDto
import com.rlc.webtoon.dto.response.UserResponseDto
import com.rlc.webtoon.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rlc/user")
class UserController(
    private val userService: UserService
) {

    /**
     * 사용자 등록.
     * TODO 응답 포맷 설정, 테스트 코드
     */
    @PostMapping
    fun signupUser(@RequestBody userRequestDto: UserRequestDto): UserResponseDto {
        return userService.signupUser(userRequestDto)
    }
}