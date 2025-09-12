package com.chatia.onBoarding.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cahatia.feature.onboarding.generated.resources.Res
import cahatia.feature.onboarding.generated.resources.chatia_logo
import cahatia.feature.onboarding.generated.resources.chatia_logo_content_description
import cahatia.feature.onboarding.generated.resources.login
import cahatia.feature.onboarding.generated.resources.on_boarding_description
import cahatia.feature.onboarding.generated.resources.on_boarding_title
import cahatia.feature.onboarding.generated.resources.register
import com.chatia.onBoarding.presentation.OnBoardingInfoCard
import com.chatia.onBoarding.presentation.infoType.OnboardingInfoType
import com.chatia.presentation.applyIf
import com.chatia.presentation.models.UiText
import com.chatia.presentation.models.asString
import com.chatia.project.isAndroid
import com.chatia.ui.components.buttons.PrimaryButton
import com.chatia.ui.components.texts.PrimaryText
import com.chatia.ui.components.verticalGradientStops
import org.jetbrains.compose.resources.painterResource

@Composable
fun OnBoardingScreen() {
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
            painter = painterResource(Res.drawable.chatia_logo),
            contentDescription = UiText.Resource(Res.string.chatia_logo_content_description)
                .asString()
        )
        Spacer(modifier = Modifier.height(24.dp))
        PrimaryText(
            text = UiText.Resource(Res.string.on_boarding_title).asString(),
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            lineHeight = 32.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        PrimaryText(
            text = UiText.Resource(Res.string.on_boarding_description).asString(),
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSecondary,
            lineHeight = 20.sp,
            softWrap = true
        )
        Spacer(modifier = Modifier.height(24.dp))
        Column(
            modifier = Modifier.fillMaxWidth().weight(1f).padding(horizontal = 16.dp)
        ) {
            OnboardingInfoType.entries.forEachIndexed { index, it ->
                OnBoardingInfoCard(
                    modifier = Modifier, infoType = it
                )
                if (index != OnboardingInfoType.entries.lastIndex) Spacer(Modifier.height(12.dp))
            }
        }

        Row(
            modifier = Modifier.padding(horizontal = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            PrimaryButton(
                modifier = Modifier.weight(1f),
                text = UiText.Resource(res = Res.string.login).asString(),
                onClick = {},
                textFontWeight = FontWeight.Normal,
                textFontSize = 16.sp,
                textColor = MaterialTheme.colorScheme.onSurface,
                buttonColors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 1.dp),
                contentPadding = PaddingValues(vertical = 12.dp)
            )

            PrimaryButton(
                modifier = Modifier.weight(1f),
                text = UiText.Resource(res = Res.string.register).asString(),
                onClick = {},
                textFontWeight = FontWeight.Normal,
                textFontSize = 16.sp,
                contentPadding = PaddingValues(vertical = 12.dp)
            )
        }
    }
}