package com.docdoku.testmobileca.api

import com.docdoku.testmobileca.domain.Bank
import retrofit2.http.GET

/**
 * Created by Arno ABOMO on Sept/06/2023
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