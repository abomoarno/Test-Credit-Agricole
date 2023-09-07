package com.docdoku.testmobileca.di

import com.docdoku.testmobileca.api.datasources.BanksDataSource
import com.docdoku.testmobileca.api.datasources.BanksDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Arno ABOMO on Sept/06/2023
 */

/*
 * This class is used to provide the DataSource using Hilt dependency injection
 */

@Module
@InstallIn(SingletonComponent::class)
interface DataSourcesModule {

    // Binds RecipesDataSource
    @Binds
    fun bindBanksDataSource(
        dataSource: BanksDataSourceImpl
    ): BanksDataSource

}