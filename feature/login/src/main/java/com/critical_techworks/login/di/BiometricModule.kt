package com.critical_techworks.login.di

import com.critical_techworks.login.presentation.ui.util.BiometricUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class BiometricModule {

    @Singleton
    @Provides
    fun provideBiometricUtil(
    ): BiometricUtil {
        return BiometricUtil()
    }
}