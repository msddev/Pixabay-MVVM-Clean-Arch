package com.example.data.dataSource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_table")
data class ImageEntity(
    @PrimaryKey(autoGenerate = true)
    val imageId: Int = 0,
    val id: Int,
    val previewImageURL: String,
    val largeImageURL: String,
    val user: String,
    val tags: String,
    val likes: Int,
    val downloads: Int,
    val comments: Int,
    val userId: Int,
    val views: Int,
    var searchString: String,
)
