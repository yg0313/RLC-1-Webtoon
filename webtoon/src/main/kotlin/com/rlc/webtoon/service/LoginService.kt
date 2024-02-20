package com.rlc.webtoon.service

import com.rlc.webtoon.dto.response.JwtDto
import com.rlc.webtoon.entity.User
import com.rlc.webtoon.exception.RlcClientException
import com.rlc.webtoon.repostiory.UserRepository
import com.rlc.webtoon.util.JwtUtil
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LoginService(
    private val jwtUtil: JwtUtil,
    private val userRepository: UserRepository
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    /**
     * 유저 로그인.
     * Jwt 생성 후, refreshToken DB 따로 저장.
     * @return JwtDto
     */
    @Transactional
    fun login(id: String, password: String): JwtDto {
        val user = userRepository.findByLoginId(id) ?: throw RlcClientException("사용자를 찾을 수 없습니다. id:$id")

        if (!BCryptPasswordEncoder().matches(password, user.password)) {
            throw RlcClientException("패스워드가 일치하지 않음")
        }

        val jwt = user.createJwt()

        log.info("login(), user:$user")

        return JwtDto.of(id, jwt["accessToken"]!!, jwt["refreshToken"]!!)
    }

    @Transactional
    fun refreshToken(uuid: String, refreshToken: String): JwtDto {
        log.info("refreshToken() uuid:$uuid, refreshToken:$refreshToken")
        val user = userRepository.findByUuidAndRefreshToken(uuid, refreshToken)  ?: throw RlcClientException("사용자를 찾을 수 없습니다. uuid:$uuid")

        if(!jwtUtil.isValidRefreshToken(refreshToken)){
            throw RlcClientException(
                errorCode = "403",
                errorMessage =  "refreshToken이 유효하지 않습니다.")
        }
        val jwt = user.createJwt()

        log.info("refreshToken(), user:$user")

        return JwtDto.of(user.loginId, jwt["accessToken"]!!, jwt["refreshToken"]!!)
    }

    /**
     * 사용자 Jwt 생성.
     */
    private fun User.createJwt(): Map<String, String> {
        val jwt = jwtUtil.createJwt(this.uuid!!)
        this.refreshToken = jwt["refreshToken"] // refreshToken 새로 저장.

        return jwt
    }

}