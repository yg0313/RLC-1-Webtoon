package com.rlc.webtoon.service

import com.rlc.webtoon.dto.request.UserRequestDto
import com.rlc.webtoon.dto.response.UserResponseDto
import com.rlc.webtoon.entity.User
import com.rlc.webtoon.repostiory.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserService(
    private val userRepository: UserRepository
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    /**
     * 사용자 등록.
     * TODO 계정 중복 검사
     */
    @Transactional
    fun signupUser(userRequestDto: UserRequestDto): UserResponseDto {
        val user = User.of(userRequestDto).apply {
            this.password = BCryptPasswordEncoder().encode(this.password)
        }

        log.info("signupUser(), user:$user")

        return UserResponseDto.of(userRepository.save(user))
    }
}