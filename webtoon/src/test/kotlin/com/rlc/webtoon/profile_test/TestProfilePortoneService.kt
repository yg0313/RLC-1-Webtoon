package com.rlc.webtoon.profile_test

import com.rlc.webtoon.dto.response.PortoneResponse
import com.rlc.webtoon.service.inter.PortoneInterface
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("test")
class TestProfilePortoneService: PortoneInterface {

    override fun getAccessToken(impKey: String, impSecret: String): PortoneResponse {
        return PortoneResponse.successResponseFixture()
    }

    override fun payment(
        accessToken: String,
        merchantUid: String,
        price: Int,
        cardNumber: String,
        expiry: String,
        birth: String,
        cardPassword: String,
        productName: String
    ): PortoneResponse {
        return PortoneResponse.successResponseFixture()
    }
}