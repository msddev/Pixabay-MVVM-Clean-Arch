package com.example.data.dataSource.remote.api

import com.example.data.dataSource.remote.dto.ImageResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApi {
    @GET("api/")
    suspend fun searchImage(
        @Query("q") searchString: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): ImageResponseDto
}