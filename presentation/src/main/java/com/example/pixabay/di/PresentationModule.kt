package com.example.pixabay.di

import android.content.Context
import android.content.SharedPreferences
import com.example.pixabay.util.AppSettingsSharedPreference
import com.example.pixabay.util.PresentationConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PresentationModule {

    @Provides
    @AppSettingsSharedPreference
    fun provideAppSettingsSharedPreference(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            PresentationConstants.SHARED_PREFERENCE_APP_SETTINGS,
            Context.MODE_PRIVATE
        )
    }
}