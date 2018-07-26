package com.data.common

import android.content.Context
import android.content.SharedPreferences


class SharePreferenceManager constructor(app: Context) {

    companion object {
        private const val SHARED_PREF_NAME = "shared_pref"
        private const val USER_TOKEN = "user_token"
        private const val USER_UUID = "user_uuid"
    }

    private val sharedPreferences by lazy(LazyThreadSafetyMode.NONE) {
        app.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }

    var userToken: String
        get() = sharedPreferences.getString(USER_TOKEN, "")!!
        set(value) = sharedPreferences.put { putString(USER_TOKEN, value) }

    var userUUID: String
        get() = sharedPreferences.getString(USER_UUID, "")!!
        set(value) = sharedPreferences.put { putString(USER_UUID, value) }

    fun clearData() {
        sharedPreferences.edit().clear().apply()
    }

    private inline fun SharedPreferences.put(body: SharedPreferences.Editor.() -> Unit) {
        val editor = this.edit()
        editor.body()
        editor.apply()
    }
}