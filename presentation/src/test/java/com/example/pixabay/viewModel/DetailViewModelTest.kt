package com.example.pixabay.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.data.util.CoroutineTestRule
import com.example.domain.usecase.GetImageUseCase
import com.example.pixabay.mapper.toImagePresentation
import com.example.pixabay.model.MockImageDomainModelUtils
import com.example.pixabay.repository.FakeImageRepository
import com.example.pixabay.ui.navigation.ARG_IMAGE_ID
import com.example.pixabay.ui.screen.detail.DetailUiState
import com.example.pixabay.ui.screen.detail.DetailViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class DetailViewModelTest {

    private lateinit var imageViewModel: DetailViewModel

    private lateinit var getImageUseCase: GetImageUseCase
    private lateinit var savedStateHandle: SavedStateHandle

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineTestRule = CoroutineTestRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        savedStateHandle = SavedStateHandle().apply {
            set(ARG_IMAGE_ID, "123")
        }
    }

    @Test
    fun `getImage() Loading state`() = runTest {
        // Given
        val imageId = "123"

        getImageUseCase = GetImageUseCase(FakeImageRepository(DetailUiState.Loading))
        imageViewModel = DetailViewModel(savedStateHandle, getImageUseCase)

        // When
        imageViewModel.getImage(imageId)

        //Then
        assertEquals(
            DetailUiState.Loading,
            imageViewModel.state.value
        )
    }

    @Test
    fun `getImage() Success state`() = runTest {
        // Given
        val mockImagePresentationModel =
            MockImageDomainModelUtils.getMockImageDomainModel().toImagePresentation()
        val imageId = "123"

        getImageUseCase = GetImageUseCase(FakeImageRepository(DetailUiState.Success()))
        imageViewModel = DetailViewModel(savedStateHandle, getImageUseCase)

        // When
        imageViewModel.getImage(imageId)

        //Then
        assertEquals(
            DetailUiState.Success(mockImagePresentationModel),
            imageViewModel.state.value
        )
    }

    @Test
    fun `getImage() Error state`() = runTest {
        // Given
        val error = "ERROR"
        val imageId = "123"

        getImageUseCase = GetImageUseCase(FakeImageRepository(DetailUiState.Error()))
        imageViewModel = DetailViewModel(savedStateHandle, getImageUseCase)

        // When
        imageViewModel.getImage(imageId)

        //Then
        assertEquals(
            DetailUiState.Error(error),
            imageViewModel.state.value
        )
    }
}