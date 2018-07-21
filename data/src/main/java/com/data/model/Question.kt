package com.data.model

import com.google.gson.annotations.SerializedName

class Question {
    var doBQuestion: DoBQuestion? = null
    var genderQuestion: GenderQuestion? = null
    var ethnicityQuestion: EthnicityQuestion? = null
    var normalQuestion: NormalQuestion? = null
    var screeningQuestion: ScreeningQuestion? = null
    var spinnerQuestion: SpinnerQuestion? = null

    var type = TYPE_NORMAL
        get() = when {
            doBQuestion != null -> TYPE_DOB
            genderQuestion != null -> TYPE_GENDER
            ethnicityQuestion != null -> TYPE_ETHNICITY
            normalQuestion != null -> TYPE_NORMAL
            screeningQuestion != null -> TYPE_SCREENING
            spinnerQuestion != null -> TYPE_SPINNER
            else -> {
                TYPE_NORMAL
            }
        }

    class DoBQuestion {
        var dob = ""
    }

    class GenderQuestion {
        var isMale: Boolean = false
        var isFemale: Boolean = false
    }

    class EthnicityQuestion {
        var ethnicity = ""
    }

    class NormalQuestion {
        var question = ""
        var answer = ""
        var isOptional = false
    }

    class ScreeningQuestion {
        var id = 0
        var question = ""
        var image = ""
        var answers: List<Answer>? = null

        class Answer {
            @SerializedName("answer_id")
            var answerId = 0
            var answer = ""
            var isChose = false
        }

    }

    class SpinnerQuestion {
        var question = ""
        var answerChose = ""
        var answers: List<String>? = null

        class Answer {
            var answer = ""
        }
    }

    companion object {
        const val TYPE_DOB = 1
        const val TYPE_NORMAL = 2
        const val TYPE_SCREENING = 3
        const val TYPE_GENDER = 5
        const val TYPE_ETHNICITY = 6
        const val TYPE_SPINNER = 7
    }
}