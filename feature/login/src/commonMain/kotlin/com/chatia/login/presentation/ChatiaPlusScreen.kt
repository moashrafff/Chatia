package com.chatia.login.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cahatia.feature.login.generated.resources.Res
import cahatia.feature.login.generated.resources.chatia_logo_plus
import cahatia.feature.login.generated.resources.chatia_plus_unlock
import cahatia.feature.login.generated.resources.chatia_plus_unlock_description
import cahatia.feature.login.generated.resources.upgrade_to_plus
import com.chatia.presentation.applyIf
import com.chatia.presentation.models.UiText
import com.chatia.presentation.models.asString
import com.chatia.project.isAndroid
import com.chatia.ui.components.buttons.PrimaryButton
import com.chatia.ui.components.cards.PrimaryCard
import com.chatia.ui.components.texts.PrimaryText
import com.chatia.ui.components.verticalGradientStops
import org.jetbrains.compose.resources.painterResource

@Composable
fun ChatiaPlusScreen() {
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
            painter = painterResource(Res.drawable.chatia_logo_plus),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(24.dp))
        PrimaryText(
            text = UiText.Resource(Res.string.chatia_plus_unlock).asString(),
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            lineHeight = 32.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        PrimaryText(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = UiText.Resource(Res.string.chatia_plus_unlock_description).asString(),
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSecondary
        )
        Spacer(modifier = Modifier.height(12.dp))

        PrimaryCard(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            withElevation = false,
            cardColors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(20.dp),
            enabled = false,
            content = {
                ContentCard()
            }
        )
        Spacer(modifier = Modifier.padding(18.dp))

        PrimaryButton(
            modifier = Modifier.height(52.dp).fillMaxWidth().padding(horizontal = 18.dp),
            text = UiText.Resource(Res.string.upgrade_to_plus)
                .asString(),
            onClick = { /*TODO*/ },
            textFontWeight = FontWeight.Normal,
            textFontSize = 16.sp
        )
    }
}
