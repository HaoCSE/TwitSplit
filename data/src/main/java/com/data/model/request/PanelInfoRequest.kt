package com.data.model.request

import com.google.gson.annotations.SerializedName

 class PanelInfoRequest {
         var email: String = ""
         var status: Int = 0
         @SerializedName("date_of_birth")
         var date_ofBirth: String = ""
         var gender: String = ""
         var ethnicity: String = ""
         var nationality: String = ""
         @SerializedName("residence_country")
         var residenceCountry: String = ""
         @SerializedName("residence_status")
         var residenceStatus: String = ""
         @SerializedName("highest_education")
         var highestEducation: String = ""
         @SerializedName("employment_status")
         var employmentStatus: String = ""
         var industry: String = ""
         @SerializedName("job_title")
         var jobTitle: String = ""
         @SerializedName("marital_status")
         var maritalStatus: String = ""
         var children: String = ""
         @SerializedName("Household_people")
         var householdPeople: String = ""
         @SerializedName("Household_income")
         var householdIncome: String = ""
         @SerializedName("postal_code")
         var postalCode: String = ""
         @SerializedName("address_1")
         var address1: String = ""
         var phone: String = ""
         @SerializedName("pannel_info")
         var panelInfo: List<PanelInfo>? = null

         class PanelInfo {
             @SerializedName("pannel_name")
             var panelName: String = ""
                 @SerializedName("question_id")
             var questionId: Int = 0
                 @SerializedName("answer_id")
             var answerId: Int = 0
         }
}
