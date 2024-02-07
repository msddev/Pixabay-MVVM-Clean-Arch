package com.example.domain.model


data class ImageModel(
    val id: Int,
    val userId: Int,
    val largeImageURL: String,
    val likes: Int,
    val tags: String,
    val views: Int,
    val user: String,
    val comments: Int,
    val downloads: Int,
    var searchTerm: String
)