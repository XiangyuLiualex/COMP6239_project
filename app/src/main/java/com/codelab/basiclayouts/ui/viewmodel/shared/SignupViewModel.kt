package com.codelab.basiclayouts.ui.viewmodel.shared

import androidx.lifecycle.ViewModel
import com.codelab.basiclayouts.model.Profile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignupViewModel : ViewModel() {
    private val _state = MutableStateFlow(Profile())
    val state = _state.asStateFlow()
    fun onChangeUsername(newValue: String) = _state.update { it.copy(username = newValue) }

    fun onChangePassword(newValue: String) = _state.update { it.copy(password = newValue) }
    fun onChangeComfirmPassword(newValue: String) = _state.update { it.copy(confirmPassword = newValue) }

    fun onChangeEmail(newValue: String) = _state.update { it.copy(email = newValue) }

    fun onSaveUserInfo() {
        TODO()
    }
}