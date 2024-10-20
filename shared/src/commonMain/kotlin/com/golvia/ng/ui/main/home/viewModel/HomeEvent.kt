package com.golvia.ng.ui.main.home.viewModel

import com.golvia.ng.businessLayer.core.NetworkState
import com.golvia.ng.businessLayer.core.UIComponent

sealed class HomeEvent {


   data object OnRemoveHeadFromQueue : HomeEvent()

    data class Error(
        val uiComponent: UIComponent
    ) : HomeEvent()

   data object OnRetryNetwork : HomeEvent()
    data class OnUpdateNetworkState(
        val networkState: NetworkState
    ) : HomeEvent()

    data class Like(val id: Int) : HomeEvent()

}
