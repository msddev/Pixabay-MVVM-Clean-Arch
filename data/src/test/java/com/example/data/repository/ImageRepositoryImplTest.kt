package com.example.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.data.dataSource.local.dao.ImageDao
import com.example.data.dataSource.local.entity.MockImageEntityUtils
import com.example.data.util.CoroutineTestRule
import com.example.domain.repository.ImageRepository
import com.example.domain.unit.Resource
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

class ImageRepositoryImplTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var imageRepository: ImageRepository
    private val imageDao = mockk<ImageDao>()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        imageRepository = ImageRepositoryImpl(imageDao)
    }

    @Test
    fun `getImage() Loading and Success emission`() = runTest {
        //Given
        val mockImage = MockImageEntityUtils.getMockImageEntity()
        val imageId = "123"

        coEvery {
            imageDao.getImagesById(imageId)
        } returns mockImage

        // When and Then
        imageRepository.getImage(imageId).test {
            assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
            assertThat(awaitItem().data?.id).isEqualTo(
                Resource.Success(mockImage).data?.id
            )
            awaitComplete()
        }
    }

    @Test
    fun `getImage() Error emission`() = runTest {
        //Given
        val imageId = "123"
        val exception = Exception("Oops! something went wrong")

        coEvery {
            imageDao.getImagesById(imageId)
        } throws exception

        // When and Then
        imageRepository.getImage(imageId).test {
            assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)
            assertThat(awaitItem()).isInstanceOf(Resource.Error::class.java)
            awaitComplete()
        }
    }
}