package com.adaptive.testmobileca.api

import com.adaptive.testmobileca.api.dataSources.BanksDataSource
import com.adaptive.testmobileca.domaine.Bank

/**
 * Created by Arno ABOMO on 09/06/2023
 */

class FakeDataSource(
    private val banks: MutableList<Bank>? = null
): BanksDataSource {
    override suspend fun getBanks(): List<Bank> {
        return banks?.toList() ?: emptyList()
    }
}