package com.docdoku.testmobileca.domain

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Created by Arno ABOMO on Sept/06/2023
 */

/**
 * This data class contains all the information about an operation in a bank account
 *
 * @property id
 * @property title
 * @property amount
 * @property category
 * @property date
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
