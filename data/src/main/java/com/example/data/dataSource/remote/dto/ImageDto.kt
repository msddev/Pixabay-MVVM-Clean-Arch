package com.example.data.dataSource.remote.dto

import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("tags")
    val tags: String,
    @SerializedName("previewURL")
    val previewURL: String,
    @SerializedName("largeImageURL")
    val largeImageURL: String,
    @SerializedName("views")
    val views: Int,
    @SerializedName("downloads")
    val downloads: Int,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("comments")
    val comments: Int,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("user")
    val user: String,
)