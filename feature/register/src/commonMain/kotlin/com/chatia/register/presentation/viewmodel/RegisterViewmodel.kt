package com.chatia.register.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chatia.presentation.stateRenderer.StateRenderer
import com.chatia.register.domain.usecase.RegisterUseCase
import com.chatia.register.presentation.error.RegisterUIError
import com.chatia.register.presentation.model.RegisterUIModel
import com.chatia.register.presentation.protocol.RegisterEffect
import com.chatia.register.presentation.protocol.RegisterIntent
import com.chatia.register.presentation.protocol.RegisterUiState
import com.chatia.register.presentation.validation.RegisterValidator
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegisterViewmodel(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
    private var registerState = RegisterUiState()

    private val _stateRendererMutableState =
        MutableStateFlow<StateRenderer<RegisterUiState, RegisterUIModel>>(
            StateRenderer.ScreenContent(registerState)
        )
    val stateRendererFlow: StateFlow<StateRenderer<RegisterUiState, RegisterUIModel>> get() = _stateRendererMutableState

    private val _viewEffect: Channel<RegisterEffect> = Channel()
    val viewEffect = _viewEffect.receiveAsFlow()

    private inline fun updateState(crossinline reducer: RegisterUiState.() -> RegisterUiState) {
        registerState = registerState.reducer()
        validate()
    }

    private fun sendEffect(effect: () -> RegisterEffect) {
        viewModelScope.launch {
            _viewEffect.send(effect())
        }
    }

    private fun validate() {
        val emailError: RegisterUIError =
            RegisterValidator.emailError(registerState.registerUIModel.email)
        val phoneError: RegisterUIError =
            RegisterValidator.phoneError(registerState.registerUIModel.phone)
        val passwordError: RegisterUIError =
            RegisterValidator.passwordError(registerState.registerUIModel.password)
        val confirmPasswordError: RegisterUIError =
            RegisterValidator.confirmPasswordError(
                registerState.registerUIModel.password,
                registerState.registerUIModel.confirmPassword
            )
        val isRegisterButtonEnabled: Boolean =
            RegisterValidator.isRegisterValid(
                passwordError = passwordError,
                confirmPasswordError = confirmPasswordError,
                emailError = emailError,
                phoneError = phoneError
            )

        registerState = registerState.copy(
            isRegisterButtonEnabled = isRegisterButtonEnabled,
            passwordError = passwordError,
            confirmPasswordError = confirmPasswordError,
            emailError = emailError,
            phoneError = phoneError
        )

        val newStateRenderer =
            StateRenderer.ScreenContent<RegisterUiState, RegisterUIModel>(registerState)
        _stateRendererMutableState.value = newStateRenderer
    }

    fun sendIntent(intent: RegisterIntent) {
        when (intent) {
            is RegisterIntent.RegisterButtonClicked -> {
                register()
            }

            is RegisterIntent.AlreadyHaveAccountClicked -> sendEffect { RegisterEffect.NavigateToLogin }
            is RegisterIntent.ConfirmPasswordUpdated -> updateState {
                copy(
                    registerUIModel = registerState.registerUIModel.copy(
                        confirmPassword = intent.confirmPassword
                    )
                )
            }

            is RegisterIntent.EmailUpdated -> updateState {
                copy(
                    registerUIModel = registerState.registerUIModel.copy(
                        email = intent.email
                    )
                )
            }

            is RegisterIntent.PasswordUpdated -> updateState {
                copy(
                    registerUIModel = registerState.registerUIModel.copy(
                        password = intent.password
                    )
                )
            }

            is RegisterIntent.PhoneUpdated -> updateState {
                copy(
                    registerUIModel = registerState.registerUIModel.copy(
                        phone = intent.phone
                    )
                )
            }

            is RegisterIntent.UserNameUpdated -> updateState {
                copy(
                    registerUIModel = registerState.registerUIModel.copy(
                        userName = intent.userName
                    )
                )
            }

            is RegisterIntent.CountryUpdated -> updateState {
                copy(
                    registerUIModel = registerState.registerUIModel.copy(
                        country = intent.country
                    )
                )
            }

            is RegisterIntent.ShowCountryDialog -> updateState {
                copy(
                    showCountryDialog = intent.showCountryDialog
                )
            }
        }
    }

    private fun register() {
        viewModelScope.launch {
            _stateRendererMutableState.update {
                StateRenderer.LoadingScreen(registerState)
            }
            registerUseCase.execute(
                input = RegisterUseCase.Input(
                    username = registerState.registerUIModel.userName,
                    password = registerState.registerUIModel.password,
                    email = registerState.registerUIModel.email,
                    phoneNumber = registerState.registerUIModel.country.dialCode.toString()
                            + registerState.registerUIModel.phone,

                    ),
                success = { response ->
                    _stateRendererMutableState.value =
                        StateRenderer.Success(registerState.registerUIModel)
                },
                error = { errorMessage ->
                    _stateRendererMutableState.value =
                        StateRenderer.ErrorPopup(registerState, errorMessage = errorMessage)
                }
            )
        }
    }
}
