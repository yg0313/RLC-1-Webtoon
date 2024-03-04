package com.rlc.webtoon.service

import com.rlc.webtoon.dto.response.PortoneResponse
import com.rlc.webtoon.exception.RlcServerException
import com.rlc.webtoon.feign.PortoneFeignClient
import com.rlc.webtoon.service.inter.PortoneInterface
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile("!test")
class PortoneService(
    private val portoneFeignClient: PortoneFeignClient
): PortoneInterface {

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun getAccessToken(impKey: String, impSecret: String): PortoneResponse {
        val portoneResponse: PortoneResponse = portoneFeignClient.getAccessToken(impKey, impSecret)

        logger.info("getAccessToken(), portoneResponse:$portoneResponse")

        if(portoneResponse.equals("0") ) {
            throw RlcServerException(errorMessage = portoneResponse.message.toString(), errorCode = portoneResponse.code.toString())
        }

        return portoneResponse
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
        val portoneResponse: PortoneResponse = portoneFeignClient.payment(accessToken, merchantUid, price, cardNumber, expiry, birth, cardPassword, productName)

        logger.info("payment(), portoneResponse:$portoneResponse")

        if(portoneResponse.code != 0) {
            throw RlcServerException(errorMessage = portoneResponse.message.toString(), errorCode = portoneResponse.code.toString())
        }

        return portoneResponse
    }

    override fun cancel(impUid: String, merchantUid: String, price: Int): PortoneResponse {
        val portoneResponse: PortoneResponse = portoneFeignClient.cancel(impUid, merchantUid, price)

        logger.info("cancel(), portoneResponse:$portoneResponse")

        if(portoneResponse.code != 0) {
            throw RlcServerException(errorMessage = portoneResponse.message.toString(), errorCode = portoneResponse.code.toString())
        }

        return portoneResponse
    }
}