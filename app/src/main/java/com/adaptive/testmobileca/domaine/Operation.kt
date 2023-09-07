package com.adaptive.testmobileca.domaine

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Created by Arno ABOMO on 09/06/2023
 */

@Parcelize
data class Operation(

    @SerializedName("id")
    @Expose
    val id: String,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("amount")
    @Expose
    val amount: String,

    @SerializedName("category")
    @Expose
    val category: String,

    @SerializedName("date")
    @Expose
    val date: String,
) : Parcelable
