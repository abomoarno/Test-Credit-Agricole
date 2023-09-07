package com.adaptive.testmobileca.api.dataSources

import com.adaptive.testmobileca.domaine.Bank

/**
 * Created by Arno ABOMO on 09/06/2023
 */

/*
 * This interface is used to define the methods that will be used to get data from the server.
 */

interface BanksDataSource {
    suspend fun getBanks(): List<Bank>
}