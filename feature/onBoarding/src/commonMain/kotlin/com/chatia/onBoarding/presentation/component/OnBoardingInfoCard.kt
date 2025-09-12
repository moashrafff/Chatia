package com.chatia.onBoarding.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chatia.onBoarding.presentation.model.OnboardingInfoType
import com.chatia.presentation.models.asString
import com.chatia.ui.components.cards.PrimaryCard
import com.chatia.ui.components.texts.PrimaryText
import org.jetbrains.compose.resources.painterResource

@Composable
fun OnBoardingInfoCard(
    modifier: Modifier = Modifier,
    infoType: OnboardingInfoType
) {
    PrimaryCard(
        modifier = modifier,
        withElevation = false,
        cardColors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(20.dp),
        enabled = false,
        content = {
            Row(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 14.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp, alignment = Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(infoType.icon),
                    contentDescription = infoType.toString(),
                    tint = Color.Unspecified
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                ) {
                    PrimaryText(
                        text = infoType.title.asString(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                    )
                    PrimaryText(
                        text = infoType.description.asString(),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onSecondary,
                        lineHeight = 16.sp
                    )
                }
            }
        }
    )
}

