package com.data.persistence

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "country")
data class Country(@PrimaryKey
                     @ColumnInfo(name = "id")
                     var id: Int,
                   @ColumnInfo(name = "code")
                     var code: String,
                   @ColumnInfo(name = "name")
                     var name: String,
                   @SerializedName("code_tel")
                   @ColumnInfo(name = "code_tel")
                     var codeTel: String = "")
