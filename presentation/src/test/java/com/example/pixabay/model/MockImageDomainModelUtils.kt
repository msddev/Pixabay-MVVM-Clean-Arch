package com.example.pixabay.model

import com.example.domain.model.ImageDomainModel

object MockImageDomainModelUtils {

    internal fun getMockImageDomainModel(): ImageDomainModel {
        return ImageDomainModel(
            id = 5,
            previewImageURL = "https://www.previewImageURL.com",
            largeImageURL = "https://www.largeImageURL.com",
            user = "user_test",
            tags = "Tag1, Tag2, Tag3",
            likes = 10,
            downloads = 20,
            comments = 30,
            userId = 1010,
            views = 40,
            searchString = "fruits",
        )
    }

    internal fun getMockImageDomainModelList(): List<ImageDomainModel> {
        return listOf(
            getMockImageDomainModel(),
        )
    }
}