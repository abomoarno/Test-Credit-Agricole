package com.adaptive.testmobileca.api.dataSources

import com.adaptive.testmobileca.api.ApiService
import com.adaptive.testmobileca.domaine.Bank
import javax.inject.Inject

/**
 * Created by Arno ABOMO on 09/06/2023
 */

/*
 * This class provides the implementation of the methods defined in the BanksDataSource interface.
 * Here we use the ApiService to get data from the server.
 */

class BanksDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : BanksDataSource {

    /*
     * This method is used to get the list of banks from the server.
     */
    override suspend fun getBanks(): List<Bank> {
        return apiService.getBanks()
    }
}