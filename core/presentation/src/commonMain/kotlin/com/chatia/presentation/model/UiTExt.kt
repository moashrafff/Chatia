package com.chatia.presentation.model

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.PluralStringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.pluralStringResource

/**
 * Cross-platform text holder for Compose Multiplatform.
 *
 * Use:
 * - [Dynamic] for raw text.
 * - [Resource] for string resources with optional format args.
 * - [Plural] for plural resources with count + optional format args.
 */
sealed class UiText {
    data class Dynamic(val value: String) : UiText()

    data class Resource(
        val res: StringResource,
        val args: List<Any> = emptyList()
    ) : UiText()

    data class Plural(
        val res: PluralStringResource,
        val count: Int,
        val args: List<Any> = emptyList()
    ) : UiText()

    companion object {
        fun empty() = Dynamic("")
        fun ok() = Dynamic("ok")
    }
}

/** Resolve [UiText] to a localized String. Must be called in a @Composable context. */
@Composable
fun UiText.asString(): String = when (this) {
    is UiText.Dynamic -> value
    is UiText.Resource ->
        if (args.isEmpty()) stringResource(res)
        else stringResource(res, *args.toTypedArray())
    is UiText.Plural ->
        if (args.isEmpty()) pluralStringResource(res, count)
        else pluralStringResource(res, count, *args.toTypedArray())
}