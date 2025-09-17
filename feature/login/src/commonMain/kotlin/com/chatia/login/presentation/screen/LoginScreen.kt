package com.chatia.login.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cahatia.feature.login.generated.resources.Res
import com.chatia.presentation.applyIf
import com.chatia.presentation.models.UiText
import com.chatia.presentation.models.asString
import com.chatia.presentation.resources.chatia_logo
import com.chatia.presentation.resources.chatia_logo_content_description
import com.chatia.project.isAndroid
import com.chatia.ui.components.inputFields.PrimaryInputField
import com.chatia.ui.components.texts.PrimaryText
import com.chatia.ui.components.verticalGradientStops
import com.chatia.ui.theme.baseTheme.ChatiaTheme
import com.chatia.utils.AppLogger
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun LoginScreen() {
    var text by remember {  mutableStateOf("")}
        Column(
            modifier = Modifier
                .verticalGradientStops(
                    0.12f to Color(0xFFFFFFFF), 0.24f to Color(0xFFFFE5F9), 0.66f to Color(0xFFFFF7EB)
                )
                .safeContentPadding()
                .applyIf(condition = isAndroid(), modifier = {padding(bottom = 8.dp)})
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Image(
                modifier = Modifier.size(136.dp),
                painter = painterResource(com.chatia.presentation.resources.Res.drawable.chatia_logo),
                contentDescription = UiText.Dynamic("")
                    .asString()
            )
            Spacer(modifier = Modifier.height(24.dp))
            PrimaryText(
                text = UiText.Dynamic("").asString(),
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                lineHeight = 32.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            PrimaryInputField(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White.copy(alpha = .5F),
                    unfocusedContainerColor = Color.White.copy(alpha = .5F),
                    disabledContainerColor = Color.White.copy(alpha = .5F),
                    errorContainerColor = Color.White.copy(alpha = .5F),
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White,
                    disabledIndicatorColor = Color.White.copy(alpha = 0.5f),
                    errorIndicatorColor = Color.White
                ),
                shape = RoundedCornerShape(48.dp),
                placeholder = "user Name",
                value = text,
                onValueChange = {
                    try {
                        text = it
                    } catch (e: Exception) {
                        AppLogger.e("exception error", "error", e)
                    }
                }
            )
        }
}

