package com.example.tareasestudio.di

import android.content.Context
import androidx.room.Room
import com.example.tareasestudio.data.local.AppDatabase
import com.example.tareasestudio.data.local.TareaDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "tareas.db").build()

    @Provides
    fun provideTareaDao(database: AppDatabase): TareaDao = database.tareaDao()
}