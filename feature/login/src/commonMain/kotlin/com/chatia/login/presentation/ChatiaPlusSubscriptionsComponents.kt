package com.chatia.login.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cahatia.feature.login.generated.resources.upgrade_to_plus
import com.chatia.presentation.models.UiText
import com.chatia.presentation.models.asString
import com.chatia.ui.components.buttons.PrimaryButton
import com.chatia.ui.components.cards.PrimaryCard
import com.chatia.ui.components.texts.PrimaryText


@Composable
fun ListContent() {
    LazyColumn(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
        items(listOFPlans) { plan ->
            PrimaryCard(
                modifier = Modifier.padding(5.dp),
                withElevation = false,
                cardColors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(20.dp),
                enabled = false,
            ) {
                CardContent(plan.title, plan.description, plan.price)
            }
        }
    }
    PrimaryButton(
        modifier = Modifier.height(52.dp).fillMaxWidth().padding(horizontal = 18.dp),
        text = UiText.Resource(cahatia.feature.login.generated.resources.Res.string.upgrade_to_plus)
            .asString(),
        onClick = { /*TODO*/ },
        textFontWeight = FontWeight.Normal,
        textFontSize = 16.sp
    )
}

@Composable
fun CardContent(title: String, description: String, price: String) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(18.dp),
    ) {
        PrimaryText(text = title, fontWeight = FontWeight.Medium , fontSize = 18.sp)
        PrimaryText(text = description, fontWeight = FontWeight.Normal , fontSize = 12.sp)
        PrimaryText(text = price , color = Color.Red)
    }
}

data class SubscriptionsPlanItem(
    var title: String,
    var description: String,
    var price: String
)

var listOFPlans = listOf(
    SubscriptionsPlanItem(
        "Basic Plan",
        "Perfect for personal use with basic features and 100 monthly messages",
        "\$4.99/month"
    ),
    SubscriptionsPlanItem(
        "Pro Plan",
        "Designed for small businesses with team features , analytics and 500 messages per month",
        "\$19.99/month"
    ),
    SubscriptionsPlanItem(
        "Enterprise Plan",
        "Built for team and organizations including team collaboration tools , analytica ,priority support , and 1000 monthly messages",
        "\$19.99/month"
    )
)