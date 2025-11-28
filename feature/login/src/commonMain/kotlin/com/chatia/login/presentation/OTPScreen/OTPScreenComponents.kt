package com.chatia.login.presentation.OTPScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun OTPFeilds(otpNumbersState: MutableList<String>) {
    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }
    var number3 by remember { mutableStateOf("") }
    var number4 by remember { mutableStateOf("") }

    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        NumberInputBox(
            value = number1,
            onValueChange = { number1 = it })
        NumberInputBox(
            value = number2,
            onValueChange = { number2 = it })
        NumberInputBox(
            value = number3,
            onValueChange = { number3 = it })
        NumberInputBox(
            value = number4,
            onValueChange = { number4 = it })
//        for (otpNumber in 0 until otpNumbersState.size) {
//            NumberInputBox(
//                value = otpNumbersState[otpNumber],
//                onValueChange = { otpNumbersState[otpNumber] = it })
//        }
    }
}

@Composable
fun NumberInputBox(
    value: String,
    onValueChange: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .size(width = 80.dp, height = 60.dp)
            .clip(RoundedCornerShape(50))
            .background(Color.White)
            .border(1.dp, Color.White, RoundedCornerShape(50)),
        contentAlignment = Alignment.Center
    ) {
        TextField(
            value = value,
            onValueChange = { newValue ->
                if (newValue.length <= 1) {
                    onValueChange(newValue)
                }
            },
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                disabledTextColor = Color.Gray,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}
