package com.example.data.util

import kotlin.time.Duration.Companion.seconds

object DataConstants {
    const val PAGE_SIZE = 20
    const val FIRST_PAGE = 1
}

object ApiConfigs {
    const val BASE_URL: String = "https://pixabay.com/"
    const val IMAGE_TYPE: String = "photo"

    //10 MB cache
    const val cacheSize = (10 * 1024 * 1024).toLong()

    object Timeouts {
        val connect = 10.seconds
        val write = 10.seconds
        val read = 10.seconds
    }
}
