package com.adaptive.testmobileca.repositories

import com.adaptive.testmobileca.domaine.Bank
import com.adaptive.testmobileca.utils.ResultStatus

/**
 * Created by Arno ABOMO on 09/06/2023
 */

/*
 * This interface is used to define the methods that will be used to provide data to the ViewModel.
 * In our case, the data comes from the API.
 * But it can also come from a local database.
 */

interface BanksRepository {

    /*
     * This method is used to get the list of banks.
     * It returns a ResultStatus object.
     * It contains the list of banks if the request is successful.
     * Otherwise, it returns an error.
     */
    suspend fun getBanks(): ResultStatus<List<Bank>>
}