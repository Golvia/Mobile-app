package com.golvia.ng.presentation.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.painter.Painter
import org.jetbrains.compose.resources.StringResource

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

interface TextState {
    val errorState: Boolean
    val errorMessage: String
    val errorResourceId: StringResource?
}

class TextCorrect : TextState {
    override val errorState: Boolean = false
    override val errorMessage: String = ""
    override val errorResourceId: StringResource? = null
}

class TextMissing(
    override val errorMessage: String = "",
    override val errorResourceId: StringResource? = null
) : TextState {
    override val errorState: Boolean = true
}

class TextIncorrect(
    override val errorMessage: String = "",
    override val errorResourceId: StringResource? = null
) : TextState {
    override val errorState: Boolean = true
}