package com.codelab.basiclayouts.ui.viewmodel.shared

import androidx.lifecycle.ViewModel
import com.codelab.basiclayouts.model.Profile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class ProfileViewModel : ViewModel() {
    private val _state = MutableStateFlow(Profile())
    val state = _state.asStateFlow()

    init {
        getUserInfo()
    }

    private fun getUserInfo() =
        _state.update { it.copy(profilePictureLink = "https://i.pinimg.com/564x/20/0f/50/200f509408e5ae122d1a45d110f2faa2.jpg") }

    fun onChangeUsername(newValue: String) = _state.update { it.copy(username = newValue) }

    fun onChangeRealName(newValue: String) = _state.update { it.copy(realName = newValue) }

    fun onChangeDescription(newValue: String) = _state.update { it.copy(selfDescription = newValue) }

    fun onChangeEmail(newValue: String) = _state.update { it.copy(email = newValue) }

    fun onChangePhone(newValue: String) = _state.update { it.copy(telephone = newValue) }

    fun onSaveUserInfo() {
        TODO()
    }
}