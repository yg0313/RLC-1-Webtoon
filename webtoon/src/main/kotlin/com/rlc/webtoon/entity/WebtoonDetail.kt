package com.rlc.webtoon.entity

import com.rlc.webtoon.entity.common.BaseTimeEntity
import jakarta.persistence.*
import java.time.Instant
import java.util.*

@Entity
@Table(name = "tb_webtoon_detail")
class WebtoonDetail(
    val episode: String, //에피소드명 ex)1화, 2화
    @Lob
    val content: String, //웹툰 파일이라면 어떤 타입이 맞을까?
    @Enumerated(EnumType.STRING)
    val openType: OpenType,
    var fee: Int = 0,

    val displayDate: Instant, //웹툰 공개일자.

    @ManyToOne
    val webtoon: Webtoon,

    @Id
    val uuid: String = UUID.randomUUID().toString(),
): BaseTimeEntity() {
    init {
        if (openType == OpenType.LOCK) {
            fee = 500
        }
    }
}

enum class OpenType(){
    OPEN, LOCK
}