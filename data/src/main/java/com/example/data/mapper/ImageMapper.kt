package com.example.data.mapper

import com.example.data.dataSource.local.entity.ImageEntity
import com.example.data.dataSource.remote.dto.ImageDto
import com.example.domain.model.ImageDomainModel

fun ImageEntity.toDomainImage(): ImageDomainModel {
    return ImageDomainModel(
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

fun ImageDto.toImageEntity(searchSting: String): ImageEntity {
    return ImageEntity(
        id = id,
        previewImageURL = previewURL,
        largeImageURL = largeImageURL,
        comments = comments,
        downloads = downloads,
        likes = likes,
        tags = tags,
        user = user,
        userId = userId,
        views = views,
        searchString = searchSting
    )
}



