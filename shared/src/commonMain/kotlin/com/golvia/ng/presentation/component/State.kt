package com.golvia.ng.presentation.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.setValue

/**
 * davidsunday
 */

class TextFieldState(stringValue: String) {
    var value: String by mutableStateOf(stringValue)

    val TextFieldStateSaver = run {
        val valueKey = "Value"
        mapSaver(
            save = { mapOf(valueKey to it.value) },
            restore = { TextFieldState(it[valueKey] as String) }
        )
    }
}

class UIComponentAction<ViewComponent, ViewAction>(
    val isVisible: Boolean = true,
    val viewComponent: ViewComponent,
    val viewAction: ViewAction
)