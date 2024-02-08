package com.example.data.dataSource.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.dataSource.local.entity.RemoteKeyEntity

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeyEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(remoteKey: RemoteKeyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: RemoteKeyEntity)

    @Query("SELECT * FROM remote_key_table WHERE imageId = :id")
    suspend fun remoteKeysImageId(id: Int): RemoteKeyEntity?

    @Query("DELETE FROM remote_key_table")
    suspend fun clearRemoteKeys()

    @Query("SELECT * FROM remote_key_table")
    suspend fun getAll(): List<RemoteKeyEntity>
}