package wevs.com.br.transferapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Error(var message: String) : Parcelable