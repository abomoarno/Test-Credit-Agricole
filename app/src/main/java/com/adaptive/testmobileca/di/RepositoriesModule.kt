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

    // Binds BanksRepository
    @Binds
    fun bindBanksRepository(
        repository: BanksRepositoryImpl
    ): BanksRepository
}