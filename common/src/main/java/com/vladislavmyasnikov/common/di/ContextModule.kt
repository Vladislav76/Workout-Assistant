package com.vladislavmyasnikov.common.di

import android.content.Context
import com.vladislavmyasnikov.common.interfaces.ContextHolder
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ContextModule {

    @Provides
    fun provideContext(holder: ContextHolder): Context = holder.getContext()

    @Provides
    @Named("application_context")
    fun provideApplicationContext(holder: ContextHolder): Context = holder.getContext().applicationContext
}