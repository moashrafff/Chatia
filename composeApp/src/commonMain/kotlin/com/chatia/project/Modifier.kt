package com.chatia.project

import androidx.compose.ui.Modifier

inline fun Modifier.applyIf(
    condition: Boolean, modifier: Modifier.() -> Modifier
) = if (condition) this.then(modifier()) else this