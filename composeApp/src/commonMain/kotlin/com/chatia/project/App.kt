package com.chatia.project

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
import cahatia.composeapp.generated.resources.Res
import cahatia.composeapp.generated.resources.chatia_logo
import cahatia.composeapp.generated.resources.login
import cahatia.composeapp.generated.resources.register
import com.chatia.project.presentation.infoType.OnboardingInfoType
import com.chatia.ui.components.buttons.PrimaryButton
import com.chatia.ui.components.texts.PrimaryText
import com.chatia.ui.components.verticalGradientStops
import com.chatia.ui.theme.baseTheme.ChatiaTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    ChatiaTheme {
        Column(
            modifier = Modifier
                .verticalGradientStops(
                    0.12f to Color(0xFFFFFFFF),
                    0.24f to Color(0xFFFFE5F9),
                    0.66f to Color(0xFFFFF7EB)
                )
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Image(
                modifier = Modifier.size(136.dp),
                painter = painterResource(Res.drawable.chatia_logo),
                contentDescription = "chatia-logo"
            )
            Spacer(modifier = Modifier.height(24.dp))
            PrimaryText(
                text = "Smart AI Companion for\nAnytime Assistance",
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                lineHeight = 32.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            PrimaryText(
                text = "Your smart AI companion, ready to assist with\nanswers, advice, and inspiration, keeping you\ninformed and engaged anytime, anywhere you go.",
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSecondary,
                lineHeight = 20.sp,
                softWrap = true
            )
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .applyIf(
                        condition = isIOS(),
                        modifier = {
                            padding(horizontal = 18.dp)
                        })
            ) {
                OnboardingInfoType.entries.forEachIndexed { index, it ->
                    OnBoardingInfoCard(
                        modifier = Modifier,
                        infoType = it
                    )
                    if (index != OnboardingInfoType.entries.lastIndex)
                        Spacer(Modifier.height(12.dp))
                }
            }

            Row(
                modifier = Modifier.applyIf(
                    condition = isIOS(),
                    modifier = {
                        padding(horizontal = 18.dp)
                    }),
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
}