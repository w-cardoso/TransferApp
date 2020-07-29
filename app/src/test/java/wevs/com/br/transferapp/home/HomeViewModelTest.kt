package wevs.com.br.transferapp.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.coEvery
import io.mockk.mockkStatic
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import wevs.com.br.transferapp.ui.home.HomeStates
import wevs.com.br.transferapp.ui.home.HomeUseCase
import wevs.com.br.transferapp.ui.home.HomeViewModel
import wevs.com.br.transferapp.ui.home.providerHomeUseCase

class HomeViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<HomeStates>
    private val viewModel: HomeViewModel = HomeViewModel()

    @Suppress("UNCHECKED_CAST")
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        observer = Mockito.mock(Observer::class.java) as Observer<HomeStates>
        mockkStatic("wevs.com.br.transferapp.ui.home.HomeProvider")
        coEvery { providerHomeUseCase() } returns HomeUseCase()
    }

    @Test
    fun validateChangeStatesBundleOk() {
    }

    @Test
    fun validateChangeStatesBundleNOk() {

    }

    @Test
    fun validateChangeStatesCallOK() {

    }

    @Test
    fun validateChangeStatesCallError() {

    }
}