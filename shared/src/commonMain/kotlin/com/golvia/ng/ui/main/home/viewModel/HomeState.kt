package com.golvia.ng.ui.main.home.viewModel

import com.golvia.ng.business.core.NetworkState
import com.golvia.ng.business.core.ProgressBarState
import com.golvia.ng.business.core.Queue
import com.golvia.ng.business.core.UIComponent
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class HomeState(
    val time: LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.UTC),
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val networkState: NetworkState = NetworkState.Good,
    val errorQueue: Queue<UIComponent> = Queue(mutableListOf()),
)
