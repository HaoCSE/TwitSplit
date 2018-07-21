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
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class UserDomain @Inject constructor(private var mRepository: UserRepository) {
    fun signUp(signUpRequest: SignUpRequest): Single<SignUpResponse> {
        return mRepository.signUp(signUpRequest).observeOn(AndroidSchedulers.mainThread())
    }

    fun login(loginRequest: LoginRequest): Single<LoginResponse> {
        return mRepository.login(loginRequest).observeOn(AndroidSchedulers.mainThread())
    }

    fun socialLogin(socialLoginRequest: SocialLoginRequest): Single<LoginResponse> {
        return mRepository.socialLogin(socialLoginRequest).observeOn(AndroidSchedulers.mainThread())
    }

    fun getPersonalInfoQuestions(): Single<ArrayList<Question>> {
        return mRepository.getPersonalInfoQuestions().observeOn(AndroidSchedulers.mainThread())
    }

    fun getHouseholdInfoQuestions(): Single<ArrayList<Question>> {
        return mRepository.getHouseholdInfoQuestions().observeOn(AndroidSchedulers.mainThread())
    }

    fun getRedeemInfoQuestions(): Single<ArrayList<Question>> {
        return mRepository.getRedeemInfoQuestions().observeOn(AndroidSchedulers.mainThread())
    }

    fun submitPanelInfo(panelInfoRequest: PanelInfoRequest): Single<BaseResponse> {
        return mRepository.submitPanelInfo(panelInfoRequest).observeOn(AndroidSchedulers.mainThread())
    }
}
