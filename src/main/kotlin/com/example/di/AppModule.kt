package com.example.di

import com.example.config.AppConfig
import com.example.features.user.data.UserRepositoryImpl
import com.example.features.user.domain.UserRepository
import org.koin.dsl.module

val appModule = module {
    single { AppConfig() }
    single<UserRepository> { UserRepositoryImpl() }
}