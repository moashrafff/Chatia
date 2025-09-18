package com.chatia.login.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cahatia.feature.login.generated.resources.Res
import cahatia.feature.login.generated.resources.login_title
import cahatia.feature.login.generated.resources.password_icon
import cahatia.feature.login.generated.resources.password_icon_content_description
import cahatia.feature.login.generated.resources.password_placeholder
import cahatia.feature.login.generated.resources.username_placeholder
import com.chatia.presentation.applyIf
import com.chatia.presentation.models.UiText
import com.chatia.presentation.models.asString
import com.chatia.presentation.resources.chatia_logo
import com.chatia.presentation.resources.chatia_logo_content_description
import com.chatia.project.isAndroid
import com.chatia.ui.components.inputFields.PrimaryInputField
import com.chatia.ui.components.texts.PrimaryText
import com.chatia.ui.components.verticalGradientStops
import com.chatia.utils.AppLogger
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun LoginScreen() {
    var text by remember { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .verticalGradientStops(
                0.12f to Color(0xFFFFFFFF), 0.24f to Color(0xFFFFE5F9), 0.66f to Color(0xFFFFF7EB)
            )

            .applyIf(condition = isAndroid(), modifier = { padding(bottom = 8.dp) })
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier.fillMaxSize().safeContentPadding().imePadding().padding(horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Image(
                modifier = Modifier.size(136.dp),
                painter = painterResource(com.chatia.presentation.resources.Res.drawable.chatia_logo),
                contentDescription = UiText.Resource(com.chatia.presentation.resources.Res.string.chatia_logo_content_description).asString()
            )
            Spacer(modifier = Modifier.height(24.dp))
            PrimaryText(
                text = UiText.Resource(Res.string.login_title).asString(),
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
                value = text,
                onValueChange = {
                    try {
                        text = it
                    } catch (e: Exception) {
                        AppLogger.e("exception error", "error", e)
                    }
                }
            )
            Spacer(modifier = Modifier.height(4.dp))
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
                value = text,
                onValueChange = {
                    try {
                        text = it
                    } catch (e: Exception) {
                        AppLogger.e("exception error", "error", e)
                    }
                },
                trailingIcon = {
                    Icon(
                        modifier = Modifier.padding(6.dp).size(16.dp).clickable{ passwordVisible = !passwordVisible },
                        tint = Color.Unspecified,
                        painter = painterResource(Res.drawable.password_icon),
                        contentDescription = UiText.Resource(Res.string.password_icon_content_description).asString()
                    )
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
        }
    }
}

