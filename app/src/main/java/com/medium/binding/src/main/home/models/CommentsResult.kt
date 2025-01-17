package com.medium.binding.src.main.home.models

import com.google.gson.annotations.SerializedName

data class CommentsResult (

    // NewestWRResult[0] = {"bookName": 책이름}
    @SerializedName("bookName") var bookName: String? = null,

    @SerializedName("contentsIdx") val contentsIdx: Int?,
    @SerializedName("userIdx") val userIdx: Int?,
    @SerializedName("userImgUrl") val userImgUrl: String?,
    @SerializedName("nickname") val nickname: String?,
    @SerializedName("contents") val contents: String?,
    @SerializedName("createdAt") val createdAt: String?,
    @SerializedName("isBookMark") var isBookMark: Int?
)