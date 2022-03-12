package com.moon.morningismiracle.di

import com.moon.data.repo.PlanRepositoryImpl
import com.moon.data.repo.StatisticsRepositoryImpl
import com.moon.data.repo.TagRepositoryImpl
import com.moon.domain.repository.PlanRepository
import com.moon.domain.repository.StatisticsRepository
import com.moon.domain.repository.TagRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun providePlanRepository(impl: PlanRepositoryImpl): PlanRepository

    @Binds
    abstract fun provideTagRepository(impl: TagRepositoryImpl): TagRepository

    @Binds
    abstract fun provideStatisticsRepository(impl: StatisticsRepositoryImpl): StatisticsRepository
}