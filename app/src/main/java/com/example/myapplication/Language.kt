package com.example.myapplication

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Language(
    val name: String,
    val description: String,
    val detail: String,
    val photo: Int
) : Parcelable
