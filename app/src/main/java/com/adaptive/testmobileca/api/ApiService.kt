package com.adaptive.testmobileca.api

import com.adaptive.testmobileca.domaine.Bank
import retrofit2.http.GET

/**
 * Created by Arno ABOMO on 09/06/2023
 */

/*
 * This interface is used to define the API endpoints.
 */

interface ApiService {

    /*
     * This function is used to get the list of banks from the API.
     */
    @GET("banks.json")
    suspend fun getBanks(): List<Bank>

}