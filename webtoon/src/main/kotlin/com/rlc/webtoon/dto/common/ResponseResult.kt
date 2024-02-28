package com.rlc.webtoon.dto.common

import com.rlc.webtoon.util.jsonMapper

/**
 * API 응답 포맷.
 * 패키지 위치 고려해볼것.
 */
data class ResponseResult<T>(
    val code: String,
    val result: T
) {
    constructor(result: T): this("200", result) //클라이언트 요청 성공한경우 기본 포맷

    override fun toString(): String {
        return jsonMapper.writeValueAsString(this)
    }

}