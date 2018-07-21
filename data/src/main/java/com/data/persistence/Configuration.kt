package com.data.persistence

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "configuration")
data class Configuration(@PrimaryKey
                         @ColumnInfo(name = "id")
                         var id: Int,
                         @SerializedName("language_pack_version")
                         @ColumnInfo(name = "language_pack_version")
                         var languagePackVersion: Long,
                         @SerializedName("splash_screen_version")
                         @ColumnInfo(name = "splash_screen_version")
                         var splashScreenVersion: Long,
                         @SerializedName("question_pack_version")
                         @ColumnInfo(name = "question_pack_version")
                         var questionPackVersion: Long,
                         @SerializedName("field_options")
                         @ColumnInfo(name = "field_options")
                         var fieldOptions: Long
)