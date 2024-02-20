package com.rlc.webtoon.exception

sealed class RlcException: RuntimeException()

/**
 *  사용자 입력 오류 처리 익셉션.
 */
data class RlcClientException(
    val errorMessage: String,
    val errorCode: String = "400",
    val throwable: Throwable? = null): RlcException() {
}

/**
 * 서버 오류 처리 익셉션.
 */
data class RlcServerException(
    val errorMessage: String,
    val errorCode: String = "500",
    val throwable: Throwable? = null): RlcException() {
}
