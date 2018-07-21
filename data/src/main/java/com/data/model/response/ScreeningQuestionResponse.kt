package com.data.model.response

import com.data.model.Question

class ScreeningQuestionResponse : BaseResponse() {
    var data: List<Question.ScreeningQuestion>? = null
}