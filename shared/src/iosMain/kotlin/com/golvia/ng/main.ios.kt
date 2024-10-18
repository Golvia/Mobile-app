package com.golvia.ng

import androidx.compose.ui.window.ComposeUIViewController
import com.golvia.ng.common.Context
import com.golvia.ng.presentation.App

fun mainViewController() = ComposeUIViewController { App(Context()) }