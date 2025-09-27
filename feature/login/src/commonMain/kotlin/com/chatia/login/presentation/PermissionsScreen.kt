package com.chatia.login.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cahatia.feature.login.generated.resources.Res
import cahatia.feature.login.generated.resources.adaptive_learning
import cahatia.feature.login.generated.resources.adaptive_learning_description
import cahatia.feature.login.generated.resources.availability
import cahatia.feature.login.generated.resources.availability_description
import cahatia.feature.login.generated.resources.chatia_logo_plus
import cahatia.feature.login.generated.resources.chatia_plus_unlock
import cahatia.feature.login.generated.resources.chatia_plus_unlock_description
import cahatia.feature.login.generated.resources.check_icon
import cahatia.feature.login.generated.resources.confirm_email
import cahatia.feature.login.generated.resources.confirm_email_description
import cahatia.feature.login.generated.resources.continue_text
import cahatia.feature.login.generated.resources.enable_permissions
import cahatia.feature.login.generated.resources.enable_permissions_description
import cahatia.feature.login.generated.resources.resend_in
import cahatia.feature.login.generated.resources.seamless_ai_assistance
import cahatia.feature.login.generated.resources.seamless_ai_assistance_description
import cahatia.feature.login.generated.resources.unlimited_access
import cahatia.feature.login.generated.resources.unlimited_access_description
import cahatia.feature.login.generated.resources.upgrade_to_plus
import com.chatia.login.presentation.NumberInputBox
import com.chatia.login.presentation.model.PermissionsInfoType
import com.chatia.presentation.applyIf
import com.chatia.presentation.models.UiText
import com.chatia.presentation.models.asString
import com.chatia.presentation.resources.chatia_logo
import com.chatia.presentation.resources.chatia_logo_content_description
import com.chatia.project.isAndroid
import com.chatia.ui.components.buttons.PrimaryButton
import com.chatia.ui.components.cards.PrimaryCard
import com.chatia.ui.components.texts.PrimaryText
import com.chatia.ui.components.verticalGradientStops
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.Resource
import org.jetbrains.compose.resources.imageResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun EnablePermissionsScreen() {
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
            text = UiText.Resource(cahatia.feature.login.generated.resources.Res.string.enable_permissions)
                .asString(),
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            lineHeight = 32.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        PrimaryText(
            text = UiText.Resource(cahatia.feature.login.generated.resources.Res.string.enable_permissions_description)
                .asString(),
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))

        Column(
            modifier = Modifier.fillMaxWidth().weight(1f).padding(horizontal = 16.dp)
        ) {
            PermissionsInfoType.entries.forEachIndexed { index, it ->
                AccessPermissionsInfoCard(
                    modifier = Modifier, infoType = it
                )
                if (index != PermissionsInfoType.entries.lastIndex) Spacer(Modifier.height(12.dp))
            }
        }

        PrimaryButton(
            modifier = Modifier.height(52.dp).fillMaxWidth().padding(horizontal = 18.dp),
            text = UiText.Resource(cahatia.feature.login.generated.resources.Res.string.enable_permissions)
                .asString(),
            onClick = { /*TODO*/ },
            textFontWeight = FontWeight.Normal,
            textFontSize = 16.sp
        )
        Spacer(modifier = Modifier.weight(0.1f))

    }
}




@Preview
@Composable
fun PreviewOTPScreen() {
    EnablePermissionsScreen()
}