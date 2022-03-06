package com.moon.morningismiracle.di

import com.moon.data.repo.PlanRepositoryImpl
import com.moon.domain.repository.PlanRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun providePlanRepository(impl: PlanRepositoryImpl): PlanRepository
}