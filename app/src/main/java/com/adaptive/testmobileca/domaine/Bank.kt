package com.adaptive.testmobileca.domaine

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Arno ABOMO on 09/06/2023
 */

/**
 * Bank data class that contains all the information about a bank
 *
 * @property name
 * @property isCA
 * @property bankAccounts
 * @constructor Create empty Bank
 */

data class Bank(

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("isCA")
    @Expose
    val isCA: Int,

    @SerializedName("accounts")
    @Expose
    val bankAccounts: List<BankAccount>
)
