package com.data.user

import com.data.common.SharePreferenceManager
import com.data.common.random
import com.data.model.Question
import com.data.model.request.LoginRequest
import com.data.model.request.PanelInfoRequest
import com.data.model.request.SignUpRequest
import com.data.model.request.SocialLoginRequest
import com.data.model.response.BaseResponse
import com.data.model.response.LoginResponse
import com.data.model.response.ScreeningQuestionResponse
import com.data.model.response.SignUpResponse
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class UserRepositoryImpl(private var mService: UserService, private var mSharePreferenceManager: SharePreferenceManager) : UserRepository {

    private var mQuestionsInMemory: ArrayList<ArrayList<Question>>? = null
    private var mScreeningQuestionsInMemory: ScreeningQuestionResponse? = null
    private var mPersonalInfoQuestionsInMemory: ArrayList<Question>? = null
    private var mHouseholdInfoQuestionsInMemory: ArrayList<Question>? = null
    private var mRedeemInfoQuestionsInMemory: ArrayList<Question>? = null

    override fun signUp(signUpRequest: SignUpRequest): Single<SignUpResponse> {
        return mService.signUp(signUpRequest)
                .subscribeOn(Schedulers.io())
    }

    override fun login(loginRequest: LoginRequest): Single<LoginResponse> {
        return mService.login(loginRequest)
                .doOnSuccess {
                    mSharePreferenceManager.userToken = it.data.accessToken
                }.subscribeOn(Schedulers.io())
    }

    override fun socialLogin(socialLoginRequest: SocialLoginRequest): Single<LoginResponse> {
        return mService.socialLogin(socialLoginRequest)
                .doOnSuccess {
                    mSharePreferenceManager.userToken = it.data.accessToken
                }.subscribeOn(Schedulers.io())
    }

    override fun getPersonalInfoQuestions(): Single<ArrayList<Question>> {
        if (mScreeningQuestionsInMemory != null) {
            return Single.just(mScreeningQuestionsInMemory)
                    .map {
                        if (mPersonalInfoQuestionsInMemory != null) {
                            return@map mPersonalInfoQuestionsInMemory!!
                        } else {
                            return@map initQuestionsPersonalInfo(it)
                        }
                    }
                    .subscribeOn(Schedulers.io())
        }
        return mService.getScreeningQuestion()
                .map {
                    mScreeningQuestionsInMemory = it
                    return@map initQuestionsPersonalInfo(it)
                }
                .subscribeOn(Schedulers.io())
    }

    override fun getHouseholdInfoQuestions(): Single<ArrayList<Question>> {
        if (mScreeningQuestionsInMemory != null) {
            return Single.just(mScreeningQuestionsInMemory)
                    .map {
                        if (mHouseholdInfoQuestionsInMemory != null) {
                            return@map mHouseholdInfoQuestionsInMemory!!
                        } else {
                            return@map initQuestionsHouseholdInfo(it)
                        }
                    }
                    .subscribeOn(Schedulers.io())
        }
        return mService.getScreeningQuestion()
                .map {
                    mScreeningQuestionsInMemory = it
                    return@map initQuestionsHouseholdInfo(it)
                }
                .subscribeOn(Schedulers.io())
    }

    override fun getRedeemInfoQuestions(): Single<ArrayList<Question>> {
        if (mScreeningQuestionsInMemory != null) {
            return Single.just(mScreeningQuestionsInMemory)
                    .map {
                        if (mRedeemInfoQuestionsInMemory != null) {
                            return@map mRedeemInfoQuestionsInMemory!!
                        } else {
                            return@map initQuestionsRedeemInfo(it)
                        }
                    }
                    .subscribeOn(Schedulers.io())
        }
        return mService.getScreeningQuestion()
                .map {
                    mScreeningQuestionsInMemory = it
                    return@map initQuestionsRedeemInfo(it)
                }
                .subscribeOn(Schedulers.io())
    }

    override fun submitPanelInfo(panelInfoRequest: PanelInfoRequest): Single<BaseResponse> {
        return mService.submitPanelInfo(panelInfoRequest).subscribeOn(Schedulers.io())
    }

//    override fun getQuestions(): Single<ArrayList<ArrayList<Question>>> {
//        if (mScreeningQuestionsInMemory != null) {
//            return Single.just(mScreeningQuestionsInMemory)
//                    .map {
//                        return@map initPagesQuestions(it)
//                    }
//                    .subscribeOn(Schedulers.io())
//        }
//        return mService.getScreeningQuestion()
//                .map {
//                    mScreeningQuestionsInMemory = it
//                    return@map initPagesQuestions(it)
//                }
//                .subscribeOn(Schedulers.io())
//    }

//    private fun initPagesQuestions(it: ScreeningQuestionResponse): ArrayList<ArrayList<Question>> {
//        val pageQuestions: ArrayList<ArrayList<Question>> = ArrayList()
//        // init questions for personal info
//        initQuestionsPersonalInfo(it, pageQuestions)
//
//        // init question for household info
//        initQuestionsHouseholdInfo(it, pageQuestions)
//
//        //init question for redeem info
//        initQuestionsRedeemInfo(it, pageQuestions)
//
//        return pageQuestions
//    }

    private fun initQuestionsRedeemInfo(it: ScreeningQuestionResponse): ArrayList<Question> {
        val questionsRedeemInfo: ArrayList<Question> = ArrayList()

        val questionAddress = Question()
        val normalQuestionAddress = Question.NormalQuestion()
        normalQuestionAddress.question = "Address"
        questionAddress.normalQuestion = normalQuestionAddress
        questionsRedeemInfo.add(questionAddress)

        val questionCity = Question()
        val normalQuestionCity = Question.NormalQuestion()
        normalQuestionCity.question = "City"
        questionCity.normalQuestion = normalQuestionCity
        questionsRedeemInfo.add(questionCity)

        val questionPostalCode = Question()
        val normalQuestionPostalCode = Question.NormalQuestion()
        normalQuestionPostalCode.question = "Postal Code"
        questionPostalCode.normalQuestion = normalQuestionPostalCode
        questionsRedeemInfo.add(questionPostalCode)

        val questionMobile = Question()
        val normalQuestionMobile = Question.NormalQuestion()
        normalQuestionMobile.question = "Mobile"
        questionMobile.normalQuestion = normalQuestionMobile
        questionsRedeemInfo.add(questionMobile)

        val questionIDType = Question()
        val spinnerQuestion = Question.SpinnerQuestion()
        spinnerQuestion.question = "ID Type"
        val answers: ArrayList<String> = ArrayList()
        answers.add("NRIC/FIN")
        answers.add("Passport")
        answers.add("Other")
        spinnerQuestion.answers = answers
        questionIDType.spinnerQuestion = spinnerQuestion
        questionsRedeemInfo.add(questionIDType)

        val questionIDNumber = Question()
        val normalQuestionIDNumber = Question.NormalQuestion()
        normalQuestionIDNumber.question = "ID Number"
        questionIDNumber.normalQuestion = normalQuestionIDNumber
        questionsRedeemInfo.add(questionIDNumber)

        val questionBankName = Question()
        val normalQuestionBankName = Question.NormalQuestion()
        normalQuestionBankName.question = "Bank Name (optional)"
        normalQuestionBankName.isOptional = true
        questionBankName.normalQuestion = normalQuestionBankName
        questionsRedeemInfo.add(questionBankName)

        val questionBankAccountName = Question()
        val normalQuestionBankAccountName = Question.NormalQuestion()
        normalQuestionBankAccountName.question = "Bank Account Name (optional)"
        normalQuestionBankAccountName.isOptional = true
        questionBankAccountName.normalQuestion = normalQuestionBankAccountName
        questionsRedeemInfo.add(questionBankAccountName)

        val questionBankAccountNumber = Question()
        val normalQuestionBankAccountNumber = Question.NormalQuestion()
        normalQuestionBankAccountNumber.question = "Bank Account Number (optional)"
        normalQuestionBankAccountNumber.isOptional = true
        questionBankAccountNumber.normalQuestion = normalQuestionBankAccountNumber
        questionsRedeemInfo.add(questionBankAccountNumber)

        val questionPaypalRecipient = Question()
        val normalQuestionPaypalRecipient = Question.NormalQuestion()
        normalQuestionPaypalRecipient.question = "Paypal Recipient (optional)"
        normalQuestionPaypalRecipient.isOptional = true
        questionPaypalRecipient.normalQuestion = normalQuestionPaypalRecipient
        questionsRedeemInfo.add(questionPaypalRecipient)

        //random position for screening question
        val questionScreeningRedeemInfo = Question()
        questionScreeningRedeemInfo.screeningQuestion = it.data?.get(2)
        val positionInRedeemInfo = (0 until questionsRedeemInfo.size).random()
        questionsRedeemInfo.add(positionInRedeemInfo, questionScreeningRedeemInfo)

        mRedeemInfoQuestionsInMemory = questionsRedeemInfo
        return questionsRedeemInfo
    }

    private fun initQuestionsHouseholdInfo(it: ScreeningQuestionResponse): ArrayList<Question> {
        val questionsHouseholdInfo: ArrayList<Question> = ArrayList()

        val questionMaritalStatus = Question()
        val spinnerQuestion = Question.SpinnerQuestion()
        spinnerQuestion.question = "Marital Status"
        //                    val answers: ArrayList<Question.SpinnerQuestion.Answer> = ArrayList()
        //                    val answerSingle = Question.SpinnerQuestion.Answer()
        //                    answerSingle.answer = "Single"
        //                    answers.add(answerSingle)
        //                    val answerMarried = Question.SpinnerQuestion.Answer()
        //                    answerMarried.answer = "Married"
        //                    answers.add(answerMarried)
        //                    val answerDivorced = Question.SpinnerQuestion.Answer()
        //                    answerDivorced.answer = "Divorced"
        //                    answers.add(answerDivorced)
        //                    val answerWidow = Question.SpinnerQuestion.Answer()
        //                    answerWidow.answer = "Widow"
        //                    answers.add(answerWidow)
        val answers: ArrayList<String> = ArrayList()
        answers.add("Single")
        answers.add("Married")
        answers.add("Divorced")
        answers.add("Widow")
        spinnerQuestion.answers = answers
        questionMaritalStatus.spinnerQuestion = spinnerQuestion
        questionsHouseholdInfo.add(questionMaritalStatus)

        val questionNumOfChildren = Question()
        val normalQuestionNumOfChildren = Question.NormalQuestion()
        normalQuestionNumOfChildren.question = "Numbers of Children (<18 years old)"
        questionNumOfChildren.normalQuestion = normalQuestionNumOfChildren
        questionsHouseholdInfo.add(questionNumOfChildren)
        val questionNumOfPeople = Question()
        val normalQuestionNumOfPeople = Question.NormalQuestion()
        normalQuestionNumOfPeople.question = "Numbers of People in the household"
        questionNumOfPeople.normalQuestion = normalQuestionNumOfPeople
        questionsHouseholdInfo.add(questionNumOfPeople)
        val questionIncome = Question()
        val normalQuestionIncome = Question.NormalQuestion()
        normalQuestionIncome.question = "Household income"
        questionIncome.normalQuestion = normalQuestionIncome
        questionsHouseholdInfo.add(questionIncome)

        //random position for screening question
        val questionScreeningHousehold = Question()
        questionScreeningHousehold.screeningQuestion = it.data?.get(1)
        val positionInHousehold = (0 until questionsHouseholdInfo.size).random()
        questionsHouseholdInfo.add(positionInHousehold, questionScreeningHousehold)

        mHouseholdInfoQuestionsInMemory = questionsHouseholdInfo
        return questionsHouseholdInfo
    }

    private fun initQuestionsPersonalInfo(it: ScreeningQuestionResponse): ArrayList<Question> {
        val questionsPersonalInfo: ArrayList<Question> = ArrayList()
        val questionDoB = Question()
        questionDoB.doBQuestion = Question.DoBQuestion()
        questionsPersonalInfo.add(questionDoB)
        val questionGender = Question()
        questionGender.genderQuestion = Question.GenderQuestion()
        questionsPersonalInfo.add(questionGender)
        val questionEthnicity = Question()
        questionEthnicity.ethnicityQuestion = Question.EthnicityQuestion()
        questionsPersonalInfo.add(questionEthnicity)

        val questionNationality = Question()
        val normalQuestionNationality = Question.NormalQuestion()
        normalQuestionNationality.question = "Nationality / Primary Citizenship"
        questionNationality.normalQuestion = normalQuestionNationality
        questionsPersonalInfo.add(questionNationality)

        val questionResidency = Question()
        val normalQuestionResidency = Question.NormalQuestion()
        normalQuestionResidency.question = "Residency Status in country of residence"
        questionResidency.normalQuestion = normalQuestionResidency
        questionsPersonalInfo.add(questionResidency)

        val questionEducation = Question()
        val normalQuestionEducation = Question.NormalQuestion()
        normalQuestionEducation.question = "Highest Level of Education"
        questionEducation.normalQuestion = normalQuestionEducation
        questionsPersonalInfo.add(questionEducation)

        val questionEmploymentStatus = Question()
        val spinnerQuestionEmploymentStatus = Question.SpinnerQuestion()
        spinnerQuestionEmploymentStatus.question = "Employment Status"
        val answersEmploymentStatus: ArrayList<String> = ArrayList()
        answersEmploymentStatus.add("Employed full time (40 or more hours per week)")
        answersEmploymentStatus.add("Employed part time (up to 39 hours per week)")
        answersEmploymentStatus.add("Self-employed")
        answersEmploymentStatus.add("Unemployed and currently looking for work")
        answersEmploymentStatus.add("Unemployed and not currently looking for work")
        answersEmploymentStatus.add("Student")
        answersEmploymentStatus.add("Retired")
        answersEmploymentStatus.add("Homemaker")
        answersEmploymentStatus.add("Unable to work")
        spinnerQuestionEmploymentStatus.answers = answersEmploymentStatus
        questionEmploymentStatus.spinnerQuestion = spinnerQuestionEmploymentStatus
        questionsPersonalInfo.add(questionEmploymentStatus)

        val questionCompany = Question()
        val spinnerQuestionCompany = Question.SpinnerQuestion()
        spinnerQuestionCompany.question = "Industry of company work"
        val answersCompany: ArrayList<String> = ArrayList()
        answersCompany.add("Agriculture and Fishing")
        answersCompany.add("Mining and Quarrying")
        answersCompany.add("Manufacturing")
        answersCompany.add("Electricity, Gas, Steam and Air--Conditioning Supply")
        answersCompany.add("Water Supply")
        answersCompany.add("Sewerage, Waste Management and Remediation Activities")
        answersCompany.add("Construction")
        answersCompany.add("Wholesale and Retail Trade")
        answersCompany.add("Transportation and Storage")
        answersCompany.add("Accomodation and Food Service Activities")
        answersCompany.add("Information and Communications")
        answersCompany.add("Financial and Insurance Activities")
        answersCompany.add("Real Estate Activities")
        answersCompany.add("Professional, Scientific and Technical Activities")
        answersCompany.add("Administrative and Support Service Activities")
        answersCompany.add("Public Administration and Defence")
        answersCompany.add("Education")
        answersCompany.add("Health and Social Services")
        answersCompany.add("Arts, Entertainment and Recreation")
        spinnerQuestionCompany.answers = answersCompany
        questionCompany.spinnerQuestion = spinnerQuestionCompany
        questionsPersonalInfo.add(questionCompany)

        val questionMainJob = Question()
        val spinnerQuestionMainJob = Question.SpinnerQuestion()
        spinnerQuestionMainJob.question = "Main Job Function"
        val answersMainJob: ArrayList<String> = ArrayList()
        answersMainJob.add("Administrative / Management")
        answersMainJob.add("Communications & PR")
        answersMainJob.add("Consulting")
        answersMainJob.add("Customer Service")
        answersMainJob.add("Design")
        answersMainJob.add("Engineering")
        answersMainJob.add("Finance and Accounting")
        answersMainJob.add("Healthcare")
        answersMainJob.add("Hospitality")
        answersMainJob.add("Human Resources")
        answersMainJob.add("IT & Tech Support")
        answersMainJob.add("Legal")
        answersMainJob.add("Logistics & Distribution")
        answersMainJob.add("Maintenance")
        answersMainJob.add("Management")
        answersMainJob.add("Marketing")
        answersMainJob.add("Market Research")
        answersMainJob.add("Planning")
        answersMainJob.add("Production")
        answersMainJob.add("Purchasing")
        answersMainJob.add("Quality Assurance")
        answersMainJob.add("R&D")
        answersMainJob.add("Safety & Hygiene")
        answersMainJob.add("Sales")
        answersMainJob.add("Strategy")
        answersMainJob.add("Teaching")
        spinnerQuestionMainJob.answers = answersMainJob
        questionMainJob.spinnerQuestion = spinnerQuestionMainJob
        questionsPersonalInfo.add(questionMainJob)

        val questionPositionLevel = Question()
        val spinnerQuestionPositionLevel = Question.SpinnerQuestion()
        spinnerQuestionPositionLevel.question = "Position Level"
        val answersPositionLevel: ArrayList<String> = ArrayList()
        answersPositionLevel.add("Intern, Entry Level, Analyst / Associate")
        answersPositionLevel.add("Manager, Senior Manager")
        answersPositionLevel.add("Director, Vice President, Senior Vice President")
        answersPositionLevel.add("C level executive (CIO, CTO, COO, CMO, Etc)")
        answersPositionLevel.add("President or CEO")
        answersPositionLevel.add("Owner or Founder")
        spinnerQuestionPositionLevel.answers = answersPositionLevel
        questionPositionLevel.spinnerQuestion = spinnerQuestionPositionLevel
        questionsPersonalInfo.add(questionPositionLevel)

        //random position for screening question
        val questionScreening = Question()
        questionScreening.screeningQuestion = it.data?.get(0)
        val position = (0 until questionsPersonalInfo.size).random()
        questionsPersonalInfo.add(position, questionScreening)

        mPersonalInfoQuestionsInMemory = questionsPersonalInfo
        return questionsPersonalInfo
    }
}