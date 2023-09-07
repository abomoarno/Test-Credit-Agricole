package com.adaptive.testmobileca.domaine

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Arno ABOMO on 09/06/2023
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