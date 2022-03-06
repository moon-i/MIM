package com.moon.data.room.di

import android.content.Context
import androidx.room.Room
import com.moon.data.room.database.MIMRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun provideMIMRoomDatabase(
        @ApplicationContext context: Context,
        scope: CoroutineScope
    ): MIMRoomDatabase = MIMRoomDatabase.getDatabase(context, scope)
}