package com.chatia.register.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cahatia.feature.register.generated.resources.Res
import cahatia.feature.register.generated.resources.down_arrow
import cahatia.feature.register.generated.resources.email_placeholder
import cahatia.feature.register.generated.resources.have_an_account
import cahatia.feature.register.generated.resources.login
import cahatia.feature.register.generated.resources.password_icon
import cahatia.feature.register.generated.resources.password_icon_content_description
import cahatia.feature.register.generated.resources.password_placeholder
import cahatia.feature.register.generated.resources.phone_number_placeholder
import cahatia.feature.register.generated.resources.register
import cahatia.feature.register.generated.resources.register_title
import cahatia.feature.register.generated.resources.us_flag
import cahatia.feature.register.generated.resources.username_placeholder
import com.chatia.presentation.applyIf
import com.chatia.presentation.component.AuthenticationAnnotatedText
import com.chatia.presentation.model.UiText
import com.chatia.presentation.model.asString
import com.chatia.presentation.resources.chatia_logo
import com.chatia.presentation.resources.chatia_logo_content_description
import com.chatia.project.isAndroid
import com.chatia.register.presentation.protocol.RegisterIntent
import com.chatia.register.presentation.protocol.RegisterUiState
import com.chatia.ui.components.buttons.PrimaryButton
import com.chatia.ui.components.inputFields.PrimaryInputField
import com.chatia.ui.components.texts.PrimaryText
import com.chatia.ui.components.verticalGradientStops
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun RegisterUiContent(
    uiState: RegisterUiState = RegisterUiState(),
    sendIntent: (RegisterIntent) -> Unit
) {
    Column(
        modifier = Modifier
            .verticalGradientStops(
                0.12f to Color(0xFFFFFFFF), 0.24f to Color(0xFFFFE5F9), 0.66f to Color(0xFFFFF7EB)
            )
            .fillMaxSize()
            .applyIf(condition = isAndroid(), modifier = { padding(bottom = 8.dp) }),

        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier.fillMaxSize().safeContentPadding()
                .padding(horizontal = 10.dp).verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Image(
                modifier = Modifier.size(136.dp),
                painter = painterResource(com.chatia.presentation.resources.Res.drawable.chatia_logo),
                contentDescription = UiText.Resource(com.chatia.presentation.resources.Res.string.chatia_logo_content_description)
                    .asString()
            )
            Spacer(modifier = Modifier.height(24.dp))
            PrimaryText(
                text = UiText.Resource(Res.string.register_title).asString(),
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                lineHeight = 32.sp
            )
            Spacer(modifier = Modifier.height(32.dp))
            PrimaryInputField(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(48.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White.copy(alpha = .5F),
                    unfocusedContainerColor = Color.White.copy(alpha = .5F),
                    disabledContainerColor = Color.White.copy(alpha = .5F),
                    errorContainerColor = Color.White.copy(alpha = .5F),
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = Color.White,
                    disabledIndicatorColor = Color.White.copy(alpha = 0.5f),
                    errorIndicatorColor = Color.White
                ),
                placeholder = UiText.Resource(Res.string.username_placeholder).asString(),
                value = uiState.registerUIModel.userName,
                onValueChange = {sendIntent.invoke(RegisterIntent.UserNameUpdated(it))}
            )
            Spacer(modifier = Modifier.height(16.dp))
            PrimaryInputField(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(48.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White.copy(alpha = .5F),
                    unfocusedContainerColor = Color.White.copy(alpha = .5F),
                    disabledContainerColor = Color.White.copy(alpha = .5F),
                    errorContainerColor = Color.White.copy(alpha = .5F),
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = Color.White,
                    disabledIndicatorColor = Color.White.copy(alpha = 0.5f),
                    errorIndicatorColor = Color.White
                ),
                placeholder = UiText.Resource(Res.string.email_placeholder).asString(),
                value = uiState.registerUIModel.email,
                onValueChange = {sendIntent.invoke(RegisterIntent.EmailUpdated(it))},
                isError = uiState.showEmailError(),
                errorText = stringResource(uiState.emailError.getErrorMessage())
            )
            Spacer(modifier = Modifier.height(16.dp))
            PrimaryInputField(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(48.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White.copy(alpha = .5F),
                    unfocusedContainerColor = Color.White.copy(alpha = .5F),
                    disabledContainerColor = Color.White.copy(alpha = .5F),
                    errorContainerColor = Color.White.copy(alpha = .5F),
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = Color.White,
                    disabledIndicatorColor = Color.White.copy(alpha = 0.5f),
                    errorIndicatorColor = Color.White
                ),
                placeholder = UiText.Resource(Res.string.phone_number_placeholder).asString(),
                leadingIcon = {
                    Row(
                        modifier = Modifier.padding(start = 12.dp, end = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            modifier = Modifier.size(16.dp).clip(CircleShape),
                            painter = painterResource(Res.drawable.us_flag),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                        PrimaryText(
                            text = "+1",
                            color = MaterialTheme.colorScheme.onSurface,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal
                        )
                        Icon(
                            modifier = Modifier.size(10.dp),
                            painter = painterResource(Res.drawable.down_arrow),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    }
                },
                value = uiState.registerUIModel.phone,
                onValueChange = {sendIntent.invoke(RegisterIntent.PhoneUpdated(it))},
                isError = uiState.showPhoneError(),
                errorText = stringResource(uiState.phoneError.getErrorMessage())
            )
            Spacer(modifier = Modifier.height(16.dp))
            PrimaryInputField(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(48.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White.copy(alpha = .5F),
                    unfocusedContainerColor = Color.White.copy(alpha = .5F),
                    disabledContainerColor = Color.White.copy(alpha = .5F),
                    errorContainerColor = Color.White.copy(alpha = .5F),
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = Color.White,
                    disabledIndicatorColor = Color.White.copy(alpha = 0.5f),
                    errorIndicatorColor = Color.White
                ),
                placeholder = UiText.Resource(Res.string.password_placeholder).asString(),
                value = uiState.registerUIModel.password,
                onValueChange = {sendIntent.invoke(RegisterIntent.PasswordUpdated(it))},
                isError = uiState.showPasswordError(),
                errorText = stringResource(uiState.passwordError.getErrorMessage()),
                trailingIcon = {
                    Icon(
                        modifier = Modifier.padding(6.dp).size(16.dp)
                            .clickable { },
                        tint = Color.Unspecified,
                        painter = painterResource(Res.drawable.password_icon),
                        contentDescription = UiText.Resource(Res.string.password_icon_content_description)
                            .asString()
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Spacer(modifier = Modifier.height(16.dp))
            PrimaryInputField(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(48.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White.copy(alpha = .5F),
                    unfocusedContainerColor = Color.White.copy(alpha = .5F),
                    disabledContainerColor = Color.White.copy(alpha = .5F),
                    errorContainerColor = Color.White.copy(alpha = .5F),
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = Color.White,
                    disabledIndicatorColor = Color.White.copy(alpha = 0.5f),
                    errorIndicatorColor = Color.White
                ),
                placeholder = UiText.Resource(Res.string.password_placeholder).asString(),
                value = uiState.registerUIModel.confirmPassword,
                onValueChange = {sendIntent.invoke(RegisterIntent.ConfirmPasswordUpdated(it))},
                isError = uiState.showConfirmPasswordError(),
                errorText = stringResource(uiState.confirmPasswordError.getErrorMessage()),
                trailingIcon = {
                    Icon(
                        modifier = Modifier.padding(6.dp).size(16.dp)
                            .clickable { },
                        tint = Color.Unspecified,
                        painter = painterResource(Res.drawable.password_icon),
                        contentDescription = UiText.Resource(Res.string.password_icon_content_description)
                            .asString()
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Spacer(modifier = Modifier.height(32.dp))
            PrimaryButton(
                modifier = Modifier.height(52.dp).fillMaxWidth(),
                text = UiText.Resource(Res.string.register).asString(),
                onClick = {sendIntent.invoke(RegisterIntent.RegisterButtonClicked)},
                textFontWeight = FontWeight.Normal,
                textFontSize = 16.sp,
            )
            Spacer(modifier = Modifier.weight(1f))
            AuthenticationAnnotatedText(
                firstText = UiText.Resource(Res.string.have_an_account),
                secondText = UiText.Resource(Res.string.login),
                onCreateAccountClicked = {sendIntent.invoke(RegisterIntent.AlreadyHaveAccountClicked)}
            )
        }
    }
}