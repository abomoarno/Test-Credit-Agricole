package com.docdoku.testmobileca.domain

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Created by Arno ABOMO on Sept/06/2023
 */

/**
 * This data class contains all the information about a bank account
 *
 * @property order
 * @property id
 * @property holder
 * @property role
 * @property contractNumber
 * @property label
 * @property productCode
 * @property balance
 * @property operations
 */

@Parcelize
data class BankAccount(

    @SerializedName("order")
    @Expose
    val order: Int,

    @SerializedName("id")
    @Expose
    val id: String,

    @SerializedName("holder")
    @Expose
    val holder: String,

    @SerializedName("role")
    @Expose
    val role: Int,

    @SerializedName("contract_number")
    @Expose
    val contractNumber: String,

    @SerializedName("label")
    @Expose
    val label: String,

    @SerializedName("product_code")
    @Expose
    val productCode: String,

    @SerializedName("balance")
    @Expose
    val balance: Double,

    @SerializedName("operations")
    @Expose
    val operations: ArrayList<Operation>
) : Parcelable
