package com.chatia.login.presentation.component

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
import cahatia.feature.login.generated.resources.Res
import cahatia.feature.login.generated.resources.create_an_account
import cahatia.feature.login.generated.resources.dont_have_account
import com.chatia.presentation.model.UiText
import com.chatia.presentation.model.asString

@Composable
fun CreateAccountAnnotatedText(onCreateAccountClicked: () -> Unit) {
    val createAccountText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )
        ) {
            append(UiText.Resource(Res.string.dont_have_account).asString())
        }

        withStyle(
            style = SpanStyle(
                color = Color(0xFF0062FF),
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )
        ) {
            append(UiText.Resource(Res.string.create_an_account).asString())
        }
    }
    Text(
        modifier = Modifier.clickable {onCreateAccountClicked.invoke()},
        text = createAccountText
    )
}