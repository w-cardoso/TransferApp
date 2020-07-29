package wevs.com.br.transferapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StatementResponse(
    val statementList: MutableList<Statement>,
    val error: Error
) : Parcelable