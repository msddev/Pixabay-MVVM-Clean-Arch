package com.example.data.dataSource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_key_table")
data class RemoteKeyEntity(
    @PrimaryKey
    val imageId: Int,
    val prevPage: Int?,
    val nextPage: Int?
)