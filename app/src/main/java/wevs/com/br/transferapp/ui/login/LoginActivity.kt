package wevs.com.br.transferapp.ui.login

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import wevs.com.br.transferapp.R
import wevs.com.br.transferapp.model.Login
import wevs.com.br.transferapp.model.UserAccount

class LoginActivity : AppCompatActivity() {
    private val viewModel by lazy { providerLoginViewModel(this) }

    private val edtUser by lazy { findViewById<EditText>(R.id.login_edt_user) }
    private val edtPassword by lazy { findViewById<EditText>(R.id.login_edt_password) }
    private val bntLogin by lazy { findViewById<Button>(R.id.login_btn) }

    override fun onStart() {
        super.onStart()
        viewModel.interpret(LoginInteractor.ValidateFieldUser(edtUser))
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

                    }
                    is LoginStates.PasswordSucess -> {

                    }
                    is LoginStates.PasswordError -> {

                    }
                }
            }

        })
    }

    private fun userOk() {
        Toast.makeText(this, "PARECE QUE FUNCIONA", Toast.LENGTH_LONG).show()
    }

    private fun sendLoginError(mesage: String) {

    }

    private fun sendLogin(user: UserAccount?) {
        Toast.makeText(this, user?.name, Toast.LENGTH_LONG).show()
    }
}