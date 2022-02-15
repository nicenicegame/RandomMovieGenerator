package com.rmg.randommoviegenerator.di

import android.content.Context
import com.rmg.randommoviegenerator.data.local.AppDatabase
import com.rmg.randommoviegenerator.data.local.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.getInstance(context)

    @Provides
    fun provideMovieDao(appDatabase: AppDatabase): MovieDao = appDatabase.getMovieDao()
}