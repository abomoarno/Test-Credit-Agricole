package com.docdoku.testmobileca.api

import com.docdoku.testmobileca.api.datasources.BanksDataSource
import com.docdoku.testmobileca.domain.Bank

/**
 * Created by Arno ABOMO on Sept/06/2023
 */

/*
 * We create a fake data source to test the BankRepository class witch depends on the data source.
 */

class FakeDataSource(
    private val banks: MutableList<Bank>? = null
): BanksDataSource {
    override suspend fun getBanks(): List<Bank> {
        return banks?.toList() ?: emptyList()
    }
}