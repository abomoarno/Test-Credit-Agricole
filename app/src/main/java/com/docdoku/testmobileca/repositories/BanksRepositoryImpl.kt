package com.docdoku.testmobileca.repositories

import com.docdoku.testmobileca.api.datasources.BanksDataSource
import com.docdoku.testmobileca.domain.Bank
import com.docdoku.testmobileca.utils.ResultStatus
import javax.inject.Inject

/**
 * Created by Arno ABOMO on Sept/06/2023
 */

/*
 * This class provides the actual implementation of the methods defined in the BanksRepository interface.
 */

class BanksRepositoryImpl @Inject constructor(
    private val banksDataSource: BanksDataSource
): BanksRepository {


    /*
     * This method is used to get the list of banks.
     * It returns a ResultStatus object.
     * It contains the list of banks if the request is successful.
     * Otherwise, it returns an error.
     */
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