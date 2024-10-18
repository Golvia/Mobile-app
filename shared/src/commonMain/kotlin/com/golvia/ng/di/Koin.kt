package com.golvia.ng.di


import com.golvia.ng.common.Context
import kotlinx.serialization.json.Json
import org.koin.dsl.module


fun appModule(context: Context) = module {
    single { Json { isLenient = true; ignoreUnknownKeys = true } }

}