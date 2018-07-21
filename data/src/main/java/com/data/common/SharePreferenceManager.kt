package com.data.common

import android.content.Context
import android.content.SharedPreferences


class SharePreferenceManager constructor(app: Context) {

    companion object {
        private const val SHARED_PREF_NAME = "shared_pref"
        private const val USER_TOKEN = "user_token"
        private const val USER_UUID = "user_uuid"
        private const val USER_NAME = "user_name"
        private const val USER_AVATAR = "user_avatar"
        private const val DEVICE_TOKEN = "device_token"
        private const val USER_LANGUAGE = "user_language"
        private const val ACCUMULATION_POINT = "accumulation_point"
        private const val UNDER_VERIFY = "under_verify"
        private const val QR_CODE = "qr_code"
        private const val CURRENT_VOUCHER_COUNT = "current_voucher_count"
    }

    private val sharedPreferences by lazy(LazyThreadSafetyMode.NONE) {
        app.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }

    var deviceToken: String
        get() = sharedPreferences.getString(DEVICE_TOKEN, "")!!
        set(value) = sharedPreferences.put { putString(DEVICE_TOKEN, value) }

    var userToken: String
        get() = sharedPreferences.getString(USER_TOKEN, "")!!
        set(value) = sharedPreferences.put { putString(USER_TOKEN, value) }

    var userUUID: String
        get() = sharedPreferences.getString(USER_UUID, "")!!
        set(value) = sharedPreferences.put { putString(USER_UUID, value) }

    var userAvatar: String
        get() = sharedPreferences.getString(USER_AVATAR, "")!!
        set(value) = sharedPreferences.put { putString(USER_AVATAR, value) }
    var userName: String
        get() = sharedPreferences.getString(USER_NAME, "")!!
        set(value) = sharedPreferences.put { putString(USER_NAME, value) }

    var userLanguage: String
        get() = sharedPreferences.getString(USER_LANGUAGE, "")!!
        set(value) = sharedPreferences.put { putString(USER_LANGUAGE, value) }

    fun clearData() {
        sharedPreferences.edit().clear().apply()
    }

    private inline fun SharedPreferences.put(body: SharedPreferences.Editor.() -> Unit) {
        val editor = this.edit()
        editor.body()
        editor.apply()
    }
}