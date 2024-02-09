package com.example.pixabay.mapper

import com.example.domain.model.ImageDomainModel
import com.example.pixabay.model.ImagePresentationModel

fun ImageDomainModel.toImagePresentation(): ImagePresentationModel {
    return ImagePresentationModel(
        id = id,
        previewImageURL = previewImageURL,
        largeImageURL = largeImageURL,
        comments = comments,
        downloads = downloads,
        likes = likes,
        tags = tags,
        user = user,
        userId = userId,
        views = views,
        searchString = searchString
    )
}

