package com.chatia.login.presentation.model

import cahatia.feature.login.generated.resources.Res
import cahatia.feature.login.generated.resources.calender_access
import cahatia.feature.login.generated.resources.calender_access_description
import cahatia.feature.login.generated.resources.calender_icon
import cahatia.feature.login.generated.resources.gallery_access
import cahatia.feature.login.generated.resources.gallery_access_description
import cahatia.feature.login.generated.resources.gallery_icon
import cahatia.feature.login.generated.resources.location_access
import cahatia.feature.login.generated.resources.location_access_description
import cahatia.feature.login.generated.resources.location_icon
import cahatia.feature.login.generated.resources.login_title
import cahatia.feature.login.generated.resources.notification_access
import cahatia.feature.login.generated.resources.notification_access_description
import cahatia.feature.login.generated.resources.quote_up_circle
import com.chatia.presentation.model.UiText
import org.jetbrains.compose.resources.DrawableResource

enum class PermissionsInfoType(val title: UiText, val icon: DrawableResource, val description: UiText) {

    NOTIFICATIONS_ACCESS(
        title = UiText.Resource(Res.string.notification_access),
        icon = Res.drawable.quote_up_circle,
        description = UiText.Resource(Res.string.notification_access_description)
    ),
    CALENDER_ACCESS (
    title = UiText.Resource(Res.string.calender_access),
    icon = Res.drawable.calender_icon,
    description =UiText.Resource(Res.string.calender_access_description)
    ),
    LOCATION_ACCESS(
        title = UiText.Resource(Res.string.location_access),
        icon = Res.drawable.location_icon,
        description =UiText.Resource(Res.string.location_access_description)
    ),
    GALLERY_OR_FILE_ACCESS(
        title = UiText.Resource(Res.string.gallery_access),
        icon = Res.drawable.gallery_icon,
        description =UiText.Resource(Res.string.gallery_access_description)
    )
}