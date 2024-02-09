package com.example.data.util

import retrofit2.HttpException
import java.io.IOException

fun Exception.handleException(): String {
    return when (this) {
        is IOException -> {
            "Couldn't reach server, check your internet connection."
        }

        is HttpException -> {
            "Oops, something went wrong!"
        }

        else -> {
            "Oops, something went wrong!"
        }
    }
}