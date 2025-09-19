package com.chatia.login.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cahatia.feature.login.generated.resources.Res
import cahatia.feature.login.generated.resources.apple_icon
import cahatia.feature.login.generated.resources.create_an_account
import cahatia.feature.login.generated.resources.dont_have_account
import cahatia.feature.login.generated.resources.forget_password
import cahatia.feature.login.generated.resources.google_icon
import cahatia.feature.login.generated.resources.login_title
import cahatia.feature.login.generated.resources.login_with_apple
import cahatia.feature.login.generated.resources.login_with_google
import cahatia.feature.login.generated.resources.or_login_with
import cahatia.feature.login.generated.resources.password_icon
import cahatia.feature.login.generated.resources.password_icon_content_description
import cahatia.feature.login.generated.resources.password_placeholder
import cahatia.feature.login.generated.resources.remember_me
import cahatia.feature.login.generated.resources.sign_in
import cahatia.feature.login.generated.resources.username_placeholder
import com.chatia.presentation.applyIf
import com.chatia.presentation.models.UiText
import com.chatia.presentation.models.asString
import com.chatia.presentation.resources.chatia_logo
import com.chatia.presentation.resources.chatia_logo_content_description
import com.chatia.project.isAndroid
import com.chatia.ui.components.buttons.PrimaryButton
import com.chatia.ui.components.inputFields.PrimaryInputField
import com.chatia.ui.components.texts.PrimaryText
import com.chatia.ui.components.verticalGradientStops
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun LoginScreen() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }
    var checked by remember { mutableStateOf(false) }


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
            modifier = Modifier.fillMaxSize().safeContentPadding().imePadding()
                .padding(horizontal = 10.dp),
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
                value = username,
                onValueChange = {
                    username = it
                }
            )
            Spacer(modifier = Modifier.height(4.dp))
            PrimaryInputField(
                modifier = Modifier.fillMaxWidth().padding(0.dp),
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
                value = password,
                onValueChange = {
                    password = it
                },
                trailingIcon = {
                    Icon(
                        modifier = Modifier.padding(6.dp).size(16.dp)
                            .clickable { passwordVisible = !passwordVisible },
                        tint = Color.Unspecified,
                        painter = painterResource(Res.drawable.password_icon),
                        contentDescription = UiText.Resource(Res.string.password_icon_content_description)
                            .asString()
                    )
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(18.dp)
                            .clip(CircleShape)
                            .background(
                                if (checked) MaterialTheme.colorScheme.primary else Color.White.copy(
                                    alpha = 0.35f
                                ), RoundedCornerShape(0.5f)
                            )
                            .applyIf(
                                condition = !checked,
                                modifier = {
                                    border(
                                        2.dp,
                                        Color.White.copy(alpha = 0.5f),
                                        CircleShape
                                    )
                                })
                            .clickable { checked = !checked },
                        contentAlignment = Alignment.Center
                    ) {
                        if (checked) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }
                    PrimaryText(
                        modifier = Modifier.padding(start = 4.dp),
                        text = UiText.Resource(Res.string.remember_me).asString(),
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
                PrimaryText(
                    modifier = Modifier.clickable {},
                    text = UiText.Resource(Res.string.forget_password).asString(),
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                )
            }
            Spacer(modifier = Modifier.height(18.dp))
            PrimaryButton(
                modifier = Modifier.height(52.dp).fillMaxWidth(),
                text = UiText.Resource(Res.string.sign_in).asString(),
                onClick = {},
                textFontWeight = FontWeight.Normal,
                textFontSize = 16.sp,
            )
            Spacer(modifier = Modifier.height(28.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    thickness = 1.dp,
                    color = Color.White
                )
                PrimaryText(
                    text = UiText.Resource(Res.string.or_login_with).asString(),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                )
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    thickness = 1.dp,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(28.dp))
            PrimaryButton(
                modifier = Modifier.height(52.dp).fillMaxWidth(),
                buttonColors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                ),
                text = UiText.Resource(Res.string.login_with_google).asString(),
                onClick = {},
                textFontWeight = FontWeight.Normal,
                textColor = MaterialTheme.colorScheme.onSurface ,
                textFontSize = 16.sp,
                icon = {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(Res.drawable.google_icon),
                        contentDescription = UiText.Resource(Res.string.login_with_google).asString(),
                        tint = Color.Unspecified
                    )
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            PrimaryButton(
                modifier = Modifier.height(52.dp).fillMaxWidth(),
                buttonColors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                ),
                text = UiText.Resource(Res.string.login_with_apple).asString(),
                onClick = {},
                textFontWeight = FontWeight.Normal,
                textColor = MaterialTheme.colorScheme.onSurface ,
                textFontSize = 16.sp,
                icon = {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(Res.drawable.apple_icon),
                        contentDescription = UiText.Resource(Res.string.login_with_apple).asString(),
                        tint = Color.Unspecified
                    )
                }
            )
            Spacer(modifier = Modifier.weight(1f))
            val createAccountText = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    )
                ) {
                    append(UiText.Resource(Res.string.dont_have_account).asString())
                }

                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF0062FF),
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    )
                ) {
                    append(UiText.Resource(Res.string.create_an_account).asString())
                }
            }
            Text(
                modifier = Modifier.clickable{},
                text  = createAccountText
            )
        }
    }
}

