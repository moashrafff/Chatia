package com.chatia.onBoarding.di

import com.chatia.onBoarding.presentation.viewmodel.OnBoardingViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val onBoardingModule = module {
    viewModel {
        OnBoardingViewModel()
    }
}