package com.data.model.request

import com.google.gson.annotations.SerializedName

class SocialLoginRequest {
    var type = ""
    var email = ""
    @SerializedName("display_name")
    var displayName = ""
    @SerializedName("country_id")
    var countryId = ""
    @SerializedName("social_id")
    var socialId = ""
    @SerializedName("social_username")
    var socialUsername = ""
    var token = ""
}