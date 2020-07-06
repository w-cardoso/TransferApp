package wevs.com.br.transferapp.ui.login

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import wevs.com.br.transferapp.R
import wevs.com.br.transferapp.model.UserAccount
import wevs.com.br.transferapp.ui.edittext.CustomEditText

class LoginActivity : AppCompatActivity() {
    private val viewModel by lazy { providerLoginViewModel(this) }

    private val edtUser by lazy { findViewById<CustomEditText>(R.id.login_edt_user) }
    private val edtPassword by lazy { findViewById<CustomEditText>(R.id.login_edt_password) }
    private val bntLogin by lazy { findViewById<Button>(R.id.login_btn) }

    override fun onStart() {
        super.onStart()
        viewModel.interpret(LoginInteractor.ValidateFieldUser(edtUser.editText))
        viewModel.interpret(LoginInteractor.ValidateFieldPassword(edtPassword.editText))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //viewModel.interpret(LoginInteractor.PostLogin(Login("sadada", "dsadasfasa")))
        viewModel.viewStates.observe(this, Observer { viewStates ->
            viewStates.let {
                when (it) {
                    is LoginStates.CallSucess -> {
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
                }
            }

        })
    }

    private fun userOk() {
        edtUser.hideError()
        viewModel.interpret(LoginInteractor.EnableButtonLogin)
    }

    private fun userNok() {
        edtUser.showError()
    }

    private fun passwordOk() {
        edtPassword.hideError()
        viewModel.interpret(LoginInteractor.EnableButtonLogin)
    }

    private fun passwordNok() {
        edtPassword.showError()
    }

    private fun disableButtonLogin() {
        bntLogin.isEnabled = false
    }

    private fun enableButtonLogin() {
        bntLogin.isEnabled = true
    }

    private fun sendLoginError(mesage: String) {

    }

    private fun sendLogin(user: UserAccount?) {
        Toast.makeText(this, user?.name, Toast.LENGTH_LONG).show()
    }
}