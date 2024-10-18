package com.golvia.ng.ui.auth.viewModel

import com.golvia.ng.business.core.NetworkState
import com.golvia.ng.business.core.ProgressBarState
import com.golvia.ng.business.core.Queue
import com.golvia.ng.business.core.UIComponent

data class LoginState(
    val nameRegister: String = "",
    val usernameLogin: String = "",
    val passwordLogin: String = "",

    val isTokenValid: Boolean = false,
    val navigateToMain: Boolean = false,

    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
    val errorQueue: Queue<UIComponent> = Queue(mutableListOf()),
)
