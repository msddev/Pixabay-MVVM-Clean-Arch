package com.example.data.dataSource.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.dataSource.local.dao.ImageDao
import com.example.data.dataSource.local.dao.RemoteKeyDao
import com.example.data.dataSource.local.entity.ImageEntity
import com.example.data.dataSource.local.entity.RemoteKeyEntity

@Database(
    entities = [ImageEntity::class, RemoteKeyEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PixabayRoomDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDao
    abstract fun remoteKeyDao(): RemoteKeyDao

    companion object {
        @Volatile
        private var INSTANCE: PixabayRoomDatabase? = null

        fun getDatabase(context: Context): PixabayRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PixabayRoomDatabase::class.java,
                    "pixabay_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}