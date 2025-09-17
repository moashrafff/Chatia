package com.chatia.navigator.viewmodel

import androidx.lifecycle.ViewModel
import com.chatia.navigator.core.AppNavigator

class AppNavigatorViewModel(
    private val appNavigator: AppNavigator,
) : ViewModel(), AppNavigator by appNavigator