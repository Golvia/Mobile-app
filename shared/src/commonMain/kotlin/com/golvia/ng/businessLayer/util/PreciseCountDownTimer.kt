package com.golvia.ng.businessLayer.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * davidsunday
 */

abstract class PreciseCountDownTimer(
    private val totalTime: Long,
    private val interval: Long
) {
    private var job: Job? = null

    abstract fun onTick(millisRemaining: Long)
    abstract fun onFinish()

    fun start() {
        job = CoroutineScope(Dispatchers.Default).launch {
            var millisRemaining = totalTime
            while (millisRemaining > 0) {
                onTick(millisRemaining)
                delay(interval)
                millisRemaining -= interval
            }
            onFinish()
        }
    }

    fun stop() {
        job?.cancel()
    }
}