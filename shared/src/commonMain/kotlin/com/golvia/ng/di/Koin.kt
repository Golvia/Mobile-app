package com.golvia.ng.di


import androidx.lifecycle.viewmodel.compose.viewModel
import com.golvia.ng.businessLayer.core.AppDataStore
import com.golvia.ng.businessLayer.core.AppDataStoreManager
import com.golvia.ng.businessLayer.core.KtorHttpClient
import com.golvia.ng.businessLayer.interactors.CheckTokenInteractor
import com.golvia.ng.businessLayer.interactors.LoginInteractor
import com.golvia.ng.businessLayer.interactors.LogoutInteractor
import com.golvia.ng.businessLayer.service.AuthService
import com.golvia.ng.businessLayer.service.AuthServiceImpl
import com.golvia.ng.common.Context
import com.golvia.ng.presentation.token_manager.TokenManager
import com.golvia.ng.ui.auth.viewModel.AuthViewModel
import kotlinx.serialization.json.Json
import org.koin.dsl.module


fun appModule(context: Context) = module {
    single { Json { isLenient = true; ignoreUnknownKeys = true } }
    single {
        KtorHttpClient.httpClient(get())
    }
    single<AuthService> { AuthServiceImpl(get()) }
    single<AppDataStore> { AppDataStoreManager(context) }
    single { AuthViewModel(get())}
    single { LoginInteractor(get()) }
    single { TokenManager(get(), get()) }
    single { CheckTokenInteractor(get()) }
    single { LogoutInteractor(get()) }

}