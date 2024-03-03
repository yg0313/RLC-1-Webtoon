package com.rlc.webtoon.feign

import com.rlc.webtoon.dto.response.PortoneResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestPart

@FeignClient(
    name = "portone",
    contextId = "portoneFeign",
    url = "https://api.iamport.kr/"
)
interface PortoneFeignClient {

    @PostMapping("/users/getToken")
    fun getAccessToken(@RequestPart("imp_key")impKey: String, @RequestPart("imp_secret")impSecret: String): PortoneResponse

    @PostMapping("/subscribe/payments/onetime")
    fun payment(
        @RequestHeader("Authorization")accessToken: String,
        @RequestPart("merchant_uid")merchantUid: String,
        @RequestPart("amount")price: Int,
        @RequestPart("card_number")cardNumber: String,
        @RequestPart("expiry")expiry: String,
        @RequestPart("birth")birth: String,
        @RequestPart("pwd_2digit")cardPassword: String,
        @RequestPart("name")productName: String,
    ): PortoneResponse

}