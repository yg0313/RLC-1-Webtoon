package com.rlc.webtoon.dto.response

/**
 * JWT 정보.
 */
data class JwtDto constructor(
    val userId: String, //로그인 시도한 사용자 아이디
    val accessToken: String,
    val refreshToken: String
) {

    companion object {
        fun of(
            userId: String,
            accessToken: String,
            refreshToken: String
        ): JwtDto {
            return JwtDto(
                userId = userId,
                accessToken = accessToken,
                refreshToken = refreshToken
            )
        }
    }

}