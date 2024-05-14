package com.codelab.basiclayouts.ui.viewmodel.shared

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelab.basiclayouts.data.RetrofitInstance
import com.codelab.basiclayouts.model.Profile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(Profile())
    val state = _state.asStateFlow()
    fun onChangeUsername(newValue: String) = _state.update { it.copy(username = newValue) }
    fun onChangePassword(newValue: String) = _state.update { it.copy(password = newValue) }
    fun onChangeComfirmPassword(newValue: String) = _state.update { it.copy(confirmPassword = newValue) }
    fun onChangeEmail(newValue: String) = _state.update { it.copy(email = newValue) }


    fun ResetPassword() {
        viewModelScope.launch {
            try {
                // 创建一个 Map，包含读者ID
//                val params = mapOf("userId" to 1)
                // 调用挂起函数
                val Result = RetrofitInstance.tUserService.profileInsert(_state.value)
                // 更新状态
            } catch (e: Exception) {
                e.printStackTrace()
                // 在此处可以设置错误状态或采取其他行动
            }
        }
    }

    private val _activeScreen = MutableStateFlow("ForgotPasswordScreen")
    val activeScreen = _activeScreen.asStateFlow()

    fun setActiveScreen(screenName: String) {
        _activeScreen.value = screenName
    }
}