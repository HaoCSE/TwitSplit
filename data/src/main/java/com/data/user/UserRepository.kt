package com.data.user

import com.data.model.Question
import com.data.model.request.LoginRequest
import com.data.model.request.PanelInfoRequest
import com.data.model.request.SignUpRequest
import com.data.model.request.SocialLoginRequest
import com.data.model.response.BaseResponse
import com.data.model.response.LoginResponse
import com.data.model.response.SignUpResponse
import io.reactivex.Single

interface UserRepository {
    fun signUp(signUpRequest: SignUpRequest): Single<SignUpResponse>
    fun login(loginRequest: LoginRequest): Single<LoginResponse>
    fun socialLogin(socialLoginRequest: SocialLoginRequest): Single<LoginResponse>
    fun getPersonalInfoQuestions(): Single<ArrayList<Question>>
    fun getHouseholdInfoQuestions(): Single<ArrayList<Question>>
    fun getRedeemInfoQuestions(): Single<ArrayList<Question>>
    fun submitPanelInfo(panelInfoRequest: PanelInfoRequest): Single<BaseResponse>
}