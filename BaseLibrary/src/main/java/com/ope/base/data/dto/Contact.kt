package com.ope.base.data.dto

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.ArrayList

@Parcelize
@SuppressLint("ParcelCreator")
data class Contact (var id:String,var name:String?, var phones: ArrayList<String>, var photo:Bitmap?,var isSelected : Boolean = false) : Parcelable