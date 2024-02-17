package com.rlc.webtoon.repostiory

import com.rlc.webtoon.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, String> {

}