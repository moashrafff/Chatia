package com.chatia.login.presentation.PermissionsScreen

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
import cahatia.feature.login.generated.resources.enable_permissions
import cahatia.feature.login.generated.resources.enable_permissions_description
import com.chatia.login.presentation.model.PermissionsInfoType
import com.chatia.login.presentation.PermissionsScreen.PermissionsInfoType
import com.chatia.presentation.applyIf
import com.chatia.presentation.model.UiText
import com.chatia.presentation.model.asString
import com.chatia.presentation.permission.PermissionBridge
import com.chatia.presentation.permission.PermissionResultCallback
import com.chatia.presentation.permission.PermissionState
import com.chatia.presentation.permission.PermissionsBridgeListener
import com.chatia.presentation.models.UiText
import com.chatia.presentation.models.asString
import com.chatia.presentation.resources.Res
import com.chatia.presentation.resources.chatia_logo
import com.chatia.presentation.resources.chatia_logo_content_description
import com.chatia.project.isAndroid
import com.chatia.ui.components.buttons.PrimaryButton
import com.chatia.ui.components.texts.PrimaryText
import com.chatia.ui.components.verticalGradientStops
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun EnablePermissionsScreen(
    onEnablePermissionsButtonClick: () -> Unit
) {
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
            painter = painterResource(Res.drawable.chatia_logo),
            contentDescription = UiText.Resource(Res.string.chatia_logo_content_description)
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
            onClick = onEnablePermissionsButtonClick,
            textFontWeight = FontWeight.Normal,
            textFontSize = 16.sp
        )
        Spacer(modifier = Modifier.weight(0.1f))

    }
}



@Preview
@Composable
fun PreviewOTPScreen() {
    EnablePermissionsScreen({})
}