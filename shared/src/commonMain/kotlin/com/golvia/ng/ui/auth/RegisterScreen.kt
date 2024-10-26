package com.golvia.ng.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableLongState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.golvia.ng.businessLayer.domain.convertStringListToDropdownItem
import com.golvia.ng.businessLayer.util.validateEmail
import com.golvia.ng.common.ChangeStatusBarColors
import com.golvia.ng.presentation.component.DefaultScreenUI
import com.golvia.ng.presentation.component.DefaultText
import com.golvia.ng.presentation.component.DividerWithTextContent
import com.golvia.ng.presentation.component.InputFieldHeader
import com.golvia.ng.presentation.component.NormalRoundedButton
import com.golvia.ng.presentation.component.OutlinedInputField
import com.golvia.ng.presentation.component.OutlinedRoundedButtonWithIcon
import com.golvia.ng.presentation.component.PasswordTextField
import com.golvia.ng.presentation.component.SelectModal
import com.golvia.ng.presentation.component.Spacer_16dp
import com.golvia.ng.presentation.component.Spacer_24dp
import com.golvia.ng.presentation.component.Spacer_32dp
import com.golvia.ng.presentation.component.Spacer_4dp
import com.golvia.ng.presentation.component.Spacer_8dp
import com.golvia.ng.presentation.component.TextFieldState
import com.golvia.ng.presentation.theme.LatoTypography
import com.golvia.ng.presentation.theme.PrimaryColor
import com.golvia.ng.presentation.theme.Thick_black
import com.golvia.ng.presentation.theme.default_black
import com.golvia.ng.presentation.theme.gray_50
import com.golvia.ng.presentation.theme.light_gray
import golvia.shared.generated.resources.Res
import golvia.shared.generated.resources.continue_with_google
import golvia.shared.generated.resources.create_account
import golvia.shared.generated.resources.email_address
import golvia.shared.generated.resources.email_error
import golvia.shared.generated.resources.full_name
import golvia.shared.generated.resources.full_name_hint
import golvia.shared.generated.resources.have_account
import golvia.shared.generated.resources.ic_google_icon
import golvia.shared.generated.resources.ic_logo_header
import golvia.shared.generated.resources.ic_network
import golvia.shared.generated.resources.legal_name_message
import golvia.shared.generated.resources.login
import golvia.shared.generated.resources.password
import golvia.shared.generated.resources.password_error
import golvia.shared.generated.resources.register_with_email
import golvia.shared.generated.resources.sign_up
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

/**
 * davidsunday
 */

@Composable
fun RegisterScreen(
    navigateToMain: () -> Unit,
    popUp: () -> Unit
){

    val country = remember { TextFieldState("") }
    val selectedCountryID = remember { mutableLongStateOf(0L) }
    var showCountry by remember { mutableStateOf(false) }
    ChangeStatusBarColors(Color.White)

    //Todo update from api
    val countryList = listOf(
        "Nigeria",
        "Ghana",
        "Kenya",
        "Togo",
        "South Africa",
        "Tanzania",
        "Uganda",
        "Zambia",
        "Zimbabwe",
        "Botswana",
        "Burundi",
        "Burkina Faso"
    )

    DefaultScreenUI(
        onRemoveHeadFromQueue = { },
    ) {
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val isPasswordError = remember { mutableStateOf(false) }
        val isEmailError = remember { mutableStateOf(false) }
        val isCountryError = remember { mutableStateOf(false) }
        val isFullNameError = remember { mutableStateOf(false) }
        val passWordError = remember { mutableStateOf("") }
        val emailError = remember { mutableStateOf("") }
        val fullName = remember { mutableStateOf("") }
        val fullNameError = remember { mutableStateOf("") }
        val countryError = remember { mutableStateOf("") }


        passWordError.value = stringResource(Res.string.password_error)
        emailError.value = stringResource(Res.string.email_error)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Image(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(120.dp),
                    painter = painterResource(Res.drawable.ic_logo_header),
                    contentDescription = null,
                )
                Spacer_8dp()
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = stringResource(Res.string.create_account),
                    style = TextStyle(
                        fontSize = 24.sp,
                        lineHeight = 30.sp,
                        fontFamily = LatoTypography().bodyMedium.fontFamily,
                        fontWeight = FontWeight(600),
                        color = Thick_black,
                        fontStyle = LatoTypography().bodyMedium.fontStyle
                    )
                )
                Spacer_24dp()
                OutlinedRoundedButtonWithIcon(
                    textButton = stringResource(Res.string.continue_with_google),
                    textColor = Color.Black,
                    borderColor = light_gray,
                    containerColor = Color.White,
                    enabled = true,
                    painter = painterResource(Res.drawable.ic_google_icon),
                    onClick = {
                        //Todo add google login
                    }
                )
                Spacer_32dp()
                DividerWithTextContent(
                    textResource = stringResource(Res.string.register_with_email),
                    textColor = gray_50,
                    dividerColor = light_gray
                )
                Spacer_16dp()

                Column(horizontalAlignment = Alignment.Start) {
                    InputFieldHeader(
                        textValue = stringResource(Res.string.full_name)
                    )
                    Spacer_4dp()
                    OutlinedInputField(
                        isError = isFullNameError.value,
                        errorValue = fullNameError.value,
                        textFieldValue = fullName.value,
                        placeholder = stringResource(Res.string.full_name_hint),
                        onValueChange = {
                            isEmailError.value = false
                            email.value = it
                        }
                    )
                    Text(
                        stringResource(Res.string.legal_name_message),
                        style = TextStyle(
                            fontSize = 12.sp,
                            lineHeight = 15.sp,
                            fontFamily = LatoTypography().bodySmall.fontFamily,
                            fontWeight = FontWeight(300),
                            color = Color.Gray,
                            fontStyle = LatoTypography().bodySmall.fontStyle
                        ))
                    Spacer_24dp()

                    InputFieldHeader(
                        textValue = "Country"
                    )
                    Spacer_4dp()
                    OutlinedInputField(
                        modifier = Modifier
                            .clickable {
                                showCountry = true
                            },
                        enabled = false,
                        isError = isCountryError.value,
                        errorValue = "Select Country",
                        placeholder = "Select",
                        textFieldValue = country.value,
                        onValueChange = {
                            country.value = it
                        },
                        showDropDown = true
                    )
                    Spacer_24dp()
                    InputFieldHeader(
                        textValue = stringResource(Res.string.email_address)
                    )

                    Spacer_4dp()
                    OutlinedInputField(
                        isError = isEmailError.value,
                        errorValue = emailError.value,
                        textFieldValue = email.value,
                        onValueChange = {
                            isEmailError.value = false
                            email.value = it
                        }
                    )
                    Spacer_24dp()
                    InputFieldHeader(
                        textValue = stringResource(Res.string.password)
                    )
                    Spacer_4dp()
                    PasswordTextField(
                         isError = isPasswordError.value,
                         errorValue = passWordError.value,
                        value = password.value,
                        onValueChange = {
                            isPasswordError.value = false
                            password.value = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                    )
                }

                Spacer(modifier = Modifier.height(300.dp))
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
            ) {
                NormalRoundedButton(
                    textButton = stringResource(Res.string.sign_up),
                    textColor = Color.White,
                    containerColor = PrimaryColor,
                    enabled = email.value.isNotEmpty() && password.value.isNotEmpty()
                            && country.value.isNotEmpty() && fullName.value.isNotEmpty(),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                ) {
                    if (!validateEmail(email.value)){
                        isEmailError.value = true
                        return@NormalRoundedButton
                    }else if (password.value.length < 6) {
                        isPasswordError.value = true
                        return@NormalRoundedButton
                    }else if (country.value == "Select"){
                        isCountryError.value = true
                        return@NormalRoundedButton
                    }else {
                        isPasswordError.value = false
                        isEmailError.value = false
                        isCountryError.value = false
                        // Todo navigate to main when auth is done
                        navigateToMain()
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    DefaultText(
                        textValue = stringResource(Res.string.have_account),
                        color = default_black
                    )
                    DefaultText(
                        modifier = Modifier
                            .clickable {
                                popUp()
                            },
                        textValue = stringResource(Res.string.login),
                        color = PrimaryColor,
                        textDecoration = TextDecoration.Underline,
                        fontWeight = FontWeight(500)
                    )
                }
            }

        }
    }

    if (showCountry){
        AddUnitOfMeasurementListModal(
            countryList = countryList,
            selectedCountryID = selectedCountryID,
            countryState = country,
            onDismiss = {
                showCountry = false
            }
        )
    }
}

@Composable
fun AddUnitOfMeasurementListModal(
    countryList: List<String>,
    selectedCountryID: MutableLongState,
    countryState: TextFieldState,
    onDismiss: () -> Unit
) {
    SelectModal(
        title = "Select Country",
        searchText = "Search Country",
        items = convertStringListToDropdownItem(countryList),
        onItemSelected = { selectedText, selectedInfo ->
            countryState.value = selectedText
        },
        emptyIcon = painterResource(Res.drawable.ic_network),
        onDismiss = onDismiss,
        emptyTitle = "No Match Found",
        emptyMessage = "No Country to display",
        selectedItem = countryState.value,
        isFromProduct = true
    )
}