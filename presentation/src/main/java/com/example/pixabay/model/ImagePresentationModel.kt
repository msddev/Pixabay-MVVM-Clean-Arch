package com.example.pixabay.model


data class ImagePresentationModel(
    val id: Int,
    val userId: Int,
    val previewImageURL: String,
    val largeImageURL: String,
    val likes: Int,
    val tags: String,
    val views: Int,
    val user: String,
    val comments: Int,
    val downloads: Int,
    var searchString: String
)