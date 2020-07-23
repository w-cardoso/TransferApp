package wevs.com.br.transferapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal

@Parcelize
data class Statement(
    val date: String,
    var title: String,
    val value: BigDecimal,
    val desc: String
) : Parcelable