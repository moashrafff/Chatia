package com.chatia.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.chatia.presentation.model.UiText
import com.chatia.presentation.model.asString

@Composable
fun AuthenticationAnnotatedText(firstText:UiText, secondText: UiText, onCreateAccountClicked: () -> Unit) {
    val createAccountText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
        ) {
            append(firstText.asString())
        }

        withStyle(
            style = SpanStyle(
                color = Color(0xFF0062FF),
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )
        ) {
            append(secondText.asString())
        }
    }
    Text(
        modifier = Modifier.clickable {onCreateAccountClicked.invoke()},
        text = createAccountText
    )
}