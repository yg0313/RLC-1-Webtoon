package com.rlc.webtoon.dto.response

import com.rlc.webtoon.entity.User
import java.time.Instant

data class UserResponseDto constructor(
    val uuid: String,
    val id: String,
    val email: String,
    val phoneNumber: String,
    val birth: String,
    val createdDate: Instant
) {

    companion object {
        fun of(user: User): UserResponseDto {
            return UserResponseDto(
                uuid = user.uuid,
                id = user.loginId,
                email = user.email,
                phoneNumber = user.phoneNumber,
                birth = user.birth,
                createdDate = user.updatedDate
            )
        }
    }
}