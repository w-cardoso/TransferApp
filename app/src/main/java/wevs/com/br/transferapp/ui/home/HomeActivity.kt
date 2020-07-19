package wevs.com.br.transferapp.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import wevs.com.br.transferapp.R
import wevs.com.br.transferapp.model.UserAccount
import wevs.com.br.transferapp.utils.USER_ACCOUNT_KEY

class HomeActivity : AppCompatActivity() {
    private val viewModel by lazy { providerHomeViewModel(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        viewModel.viewStates.observe(this, Observer { viewStates ->
            viewStates.let {
                when (it) {
                    is HomeStates.IntentOk -> {
                        populateViews(it.user)
                    }

                    is HomeStates.IntentNOk -> {

                    }
                }
            }
        })
    }

    private fun populateViews(user: UserAccount?) {
        findViewById<TextView>(R.id.home_txt_name).text = user?.name
        findViewById<TextView>(R.id.home_txt_account).text =
            "${user?.bankAccount.toString()} / ${user?.agency}"
        findViewById<TextView>(R.id.home_txt_balance).text = user?.balance.toString()
    }

    override fun onStart() {
        super.onStart()
        viewModel.interpret(HomeInteractor.ValidateIntent(intent))
    }

    companion object {
        fun newIntent(context: Context, userAccount: UserAccount?): Intent {
            val intent = Intent(context, HomeActivity::class.java)
            intent.putExtra(USER_ACCOUNT_KEY, userAccount)
            return intent
        }
    }
}