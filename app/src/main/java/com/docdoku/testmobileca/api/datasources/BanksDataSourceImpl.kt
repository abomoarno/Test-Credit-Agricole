package com.docdoku.testmobileca.api.datasources

import com.docdoku.testmobileca.api.ApiService
import com.docdoku.testmobileca.domain.Bank
import javax.inject.Inject

/**
 * Created by Arno ABOMO on Sept/06/2023
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