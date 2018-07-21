package com.data.persistence

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "language_pack")
data class LanguagePack(@PrimaryKey
                        @ColumnInfo(name = "code")
                        val code: String,
                        @ColumnInfo(name = "name")
                        val name: String,
                        @ColumnInfo(name = "dict")
                        val dict: String) {
    @Ignore
    var isChose: Boolean = false
}