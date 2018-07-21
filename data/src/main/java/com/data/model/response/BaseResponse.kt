package com.data.model.response

abstract class BaseResponse {
    companion object {
        const val STATUS_SUCCESS = 0
        const val STATUS_FAILED = 1
    }

    var status: Int = 0
    var code: Int = 0
    var message: String = ""
}