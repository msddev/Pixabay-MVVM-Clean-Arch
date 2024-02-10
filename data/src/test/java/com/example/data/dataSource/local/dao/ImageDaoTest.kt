package com.example.data.dataSource.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.data.dataSource.local.database.LocalPixabayRoomDatabase
import com.example.data.dataSource.local.entity.MockImageEntityUtils
import com.example.data.util.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ImageDaoTest : LocalPixabayRoomDatabase() {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertAndGetImageList() = runBlocking {

        val imageEntityList = MockImageEntityUtils.getMockImageEntityList()
        val query = "fruits"

        database.imageDao().insertAll(imageEntityList)

        val dbImageEntityList = database.imageDao().queryImages(query)

        assertEquals(imageEntityList, dbImageEntityList)
    }

    @Test
    fun insertAndGetImageById() = runBlocking {

        val imageEntityList = MockImageEntityUtils.getMockImageEntityList()
        val imageId = "123"

        database.imageDao().insertAll(imageEntityList)

        val dbImageEntity = database.imageDao().getImagesById(imageId)

        assertEquals(imageEntityList[0].imageId, dbImageEntity.imageId)
    }
}