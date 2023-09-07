package com.adaptive.testmobileca.di

import com.adaptive.testmobileca.api.dataSources.BanksDataSource
import com.adaptive.testmobileca.api.dataSources.BanksDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Arno ABOMO on 09/06/2023
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