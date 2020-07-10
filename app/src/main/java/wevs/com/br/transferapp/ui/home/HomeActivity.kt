package wevs.com.br.transferapp.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import wevs.com.br.transferapp.R
import wevs.com.br.transferapp.model.UserAccount

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    companion object {
        fun newIntent(context: Context, userAccount: UserAccount?): Intent {
            val intent = Intent(context, HomeActivity::class.java)
            intent.putExtra("USER_ACCOUNT", userAccount)
            return intent
        }
    }
}