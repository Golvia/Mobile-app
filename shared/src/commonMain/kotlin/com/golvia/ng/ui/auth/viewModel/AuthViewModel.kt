package com.golvia.ng.ui.auth.viewModel

import androidx.lifecycle.ViewModel
import com.golvia.ng.businessLayer.interactors.LoginInteractor
import com.golvia.ng.businessLayer.util.PreciseCountDownTimer
import com.golvia.ng.presentation.component.getCountDownTimer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * davidsunday
 */

class AuthViewModel(
    private val loginInteractor: LoginInteractor,
): ViewModel() {
    private var countDownTimer: PreciseCountDownTimer? = null
    private val _remainingTime = MutableStateFlow(String())
    val remainingTime: StateFlow<String> = _remainingTime

    private val _isCountdownRunning = MutableStateFlow(true)
    val isCountdownRunning: StateFlow<Boolean> = _isCountdownRunning

    private val _shouldStartCountdown = MutableStateFlow(true)
    val shouldStartCountdown: StateFlow<Boolean> = _shouldStartCountdown

    val userName = MutableStateFlow(String())
    val email = MutableStateFlow(String())


    fun startCountdown(time: Long) {
        countDownTimer?.stop()
        countDownTimer =
            getCountDownTimer(
                timeValue = time,
                onTick = { formattedTime -> _remainingTime.value = formattedTime },
                onFinish = { _isCountdownRunning.value = false })
        countDownTimer?.start()
        _isCountdownRunning.value = true
        _shouldStartCountdown.value = false
    }
}