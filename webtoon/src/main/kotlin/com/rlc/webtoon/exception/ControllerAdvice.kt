package com.rlc.webtoon.exception

import com.rlc.webtoon.dto.common.ResponseResult
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerAdvice {

    private val log = LoggerFactory.getLogger(this::class.java)

    /**
     * 클라이언트 입력 오류 익셉션 핸들링.
     * 디테일한 에러코드는 정의하지 않고 사용자의 입력 오류면 모두 "400" 처리
     * @return HttpStatusCode = 400
     */
    @ExceptionHandler(RlcClientException::class)
    fun rlcClientException(e: RlcClientException): ResponseEntity<ResponseResult<String?>> {
        log.error("rlcClientException(), errorCode:${e.errorCode}, errorMessage:${e.errorMessage}", e.throwable)

        return ResponseEntity.badRequest()
            .body(
                ResponseResult(
                    code = e.errorCode,
                    result = e.errorMessage
                )
            )
    }

    /**
     * 서버 오류 익셉션 핸들링
     * 디테일한 에러코드는 정의하지 않고 사용자의 입력 오류면 모두 "500" 처리
     * @return HttpStatusCode = 500
     */
    @ExceptionHandler(RlcServerException::class)
    fun rlcServerException(e: RlcServerException): ResponseEntity<ResponseResult<String?>> {
        log.error("rlcServerException(), errorCode:${e.errorCode}, errorMessage:${e.errorMessage}", e.throwable)

        return ResponseEntity.internalServerError()
            .body(
                ResponseResult(
                    code = e.errorCode,
                    result = e.errorMessage
                )
            )
    }

    /**
     * RlcException() 이외의 익셉션 핸들링.
     */
    @ExceptionHandler(Exception::class)
    fun exceptionHandler(e: Exception): ResponseEntity<ResponseResult<String?>> {
        log.error("exceptionHandler() message:${e.message}", e)

        return ResponseEntity.internalServerError()
            .body(
                ResponseResult(
                    code = "500",
                    result = e.message
                )
            )
    }
}