package com.medium.binding.src.main.my_page.settings.models

import com.medium.binding.config.BaseResponse
import com.google.gson.annotations.SerializedName
import com.medium.binding.src.main.my_page.models.InfoData

// 레트로핏 getUser의 response 데이터 클래스
data class GetProfileResponse (
    @SerializedName("result") val result: ArrayList<InfoData>
): BaseResponse()