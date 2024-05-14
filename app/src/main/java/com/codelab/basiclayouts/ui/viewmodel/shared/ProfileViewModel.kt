package com.codelab.basiclayouts.ui.viewmodel.shared

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codelab.basiclayouts.data.RetrofitInstance
import com.codelab.basiclayouts.model.Profile
import com.codelab.basiclayouts.ui.uistate.author.StoryAU
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel  @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(
//        Profile(
//            userId = 8,
//            username = "alex",
//            realName = "zzz",
//            selfDescription = "good",
//            email = "@",
//            telephone = "111",
//            password = "111",
//            confirmPassword = "111",
//            profilePictureLink = "https://i.pinimg.com/564x/20/0f/50/200f509408e5ae122d1a45d110f2faa2.jpg"
//        )
        Profile()
    )
    val state = _state.asStateFlow()

    init {
        getUserInfo()
//        readProfile()
    }

    fun saveProfile() {
        viewModelScope.launch {
            try {
                // 创建一个 Map，包含读者ID
                val params = mapOf("userId" to 1)
                // 调用挂起函数
                val Result = RetrofitInstance.tUserService.profileInsert(_state.value)
                // 更新状态
            } catch (e: Exception) {
                e.printStackTrace()
                // 在此处可以设置错误状态或采取其他行动
            }
        }
    }

    fun readProfile() {
        viewModelScope.launch {
            try {
                // 创建一个 Map，包含读者ID
                val params = mapOf("userId" to 8)
                // 调用挂起函数
                val Result = RetrofitInstance.tUserService.selectProfileByUserId(params)
                // 更新状态
                _state.value=Result.data as Profile
                val i=0;
            } catch (e: Exception) {
                e.printStackTrace()
                // 在此处可以设置错误状态或采取其他行动
            }
        }
    }

    private fun getUserInfo() {
        _state.update {
            it.copy(
                profilePictureLink = "https://i.pinimg.com/564x/20/0f/50/200f509408e5ae122d1a45d110f2faa2.jpg"
            )
        }
    }

    fun onChangeUsername(newValue: String) = _state.update { it.copy(username = newValue) }

    fun onChangeRealName(newValue: String) = _state.update { it.copy(realName = newValue) }

    fun onChangeDescription(newValue: String) = _state.update { it.copy(selfDescription = newValue) }

    fun onChangeEmail(newValue: String) = _state.update { it.copy(email = newValue) }

    fun onChangePhone(newValue: String) = _state.update { it.copy(telephone = newValue) }

    fun trySave(){
        println(_state.value)
    }
    fun onSaveUserInfo() {
        TODO()
    }
}



