package com.golvia.ng.businessLayer.interactors


import com.golvia.ng.businessLayer.constants.DataStoreKeys
import com.golvia.ng.businessLayer.core.AppDataStore
import com.golvia.ng.businessLayer.core.DataState
import com.golvia.ng.businessLayer.core.ProgressBarState
import com.golvia.ng.businessLayer.util.handleUseCaseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CheckTokenInteractor(
    private val appDataStoreManager: AppDataStore,
) {


    fun execute(): Flow<DataState<Boolean>> = flow {

        try {

            emit(DataState.Loading(progressBarState = ProgressBarState.ButtonLoading))


            val token = appDataStoreManager.readValue(DataStoreKeys.TOKEN) ?: ""




            val isTokenValid = token.isNotEmpty()


            emit(DataState.Data(isTokenValid))

        } catch (e: Exception) {
            e.printStackTrace()
            emit(handleUseCaseException(e))

        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }


    }


}