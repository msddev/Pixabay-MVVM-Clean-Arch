package com.example.data.dataSource.remote.dto

import com.google.gson.annotations.SerializedName

data class ImageResponseDto(
    @SerializedName("total")
    val total: Int,
    @SerializedName("totalHits")
    val totalHits: Int,
    @SerializedName("hits")
    val images: List<ImageDto>,
)
