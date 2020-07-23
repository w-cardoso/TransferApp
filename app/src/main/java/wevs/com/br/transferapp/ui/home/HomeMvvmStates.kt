package wevs.com.br.transferapp.ui.home

import android.content.Intent
import wevs.com.br.transferapp.model.StatementResponse
import wevs.com.br.transferapp.model.UserAccount

sealed class HomeStates {
    data class IntentOk(val user: UserAccount?) : HomeStates()
    object IntentNOk : HomeStates()
    data class CallSucess(val listStatement: StatementResponse) : HomeStates()
    object CallError : HomeStates()
}

sealed class HomeInteractor {
    data class ValidateIntent(val intent: Intent?) : HomeInteractor()
    data class GetStatementList(val idUser: Int?) : HomeInteractor()
}