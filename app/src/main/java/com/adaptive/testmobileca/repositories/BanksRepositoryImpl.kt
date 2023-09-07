package com.adaptive.testmobileca.repositories

import com.adaptive.testmobileca.api.dataSources.BanksDataSource
import com.adaptive.testmobileca.domaine.Bank
import com.adaptive.testmobileca.utils.ResultStatus
import javax.inject.Inject

/**
 * Created by Arno ABOMO on 09/06/2023
 */

/*
 * This class provides the actual implementation of the methods defined in the BanksRepository interface.
 */

class BanksRepositoryImpl @Inject constructor(
    private val banksDataSource: BanksDataSource
): BanksRepository {
    override suspend fun getBanks(): ResultStatus<List<Bank>> {
        return try {
            val banks = banksDataSource.getBanks()
            ResultStatus.Success(banks)
        } catch (e: Exception) {
            e.printStackTrace()
            ResultStatus.Error()
        }
    }
}