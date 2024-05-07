package com.codelab.basiclayouts.ui.viewmodel.shared

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ResetPasswordViewModel : ViewModel() {
    var password by mutableStateOf("")
    var confirmPassword by mutableStateOf("")
}