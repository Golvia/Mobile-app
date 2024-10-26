package com.golvia.ng.presentation.component

import com.golvia.ng.businessLayer.util.PreciseCountDownTimer

/**
 * davidsunday
 */

fun getCountDownTimer(
    timeValue: Long,
    onTick: (String) -> Unit,
    onFinish: () -> Unit,
    onProgress: ((Float) -> Unit)? = null
): PreciseCountDownTimer {
    return object : PreciseCountDownTimer(timeValue, 1_000L) {
        override fun onTick(millisRemaining: Long) {
            val minutes = (millisRemaining / 60000) % 60
            val seconds = (millisRemaining / 1000) % 60

            val formattedTime = "${if (minutes < 10) "0$minutes" else "$minutes"}:" +
                    if (seconds < 10) "0$seconds" else "$seconds"

            onTick(formattedTime)
            onProgress?.invoke((timeValue - millisRemaining).toFloat() / timeValue)
        }

        override fun onFinish() {
            onTick("00:00")
            onFinish()
        }
    }
}