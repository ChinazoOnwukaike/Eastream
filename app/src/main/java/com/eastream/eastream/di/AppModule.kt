package com.eastream.eastream.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

//tells hilt when to make database so that it doesn't run everytime the app runs
@Module
@InstallIn(SingletonComponent::class) //only happens one time and won't be recreated but reused
object AppModule {
}