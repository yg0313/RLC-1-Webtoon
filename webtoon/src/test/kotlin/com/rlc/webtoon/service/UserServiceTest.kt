package com.rlc.webtoon.service

import com.rlc.webtoon.dto.request.UserRequestDto
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest @Autowired constructor(
    val userService: UserService,
) {

    @Test
    @DisplayName("사용자가 정상 등록된다.")
    fun signupUserTest(){
        val userRequestDto = UserRequestDto.fixture()
        val savedUser = userService.signupUser(userRequestDto)

        Assertions.assertThat(savedUser.id).isEqualTo("test@test.com")
    }
}