package wevs.com.br.transferapp.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import io.mockk.coEvery
import io.mockk.mockkStatic
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import wevs.com.br.transferapp.ui.login.*


class LoginViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var observer: Observer<LoginStates>
    private val viewModel: LoginViewModel = LoginViewModel()

    @Suppress("UNCHECKED_CAST")
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        observer = mock(Observer::class.java) as Observer<LoginStates>
        mockkStatic("wevs.com.br.transferapp.ui.login.LoginProvider")
        coEvery { providerLoginUseCase() } returns LoginUseCase()
    }


    @Test
    fun checkGetValuesSecurePreferencesOK() {
        viewModel.viewStates.observeForever(observer)
        viewModel.interpret(LoginInteractor.GetValues("wevs@gmail.com", "Wevs@gmail33"))
        verify(observer).onChanged(isA(LoginStates.GetValuesSharedPreference::class.java))
    }

    @Test
    fun checkValidateFieldUserOk() {
        viewModel.viewStates.observeForever(observer)
        viewModel.interpret(LoginInteractor.ValidateFieldUser("wevs@gmail.com"))
        verify(observer).onChanged(isA(LoginStates.UserSucess::class.java))
    }

    @Test
    fun checkValidateFieldUserNOk() {
        viewModel.viewStates.observeForever(observer)
        viewModel.interpret(LoginInteractor.ValidateFieldUser("w"))
        verify(observer).onChanged(isA(LoginStates.UserError::class.java))
    }

    @Test
    fun checkValidateFieldPasswordOk() {
        viewModel.viewStates.observeForever(observer)
        viewModel.interpret(LoginInteractor.ValidateFieldPassword("Wevs@gmail334"))
        verify(observer).onChanged(isA(LoginStates.PasswordSucess::class.java))
    }

    @Test
    fun checkValidateFieldPasswordNOk() {
        viewModel.viewStates.observeForever(observer)
        viewModel.interpret(LoginInteractor.ValidateFieldPassword("0"))
        verify(observer).onChanged(isA(LoginStates.PasswordError::class.java))
    }

    @Test
    fun checkEnableButtonLogin() {
        viewModel.viewStates.observeForever(observer)
        viewModel.interpret(
            LoginInteractor.EnableButtonLogin(
                "wevs@gmail.com", "Wevs@gmail334"
            )
        )
        verify(observer).onChanged(isA(LoginStates.EnableButton::class.java))
    }

    @Test
    fun checkDisableButtonLogin() {
        viewModel.viewStates.observeForever(observer)
        viewModel.interpret(
            LoginInteractor.EnableButtonLogin(
                "error", "error"
            )
        )
        verify(observer).onChanged(isA(LoginStates.DisableButton::class.java))
    }

    @Test
    fun checkSaveSecurePreferences() {
        viewModel.viewStates.observeForever(observer)
        viewModel.interpret(
            LoginInteractor.SaveDataSharedPreferences(
                "wevs@gmail.com",
                "Wevs@gmail334"
            )
        )
        verify(observer).onChanged(isA(LoginStates.SaveLoginSecurePreferences::class.java))
    }

    @Test
    fun checkPostLoginOk() {
        runBlocking {
        }
        viewModel.viewStates.observeForever(observer)
    }

    @Test
    fun checkPostLoginNOk() {
        runBlocking {
        }
        viewModel.viewStates.observeForever(observer)
    }
}