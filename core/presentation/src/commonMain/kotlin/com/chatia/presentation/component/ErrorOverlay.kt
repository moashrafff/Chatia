package com.chatia.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil3.compose.AsyncImage
import com.chatia.domain.model.ErrorMessage
import com.chatia.presentation.model.UiText
import com.chatia.presentation.model.asString
import com.chatia.presentation.resources.Res
import com.chatia.ui.components.buttons.PrimaryButton
import com.chatia.ui.components.texts.PrimaryText
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ErrorOverlay(
    errorTitle: UiText = UiText.empty(),
    errorMessage: ErrorMessage,
    retryActionText: UiText = UiText.ok(),
    retryAction: () -> Unit = {}
) {
    var showDialog by remember { mutableStateOf(true) }
    fun dismissDialog() {
        showDialog = false
    }
    if (showDialog) {
        Dialog(
            onDismissRequest = { },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false,
            ),
            content = {
                Box(
                    modifier = Modifier
                        .wrapContentSize()
                        .background(Color.White, shape = RoundedCornerShape(20.0.dp))
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        AsyncImage(
                            modifier = Modifier.size(88.dp),
                            model = Res.getUri("files/error_icon.svg"),
                            contentDescription = null,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        if (errorTitle != UiText.empty()){
                            PrimaryText(
                                text = errorTitle.asString(),
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Medium,
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        PrimaryText(
                            text = UiText.Dynamic(errorMessage.message).asString(),
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSecondary,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            PrimaryButton(
                                modifier = Modifier.fillMaxWidth().height(40.dp),
                                text = retryActionText.asString(),
                                onClick = {
                                    retryAction()
                                    dismissDialog()
                                },
                                textFontWeight = FontWeight.Medium,
                            )
                        }
                    }
                }
            },
        )
    }
}

@Preview
@Composable
fun ErrorOverLayPreview(){
    ErrorOverlay(errorTitle = UiText.Dynamic("Camera Error"), errorMessage = ErrorMessage(1,"Unable to access back camera. Please check permissions and settings."),  retryAction = {})
}