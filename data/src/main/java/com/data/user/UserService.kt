package com.data.user

import com.data.model.request.LoginRequest
import com.data.model.request.PanelInfoRequest
import com.data.model.request.SignUpRequest
import com.data.model.request.SocialLoginRequest
import com.data.model.response.BaseResponse
import com.data.model.response.LoginResponse
import com.data.model.response.ScreeningQuestionResponse
import com.data.model.response.SignUpResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {

    @POST("/api/v1/signup")
    fun signUp(@Body signUpRequest: SignUpRequest): Single<SignUpResponse>

    @POST("/api/v1/login")
    fun login(@Body loginRequest: LoginRequest): Single<LoginResponse>

    @POST("/api/v1/social-login")
    fun socialLogin(@Body socialLoginRequest: SocialLoginRequest): Single<LoginResponse>

    @GET("/api/v1/screening-question")
    fun getScreeningQuestion(): Single<ScreeningQuestionResponse>

    @POST("/api/v1/personal-information")
    fun submitPanelInfo(@Body panelInfoRequest: PanelInfoRequest): Single<BaseResponse>
}