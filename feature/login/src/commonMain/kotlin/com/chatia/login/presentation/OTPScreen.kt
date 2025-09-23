package com.chatia.login.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cahatia.feature.login.generated.resources.Res
import cahatia.feature.login.generated.resources.login_title
import com.chatia.presentation.applyIf
import com.chatia.presentation.models.UiText
import com.chatia.presentation.models.asString
import com.chatia.presentation.resources.chatia_logo
import com.chatia.presentation.resources.chatia_logo_content_description
import com.chatia.project.isAndroid
import com.chatia.ui.components.cards.PrimaryCard
import com.chatia.ui.components.texts.PrimaryText
import com.chatia.ui.components.verticalGradientStops
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun OTPScreen() {
    Column(
        modifier = Modifier.verticalGradientStops(
            0.12f to Color(0xFFFFFFFF), 0.24f to Color(0xFFFFE5F9), 0.66f to Color(0xFFFFF7EB)
        ).applyIf(condition = isAndroid(),modifier = { Modifier.padding(8.dp) })
            .fillMaxSize()
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
    }
}



@Preview
@Composable
fun previewOTPScreen() {
    OTPScreen()
}