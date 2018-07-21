package com.data.model.response

data class SignUpResponse(var data: User) : BaseResponse() {
    class User {
        var id: Int = -1
        var userLogin = ""
        var firstName = ""
        var lastName = ""
        var email = ""
        var userGroup = ""
    }
}