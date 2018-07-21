package com.data.model.response

import com.google.gson.internal.LinkedTreeMap

class LanguagePackResponse(var data: List<LanguageObject>) : BaseResponse() {
    class LanguageObject {
        var code: String = ""
        var name: String = ""
        var dict: LinkedTreeMap<String, String>? = null
    }
}