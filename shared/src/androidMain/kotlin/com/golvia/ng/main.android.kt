package com.golvia.ng

import android.app.Application
import androidx.compose.runtime.Composable
import com.golvia.ng.presentation.App

@Composable
fun MainView(application: Application) {

    App(application)
}