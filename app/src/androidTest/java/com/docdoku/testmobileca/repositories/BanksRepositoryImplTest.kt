package com.docdoku.testmobileca.repositories

import androidx.test.core.app.ApplicationProvider
import com.docdoku.testmobileca.domain.Bank
import com.docdoku.testmobileca.utils.AssetsUtils
import com.docdoku.testmobileca.utils.ResultStatus
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

/**
 * Created by Arno ABOMO on Sept/06/2023
 */

/*
  * This class is used to test the BanksRepositoryImpl class
 */

@ExperimentalCoroutinesApi
class BanksRepositoryImplTest{

    private lateinit var repository: BanksRepositoryImpl

    private lateinit var fakeDataSource: com.docdoku.testmobileca.api.FakeDataSource

    // Before each test, we initialize the repository and the fakeDataSource
    @Before
    fun setUp() {

        val json = AssetsUtils.getJsonDataFromAsset(ApplicationProvider.getApplicationContext())

        val banks: ArrayList<Bank> = Gson().fromJson(json, Array<Bank>::class.java).toList() as ArrayList<Bank>

        fakeDataSource = com.docdoku.testmobileca.api.FakeDataSource(
            banks
        )
        repository = BanksRepositoryImpl(fakeDataSource)
    }

    // test to get all banks
    @Test
    fun test_that_we_get_all_banks() = runTest{

        val banks = repository.getBanks()

        assertThat(banks).isInstanceOf(ResultStatus.Success::class.java)

        assertThat((banks as ResultStatus.Success).data.size).isEqualTo(4)
    }

}