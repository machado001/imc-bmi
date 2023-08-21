package com.machado001.imc.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfo(
    var weight: Float,
    val height: Float,
) : Parcelable

