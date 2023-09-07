package com.docdoku.testmobileca.api.datasources

import com.docdoku.testmobileca.domain.Bank

/**
 * Created by Arno ABOMO on Sept/06/2023
 */

/*
 * This interface is used to define the methods that will be used to get data from the server.
 */

interface BanksDataSource {

    /*
     * This method is used to get the list of banks from the server.
     */
    suspend fun getBanks(): List<Bank>
}