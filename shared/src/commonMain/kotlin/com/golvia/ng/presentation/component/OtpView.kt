package com.golvia.ng.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.golvia.ng.presentation.theme.LatoTypography
import com.golvia.ng.presentation.theme.red_error
import org.jetbrains.compose.resources.stringResource

/**
 * davidsunday
 */

@Composable
fun CustomPinCodeField(
    label: String,
    nFields: Int,
    nFiller: Int = 0,
    pinStates: List<TextFieldState>,
    focusManager: FocusManager,
    textState: TextState,
    checker: (List<TextFieldState>) -> Unit,
    clearPinError: () -> Unit = {}
) {
    val listIsFocused = remember {
        MutableList(nFields) { mutableStateOf(false) }
    }
    val wasFocused = remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    Text(
        modifier = Modifier.padding(bottom = 8.dp, start = 6.dp), text = label, style = TextStyle(
            fontSize = 14.sp,
            lineHeight = 22.sp,
            fontFamily = LatoTypography().bodyMedium.fontFamily,
            fontWeight = FontWeight(400),
            color = Color.Gray,
        )
    )
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        for (i in 0 until nFields) {
            CustomRegularOutlinedTextFieldPin(
                fieldState = pinStates[i],
                textAlign = TextAlign.Center,
                isPassword = true,
                modifier = Modifier
                    .onKeyEvent {
                        return@onKeyEvent numKeyEvent(
                            keyEvent = it,
                            i = i,
                            pinStates = pinStates,
                            focusManager = focusManager,
                            nFields = nFields,
                            checker = checker
                        )
                    }
                    .onFocusChanged {
                        numOnFocusChanged(
                            wasFocused = wasFocused,
                            listIsFocused = listIsFocused,
                            i = i,
                            focusState = it,
                            pinStates = pinStates,
                            checker = checker,
                            clearPinError = clearPinError
                        )
                    },
                onValueChange = {},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = if (i != (nFields - 1)) {
                        ImeAction.Next
                    } else {
                        if (pinStates[i].value.isNotEmpty()) {
                            ImeAction.Done
                        } else {
                            ImeAction.None
                        }
                    },
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        if (i < (nFields - 1)) {
                            focusManager.moveFocus(FocusDirection.Right)
                        }
                    },
                    onDone = {
                        keyboardController?.hide()
                    },
                ),
                decorationBox = { innerTextField ->
                    CustomRegularDecorationBox(
                        value = pinStates[i].value,
                        placeholder = "",
                        errorState = textState.errorState,
                        innerTextField = innerTextField
                    )

                })
        }
        InsertFillers(
            nFiller = nFiller
        )
    }
    ErrorMessage(
        modifier = Modifier.padding(bottom = 8.dp, top = 6.dp, start = 6.dp),
        textState = textState
    )
}

private fun numOnFocusChanged(
    wasFocused: MutableState<Boolean>,
    listIsFocused: MutableList<MutableState<Boolean>>,
    i: Int,
    focusState: FocusState,
    pinStates: List<TextFieldState>,
    checker: (List<TextFieldState>) -> Unit,
    clearPinError: () -> Unit
) {
    if (!wasFocused.value && focusState.isFocused) {
        wasFocused.value = true
    }
    if (wasFocused.value) {
        listIsFocused[i].value = focusState.isFocused
        if (!focusState.isFocused) {
            var isOneFocused = false
            listIsFocused.forEach { focus ->
                if (focus.value) {
                    isOneFocused = true
                    return@forEach
                }
            }
            if (!isOneFocused) {
                checker.invoke(pinStates)
            }
        } else {
            clearPinError.invoke()
        }
    }
}

private fun numKeyEvent(
    keyEvent: KeyEvent,
    i: Int,
    pinStates: List<TextFieldState>,
    focusManager: FocusManager,
    nFields: Int,
    checker: (List<TextFieldState>) -> Unit,
): Boolean {
    when (keyEvent.key) {
        Key.Backspace -> {
            if (pinStates[i].value.isEmpty() && i > 0) {
                pinStates[i - 1].value = ""
            } else {
                pinStates[i].value = ""
            }
            if (i > 0) focusManager.moveFocus(FocusDirection.Left)
            if (i == 0) checker.invoke(pinStates)
            return true
        }

        Key.Zero -> setPinState(pinStates, focusManager, "0", i, nFields, checker)
        Key.One -> setPinState(pinStates, focusManager, "1", i, nFields, checker)
        Key.Two -> setPinState(pinStates, focusManager, "2", i, nFields, checker)
        Key.Three -> setPinState(pinStates, focusManager, "3", i, nFields, checker)
        Key.Four -> setPinState(pinStates, focusManager, "4", i, nFields, checker)
        Key.Five -> setPinState(pinStates, focusManager, "5", i, nFields, checker)
        Key.Six -> setPinState(pinStates, focusManager, "6", i, nFields, checker)
        Key.Seven -> setPinState(pinStates, focusManager, "7", i, nFields, checker)
        Key.Eight -> setPinState(pinStates, focusManager, "8", i, nFields, checker)
        Key.Nine -> setPinState(pinStates, focusManager, "9", i, nFields, checker)

        else -> return false
    }
    return true
}

@Composable
private fun InsertFillers(nFiller: Int) {
    if (nFiller > 0) {
        for (i in 0 until nFiller) {
            Box(modifier = Modifier.size(48.dp))
        }
    }
}

@Composable
private fun ErrorMessage(modifier: Modifier, textState: TextState) {
    Text(
        modifier = modifier,
        text = when (textState) {
            is TextMissing, is TextIncorrect -> {
                textState.errorResourceId
                    ?.let { id -> stringResource(id) }
                    ?: textState.errorMessage
            }
            else -> {
                String()
            }
        },
        style = TextStyle(
            fontSize = 12.sp,
            lineHeight = 16.sp,
            fontFamily = LatoTypography().bodyMedium.fontFamily,
            fontWeight = FontWeight(400),
            color = red_error,
        )
    )

}

private fun setPinState(
    pinStates: List<TextFieldState>,
    focusManager: FocusManager,
    value: String,
    index: Int,
    nFields: Int,
    checker: (List<TextFieldState>) -> Unit
) {
    if (pinStates[index].value.isNotEmpty()) {
        //There is already a number there
        if (index + 1 <= nFields - 1) {
            focusManager.moveFocus(FocusDirection.Right)
            setPinState(
                pinStates = pinStates,
                focusManager = focusManager,
                value = value,
                index = index + 1,
                nFields = nFields,
                checker = checker
            )
        }
    } else {
        //Field is empty
        pinStates[index].value = value
        if (index + 1 <= nFields - 1) {
            focusManager.moveFocus(FocusDirection.Right)
        }
    }
    val pinStr = pinStates.joinToString(String()) { state -> state.value }
    if (pinStr.length == pinStates.size) checker.invoke(pinStates)
}