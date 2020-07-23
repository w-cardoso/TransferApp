package wevs.com.br.transferapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

@Parcelize
class UserAccount(
    val userId: Int,
    val name: String?,
    val bankAccount: String?,
    val agency: String?,
    val balance: BigDecimal
) : Parcelable

