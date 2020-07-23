package wevs.com.br.transferapp.model

data class StatementResponse(
    val statementList: MutableList<Statement>,
    val error: Error
)