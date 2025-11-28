package com.chatia.login.presentation.OTPScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cahatia.feature.login.generated.resources.Res
import cahatia.feature.login.generated.resources.confirm_email
import cahatia.feature.login.generated.resources.confirm_email_description
import cahatia.feature.login.generated.resources.continue_text
import cahatia.feature.login.generated.resources.resend_in
import com.chatia.presentation.applyIf
import com.chatia.presentation.model.UiText
import com.chatia.presentation.model.asString
import com.chatia.presentation.resources.chatia_logo
import com.chatia.presentation.resources.chatia_logo_content_description
import com.chatia.project.isAndroid
import com.chatia.ui.components.buttons.PrimaryButton
import com.chatia.ui.components.texts.PrimaryText
import com.chatia.ui.components.verticalGradientStops
import org.jetbrains.compose.resources.painterResource


@Composable
fun OTPScreen(optNumbersState: MutableList<String>) {
    Column(
        modifier = Modifier.verticalGradientStops(
            0.12f to Color(0xFFFFFFFF), 0.24f to Color(0xFFFFE5F9), 0.66f to Color(0xFFFFF7EB)
        ).applyIf(condition = isAndroid(), modifier = { Modifier.padding(8.dp) })
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(52.dp))
        Image(
            modifier = Modifier.size(136.dp),
            painter = painterResource(com.chatia.presentation.resources.Res.drawable.chatia_logo),
            contentDescription = UiText.Resource(com.chatia.presentation.resources.Res.string.chatia_logo_content_description)
                .asString()
        )
        Spacer(modifier = Modifier.height(24.dp))
        PrimaryText(
            text = UiText.Resource(Res.string.confirm_email)
                .asString(),
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            lineHeight = 32.sp
        )
        PrimaryText(
            modifier = Modifier.padding(horizontal = 18.dp),
            text = UiText.Resource(Res.string.confirm_email_description)
                .asString(),
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(12.dp))
        //
        //

        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
//            val remeberListState = remember { mutableStateListOf(0,0,0,0,0,0)  }

            OTPFeilds(otpNumbersState = optNumbersState)
        }

        PrimaryText(
            text = UiText.Resource(Res.string.resend_in).asString(),
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.padding(142.dp))
//        Spacer(modifier = Modifier.weight(0.1f))

        PrimaryButton(
            modifier = Modifier.height(52.dp).fillMaxWidth().padding(horizontal = 18.dp),
            text = UiText.Resource(Res.string.continue_text)
                .asString(),
            onClick = { /*TODO*/ },
            textFontWeight = FontWeight.Normal,
            textFontSize = 16.sp,
        )
//        Spacer(modifier = Modifier.weight(0.1f))
    }
}
