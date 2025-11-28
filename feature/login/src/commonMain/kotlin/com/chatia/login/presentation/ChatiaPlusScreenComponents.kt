package com.chatia.login.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cahatia.feature.login.generated.resources.Res
import cahatia.feature.login.generated.resources.adaptive_learning
import cahatia.feature.login.generated.resources.adaptive_learning_description
import cahatia.feature.login.generated.resources.availability
import cahatia.feature.login.generated.resources.availability_description
import cahatia.feature.login.generated.resources.check_icon
import cahatia.feature.login.generated.resources.seamless_ai_assistance
import cahatia.feature.login.generated.resources.seamless_ai_assistance_description
import cahatia.feature.login.generated.resources.unlimited_access
import cahatia.feature.login.generated.resources.unlimited_access_description
import com.chatia.presentation.model.UiText
import com.chatia.presentation.model.asString
import com.chatia.ui.components.texts.PrimaryText
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun ContentCard() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(15.dp)
    ) {
        RowCardContent(
            imageResource = Res.drawable.check_icon, title =
                UiText.Resource(Res.string.seamless_ai_assistance).asString(),
            description = UiText.Resource(Res.string.seamless_ai_assistance_description).asString()
        )
        RowCardContent(
            imageResource = Res.drawable.check_icon, title =
                UiText.Resource(Res.string.unlimited_access).asString(),
            description = UiText.Resource(Res.string.unlimited_access_description).asString()
        )
        RowCardContent(
            imageResource = Res.drawable.check_icon, title =
                UiText.Resource(Res.string.availability).asString(),
            description = UiText.Resource(Res.string.availability_description).asString()
        )
        RowCardContent(
            imageResource = Res.drawable.check_icon, title =
                UiText.Resource(Res.string.adaptive_learning).asString(),
            description = UiText.Resource(Res.string.adaptive_learning_description).asString()
        )
    }
}

@Composable
fun RowCardContent(imageResource: DrawableResource, title: String, description: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.size(30.dp).padding(end = 8.dp),
            painter = painterResource(imageResource),
            contentDescription = "",
            tint = Color.Unspecified
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {
            PrimaryText(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
            )
            PrimaryText(
                text = description,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSecondary,
                lineHeight = 16.sp
            )
        }
    }
}
