package wevs.com.br.transferapp.ui.login

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import wevs.com.br.transferapp.LoginInteractor
import wevs.com.br.transferapp.LoginStates
import wevs.com.br.transferapp.R
import wevs.com.br.transferapp.model.Login
import wevs.com.br.transferapp.model.UserAccount

class LoginActivity : AppCompatActivity() {
    private val viewModel by lazy { providerLoginViewModel(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.interpret(LoginInteractor.PostLogin(Login("sadada", "dsadasfasa")))
        viewModel.viewStates.observe(this, Observer { viewStates ->
            viewStates.let {
                when (it) {
                    is LoginStates.CallSucess -> {
                        sendLogin(it.user)
                    }
                    is LoginStates.CallError -> {
                        sendLoginError(it.mesage)
                    }
                }
            }

        })
    }

    private fun sendLoginError(mesage: String) {

    }

    private fun sendLogin(user: UserAccount?) {
        Toast.makeText(this, user?.name, Toast.LENGTH_LONG).show()
    }
}