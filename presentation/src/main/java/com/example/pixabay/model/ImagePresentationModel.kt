package com.example.pixabay.model


data class ImagePresentationModel(
    val id: Int = 0,
    val userId: Int = 0,
    val previewImageURL: String = "",
    val largeImageURL: String = "",
    val likes: Int = 0,
    val tags: String = "",
    val views: Int = 0,
    val user: String = "",
    val comments: Int = 0,
    val downloads: Int = 0,
    var searchString: String = "",
)