package com.rlc.webtoon.repostiory

import com.rlc.webtoon.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, String> {

    fun findByLoginId(id: String): User?

    fun findByUuidAndRefreshToken(uuid: String, refreshToken: String): User?
}