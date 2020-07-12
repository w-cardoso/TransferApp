package wevs.com.br.transferapp.ui.login

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import wevs.com.br.transferapp.R
import wevs.com.br.transferapp.model.Login
import wevs.com.br.transferapp.model.UserAccount
import wevs.com.br.transferapp.ui.edittext.CustomEditText
import wevs.com.br.transferapp.ui.home.HomeActivity
import wevs.com.br.transferapp.utils.*

class LoginActivity : AppCompatActivity() {
    private val viewModel by lazy { providerLoginViewModel(this) }

    private val edtUser by lazy { findViewById<CustomEditText>(R.id.login_edt_user) }
    private val edtPassword by lazy { findViewById<CustomEditText>(R.id.login_edt_password) }
    private val btnLogin by lazy { findViewById<Button>(R.id.login_btn) }

    override fun onStart() {
        super.onStart()
        val (username, password) = startSecurePreferences()
        viewModel.interpret(LoginInteractor.GetValuesUserAndPassword(username, password))
    }

    private fun startSecurePreferences(): Pair<String?, String?> {
        val preferences = SecurePreferences(
            this, PREFERENCE_NAME, SECURE_KEY, true
        )
        val username = preferences.getString(USER_SECURE_PREFERENCES)
        val password = preferences.getString(PASSWORD_SECURE_PREFERENCES)
        return Pair(username, password)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        starViewModel()
        clickListenerBtnLogin()
    }

    private fun clickListenerBtnLogin() {
        btnLogin.setOnClickListener {
            viewModel.interpret(
                LoginInteractor.PostLogin(
                    Login(
                        edtUser.editText.text.toString(),
                        edtPassword.editText.text.toString()
                    )
                )
            )
        }
    }

    private fun starViewModel() {
        viewModel.viewStates.observe(this, Observer { viewStates ->
            viewStates.let {
                when (it) {
                    is LoginStates.GetValuesSharedPreference -> {
                        populateEditText(it.user, it.password)
                    }

                    is LoginStates.CallSucess -> {
                        viewModel.interpret(
                            LoginInteractor.SaveDataSharedPreferences(
                                edtUser.editText.text.toString(),
                                edtPassword.editText.text.toString()
                            )
                        )
                        sendLogin(it.user)
                    }
                    is LoginStates.CallError -> {
                        sendLoginError(it.mesage)
                    }
                    is LoginStates.UserSucess -> {
                        userOk()
                    }
                    is LoginStates.UserError -> {
                        userNok()
                    }
                    is LoginStates.PasswordSucess -> {
                        passwordOk()
                    }
                    is LoginStates.PasswordError -> {
                        passwordNok()
                    }

                    is LoginStates.EnableButton -> {
                        enableButtonLogin()
                    }

                    is LoginStates.DisableButton -> {
                        disableButtonLogin()
                    }

                    is LoginStates.SaveLoginSecurePreferences -> {
                        saveUserPassword(it.user, it.password)
                    }

                    is LoginStates.SharePreferencesEmpty -> {
                        securePreferencesEmpty()
                    }
                }
            }

        })
    }

    private fun securePreferencesEmpty() {
        viewModel.interpret(LoginInteractor.ValidateFieldUser(edtUser.editText))
        viewModel.interpret(LoginInteractor.ValidateFieldPassword(edtPassword.editText))
    }

    private fun populateEditText(user: String?, password: String?) {
        edtUser.editText.setText(user)
        edtPassword.editText.setText(password)
        viewModel.interpret(LoginInteractor.ValidateFieldUser(edtUser.editText))
        viewModel.interpret(LoginInteractor.ValidateFieldPassword(edtPassword.editText))
    }

    private fun userOk() {
        edtUser.hideError()
        viewModel.interpret(LoginInteractor.EnableButtonLogin)
    }

    private fun userNok() {
        edtUser.showError()
        disableButtonLogin()
    }

    private fun passwordOk() {
        edtPassword.hideError()
        viewModel.interpret(LoginInteractor.EnableButtonLogin)
    }

    private fun passwordNok() {
        edtPassword.showError()
        disableButtonLogin()
    }

    private fun disableButtonLogin() {
        btnLogin.isEnabled = false
    }

    private fun enableButtonLogin() {
        btnLogin.isEnabled = true
    }

    private fun sendLoginError(mesage: String) {
        Toast.makeText(this, mesage, Toast.LENGTH_LONG).show()
    }

    private fun sendLogin(user: UserAccount?) {
        val intent = HomeActivity.newIntent(this, user)
        startActivity(intent)
    }

    private fun saveUserPassword(user: String?, password: String?) {
        val preferences =
            SecurePreferences(this, PREFERENCE_NAME, SECURE_KEY, true)
        preferences.put(USER_SECURE_PREFERENCES, user)
        preferences.put(PASSWORD_SECURE_PREFERENCES, password)
    }

}