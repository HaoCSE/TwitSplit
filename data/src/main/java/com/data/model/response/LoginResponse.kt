package com.data.model.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(var data: AccessToken) : BaseResponse() {
    class AccessToken {
        @SerializedName("access_token")
        var accessToken = ""
    }
}