package com.adaptive.testmobileca.di

import com.adaptive.testmobileca.repositories.BanksRepository
import com.adaptive.testmobileca.repositories.BanksRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Arno ABOMO on 09/06/2023
 */

/*
 * This class is used to provide the Repository using Hilt dependency injection
 */

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

    /**
     * This function is used to provide a BanksRepository instance
     *
     * @param repository: The Repository to provide
     * @return The Repository provided
     */
    @Binds
    fun bindBanksRepository(
        repository: BanksRepositoryImpl
    ): BanksRepository
}