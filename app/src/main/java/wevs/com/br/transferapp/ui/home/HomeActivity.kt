package wevs.com.br.transferapp.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import wevs.com.br.transferapp.R
import wevs.com.br.transferapp.model.StatementResponse
import wevs.com.br.transferapp.model.UserAccount
import wevs.com.br.transferapp.ui.home.adapter.HomeAdapter
import wevs.com.br.transferapp.utils.EMPTY
import wevs.com.br.transferapp.utils.USER_ACCOUNT_KEY
import wevs.com.br.transferapp.utils.formatToBrazilianCurrency

class HomeActivity : AppCompatActivity() {
    private val viewModel by lazy { providerHomeViewModel(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        findViewById<ImageView>(R.id.home_logout).setOnClickListener { activitiesEnds() }

        viewModel.viewStates.observe(this, Observer { viewStates ->
            viewStates.let {
                when (it) {
                    is HomeStates.IntentOk -> {
                        populateViews(it.user)
                    }

                    is HomeStates.IntentNOk -> {
                        activitiesEnds()
                    }

                    is HomeStates.CallSucess -> {
                        buildListRecycler(it.listStatement)
                    }

                    is HomeStates.CallError -> {
                        activitiesEnds()
                    }
                }
            }
        })
    }

    private fun activitiesEnds() {
        finish()
    }

    override fun onStart() {
        super.onStart()
        viewModel.interpret(HomeInteractor.ValidateIntent(intent))
    }

    private fun buildListRecycler(listStatement: StatementResponse) {
        val adapter = HomeAdapter(listStatement.statementList)
        findViewById<RecyclerView>(R.id.home_rcv).adapter = adapter
    }

    private fun populateViews(user: UserAccount?) {
        findViewById<TextView>(R.id.home_txt_name).text = user?.name

        findViewById<TextView>(R.id.home_txt_account).text =
            getString(
                R.string.home_agency_account,
                user?.bankAccount.toString(), user?.agency
            )

        findViewById<TextView>(R.id.home_txt_balance).text =
            user?.balance?.formatToBrazilianCurrency() ?: EMPTY

        viewModel.interpret(HomeInteractor.GetStatementList(user?.userId))
    }

    companion object {
        fun newIntent(context: Context, userAccount: UserAccount?): Intent {
            val intent = Intent(context, HomeActivity::class.java)
            intent.putExtra(USER_ACCOUNT_KEY, userAccount)
            return intent
        }
    }
}