package com.rlc.webtoon.dto.request

import com.rlc.webtoon.entity.User

//TODO 유효성 검증
data class UserRequestDto constructor(
    val id: String,
    val password: String,
    val email: String,
    val phoneNumber: String,
    val birth: String,
){

    fun toEntity(): User {
        return User(
            loginId = id,
            password = password,
            email = email,
            phoneNumber = phoneNumber,
            birth = birth
        )
    }

    companion object {
        fun fixture(
            id: String = "test@test.com",
            password: String = "password",
            email: String = "test@test.com",
            phoneNumber: String = "01012345678"
        ): UserRequestDto {
            return UserRequestDto(
                id = id,
                password = password,
                email = email,
                phoneNumber = phoneNumber,
                birth = "00000101"
            )
        }
    }

    override fun toString(): String {
        return "UserRequestDto(id='$id', email='$email', phoneNumber='$phoneNumber', birth='$birth')"
    }
}
