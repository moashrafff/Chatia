package com.chatia.project.presentation.infoType

import cahatia.composeapp.generated.resources.Res
import cahatia.composeapp.generated.resources.bubble
import cahatia.composeapp.generated.resources.inspiration_any_time_description
import cahatia.composeapp.generated.resources.inspiration_any_time_title
import cahatia.composeapp.generated.resources.instant_answers_description
import cahatia.composeapp.generated.resources.instant_answers_title
import cahatia.composeapp.generated.resources.link_circle
import cahatia.composeapp.generated.resources.personalized_advice_description
import cahatia.composeapp.generated.resources.personalized_advice_title
import cahatia.composeapp.generated.resources.quote_up_circle
import com.chatia.project.UiText
import org.jetbrains.compose.resources.DrawableResource

enum class OnboardingInfoType(val title: UiText, val icon: DrawableResource, val description: UiText) {
    INSTANT_ANSWER(
        title = UiText.Resource(Res.string.instant_answers_title),
        icon = Res.drawable.quote_up_circle,
        description = UiText.Resource(Res.string.instant_answers_description)
    ),
    PERSONALIZED_ADVICE(
        title = UiText.Resource(Res.string.personalized_advice_title),
        icon = Res.drawable.link_circle,
        description = UiText.Resource(
            Res.string.personalized_advice_description
        )
    ),
    INSPIRATION_ANYTIME(
        title = UiText.Resource(Res.string.inspiration_any_time_title),
        icon = Res.drawable.bubble,
        description = UiText.Resource(
            Res.string.inspiration_any_time_description
        )
    )
}