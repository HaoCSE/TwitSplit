package com.data.model.request

import com.google.gson.annotations.SerializedName

class SignUpRequest {
    @SerializedName("first_name")
    var firstName = ""
    @SerializedName("last_name")
    var lastName = ""
    @SerializedName("country_id")
    var countryId = 0
    var email = ""
    var password = ""
}