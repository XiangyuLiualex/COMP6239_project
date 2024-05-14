package com.codelab.basiclayouts.ui.viewmodel.shared

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelab.basiclayouts.data.RetrofitInstance
import com.codelab.basiclayouts.model.Profile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _prove = MutableStateFlow(false)
    val prove = _prove.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _selectedIdentity = MutableStateFlow(Identity.READER)
    val selectedIdentity = _selectedIdentity.asStateFlow()

    fun onEmailChange(newEmail: String) {
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        _password.value = newPassword
    }

    fun onIdentityChange(newIdentity: Identity) {
        _selectedIdentity.value = newIdentity
    }
    fun readProfile() {
        viewModelScope.launch {
            try {
                // 创建一个 Map，包含读者ID
                val params = mapOf("email" to _email.value)
                // 调用挂起函数
                val Result = RetrofitInstance.tUserService.selectProfileByEmail(params)
                // 更新状态
//                _state.value=Result.data as Profile
                val isProve=(_password.value==Result.data?.password as String);
                _prove.value=isProve;

                val i=0;
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }



    fun checkValue(){
        println(_email.value)
        println(_password.value)
        println(_selectedIdentity.value)
    }
}

enum class Identity {
    READER,
    AUTHOR
}
