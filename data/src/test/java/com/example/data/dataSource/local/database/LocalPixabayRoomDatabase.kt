package com.example.data.dataSource.local.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [24], manifest = Config.NONE)
abstract class LocalPixabayRoomDatabase {
    lateinit var database: PixabayRoomDatabase

    @Before
    fun initDB() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            PixabayRoomDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun closeDB() {
        database.close()
    }
}