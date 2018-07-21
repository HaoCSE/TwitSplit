package com.data.persistence

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface CountryDao {
    @Query("SELECT * FROM country")
    fun getCountries(): Single<List<Country>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCountry(country: Country)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCountries(countries: List<Country>)
}
