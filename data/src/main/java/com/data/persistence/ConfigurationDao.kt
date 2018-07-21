package com.data.persistence

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Single

@Dao
interface ConfigurationDao {
    @Query("SELECT * FROM configuration")
    fun getConfiguration(): Single<Configuration>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertConfiguration(configuration: Configuration)
}