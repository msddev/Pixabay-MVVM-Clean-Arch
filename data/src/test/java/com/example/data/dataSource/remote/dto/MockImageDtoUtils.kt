package com.example.data.dataSource.remote.dto

object MockImageDtoUtils {

    internal fun getMockImageDtoList(): List<ImageDto> {
        return listOf(
            ImageDto(
                id = 5,
                previewURL = "https://www.previewURL.com",
                largeImageURL = "https://www.largeImageURL.com",
                user = "user_test",
                tags = "Tag1, Tag2, Tag3",
                likes = 10,
                downloads = 20,
                comments = 30,
                userId = 1010,
                views = 40,
            )
        )
    }
}