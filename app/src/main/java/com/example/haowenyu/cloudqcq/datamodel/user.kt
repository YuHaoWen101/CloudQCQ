package com.example.haowenyu.cloudqcq.datamodel

import android.os.Parcelable


data class user(
    val adValid: Boolean,
    val bindings: List<Binding>,
    val code: Int,
    val createDays: Int,
    val createTime: Long,
    val level: Int,
    val listenSongs: Int,
    val mobileSign: Boolean,
    val pcSign: Boolean,
    val peopleCanSeeMyPlayRecord: Boolean,
    val profile: Profile,
    val userPoint: UserPoint
)

data class Binding(
    val bindingTime: Long,
    val expired: Boolean,
    val expiresIn: Int,
    val id: Long,
    val refreshTime: Int,
    val tokenJsonStr: Any,
    val type: Int,
    val url: String,
    val userId: Int
)

data class UserPoint(
    val balance: Int,
    val blockBalance: Int,
    val status: Int,
    val updateTime: Long,
    val userId: Int,
    val version: Int
)

data class Profile(
    val accountStatus: Int,
    val allSubscribedCount: Int,
    val artistIdentity: List<Any>,
    val authStatus: Int,
    val authority: Int,
    val avatarImgId: Long,
    val avatarImgIdStr: String,
    val avatarImgId_str: String,
    val avatarUrl: String,
    val backgroundImgId: Long,
    val backgroundImgIdStr: String,
    val backgroundUrl: String,
    val birthday: String,
    val blacklist: Boolean,
    val cCount: Int,
    val city: Int,
    val defaultAvatar: Boolean,
    val description: String,
    val detailDescription: String,
    val djStatus: Int,
    val eventCount: Int,
    val expertTags: Any,
    val experts: Experts,
    val followed: Boolean,
    val followeds: Int,
    val follows: Int,
    val gender: Int,
    val mutual: Boolean,
    val nickname: String,
    val playlistBeSubscribedCount: Int,
    val playlistCount: Int,
    val province: Int,
    val remarkName: Any,
    val sCount: Int,
    val sDJPCount: Int,
    val signature: String,
    val userId: Int,
    val userType: Int,
    val vipType: Int
)

class Experts(
)