package com.data.persistence

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Single

@Dao
interface LanguagePackDao {

    @Query("SELECT * FROM language_pack WHERE code = :code")
    fun getLanguageByLanguageCode(code: String): Single<LanguagePack>

    @Query("SELECT * FROM language_pack")
    fun getLanguages(): Single<List<LanguagePack>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLanguagePack(languagePack: LanguagePack)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLanguagePacks(languagePack: List<LanguagePack>)
}