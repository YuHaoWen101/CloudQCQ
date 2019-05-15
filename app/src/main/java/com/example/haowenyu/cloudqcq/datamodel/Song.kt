package com.example.haowenyu.cloudqcq.datamodel

data class Song(
    val code: Int,
    val `data`: List<Data>
)

data class Data(
    val br: Int,
    val canExtend: Boolean,
    val code: Int,
    val encodeType: String,
    val expi: Int,
    val fee: Int,
    val flag: Int,
    val freeTrialInfo: Any,
    val gain: Double,
    val id: String,
    val level: String,
    val md5: String,
    val payed: Int,
    val size: Int,
    val type: String,
    val uf: Any,
    val url: String
)