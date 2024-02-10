package com.example.data.dataSource.local.entity

object MockImageEntityUtils {

    internal fun getMockImageEntity(): ImageEntity {
        return ImageEntity(
            imageId = 123,
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

    internal fun getMockImageEntityList(): List<ImageEntity> {
        return listOf(
            getMockImageEntity(),
            getMockImageEntity(),
        )
    }
}