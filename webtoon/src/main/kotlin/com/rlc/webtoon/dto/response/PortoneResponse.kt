package com.rlc.webtoon.dto.response

data class PortoneResponse(
    val code: Int,
    val message: String?,
    val response: Map<Any, Any>? = HashMap()
){

    companion object {
        fun successResponseFixture(): PortoneResponse {
            return PortoneResponse(
                code = 0,
                message = null,
                response = emptyMap()
            )
        }
    }
}
