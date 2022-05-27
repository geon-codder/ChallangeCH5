package com.geco.challangech5

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    @Named("ModeOld")
    fun provideModeOld()= "Anda sedang berada di tampilan lawas"

    @Singleton
    @Provides
    @Named("ModeNew")
    fun provideModeNew()= "Anda sedang berada di tampilan terbaru"
}