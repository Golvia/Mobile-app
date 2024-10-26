package com.golvia.ng.presentation.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.golvia.ng.businessLayer.domain.DropdownItem
import com.golvia.ng.presentation.theme.LatoTypography
import golvia.shared.generated.resources.Res
import golvia.shared.generated.resources.bookmark
import golvia.shared.generated.resources.close
import golvia.shared.generated.resources.ic_network
import golvia.shared.generated.resources.search
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

/**
 * davidsunday
 */

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SelectModal(
    title: String,
    searchText: String = "",
    items: List<DropdownItem>,
    onItemSelected: (String, String) -> Unit,
    onDismiss: () -> Unit,
    emptyIcon: Painter? = null,
    emptyTitle: String = String(),
    emptyMessage: String = String(),
    selectedItem: String = String(),
    showSearch: Boolean = true,
    isFromProduct: Boolean = false
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    val searchState = remember { TextFieldState(String()) }
    val performSearch = remember { TextFieldState(String()) }
    val isFocused = remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    ModalBottomSheet(
        sheetState = sheetState,
        containerColor = Color.White,
        content = {
            val focusManager = LocalFocusManager.current
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding( bottom = if (isFromProduct) 64.dp else 20.dp,
                            start = 20.dp, top = 20.dp, end = 20.dp)
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                modifier = Modifier.weight(1f),
                                text = title,
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    lineHeight = 32.sp,
                                    fontFamily = LatoTypography().bodyMedium.fontFamily,
                                    fontWeight = FontWeight(500),
                                    color = Color.Black,
                                )
                            )
                            IconButton(
                                modifier = Modifier
                                    .size(24.dp),
                                onClick = {
                                    scope
                                        .launch { sheetState.hide() }
                                        .invokeOnCompletion {
                                            if (!sheetState.isVisible) {
                                                onDismiss.invoke()
                                            }
                                        }
                                }
                            ) {
                                Icon(
                                    tint = Color.Black,
                                    painter = painterResource(Res.drawable.close),
                                    contentDescription = null
                                )
                            }
                        }
                        if (showSearch) {
                            CustomSearchViewField(
                                modifier = Modifier
                                    .padding(vertical = 36.dp)
                                    .onFocusChanged { isFocused.value = it.hasFocus }
                                    .focusRequester(focusRequester),
                                searchState = searchState,
                                onValueChange = { searchState.value = it },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Search
                                ),
                                keyboardActions = KeyboardActions(
                                    onSearch = {
                                        performSearch.value = searchState.value
                                        focusManager.clearFocus()
                                    }
                                ),
                                decorationBox = { innerTextField ->
                                    CustomSearchViewBox(
                                        search = UIComponentAction(
                                            viewComponent = painterResource(Res.drawable.search),
                                            viewAction = {
                                                performSearch.value = searchState.value
                                                focusManager.clearFocus()
                                            }
                                        ),
                                        clearState = UIComponentAction(
                                            viewComponent = painterResource(Res.drawable.close),
                                            viewAction = {
                                                searchState.value = String()
                                                performSearch.value = String()
                                            }
                                        ),
                                        fieldState = searchState,
                                        placeholder = searchText,
                                        isFocused = isFocused.value,
                                        innerTextField = innerTextField
                                    )
                                }
                            )
                        } else {
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(state = rememberScrollState())
                        ) {
                            val filteredList =
                                items.filter {
                                    it.text.contains(
                                        performSearch.value,
                                        ignoreCase = true
                                    )
                                }
                            if (filteredList.isEmpty() && emptyIcon != null) {
                                SelectModalEmptyList(
                                    emptyIcon = emptyIcon,
                                    emptyTitle = emptyTitle,
                                    emptyMessage = emptyMessage
                                )
                            } else {
                                SelectModalListContent(
                                    selectedItem = selectedItem,
                                    scope = scope,
                                    sheetState = sheetState,
                                    filteredList = filteredList,
                                    onDismiss = onDismiss,
                                    onItemSelected = onItemSelected,
                                )
                            }


                        }
                    }
                }
            }
        },
        onDismissRequest = onDismiss
    )

}

@Composable
private fun SelectModalEmptyList(
    emptyIcon: Painter? = null,
    emptyTitle: String,
    emptyMessage: String,
) {
    Column(
        modifier = Modifier
            .padding(top = 110.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(Res.drawable.ic_network),
            contentDescription = "",
            alignment = Alignment.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = emptyTitle,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 18.sp,
                fontFamily = LatoTypography().bodyMedium.fontFamily,
                fontWeight = FontWeight(500),
                color = Color.Black
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = emptyMessage,
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 18.sp,
                fontFamily = LatoTypography().bodyMedium.fontFamily,
                fontWeight = FontWeight(400),
                color = Color.Gray
            ),
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SelectModalListContent(
    selectedItem: String,
    scope: CoroutineScope,
    sheetState: SheetState,
    filteredList: List<DropdownItem>,
    onDismiss: () -> Unit,
    onItemSelected: (String, String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        filteredList.map { item ->
            Row(
                modifier = Modifier
                    .clickable {
                        onItemSelected(item.text, item.additionalInfo)
                        scope
                            .launch { sheetState.hide() }
                            .invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    onDismiss.invoke()
                                }
                            }
                    }
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.text,
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 16.sp,
                        lineHeight = 28.sp,
                        fontFamily = LatoTypography().bodyMedium.fontFamily,
                        fontWeight = FontWeight(400),
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(12.dp)
                )
                if (item.text == selectedItem) {
                    Image(
                        painter = painterResource(Res.drawable.bookmark),
                        contentDescription = null,
                    )
                }
            }
            Spacer(modifier = Modifier.height(34.dp))
        }
        Spacer(modifier = Modifier.height(42.dp))
    }
}