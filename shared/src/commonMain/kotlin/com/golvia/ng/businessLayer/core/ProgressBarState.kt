package com.golvia.ng.businessLayer.core

sealed class ProgressBarState{

   data object ButtonLoading: ProgressBarState()

   data object ScreenLoading: ProgressBarState()

   data object FullScreenLoading: ProgressBarState()

   data object LoadingWithLogo: ProgressBarState()

   data object Idle: ProgressBarState()

}

