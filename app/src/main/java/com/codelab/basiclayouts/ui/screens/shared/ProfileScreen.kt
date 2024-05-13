package com.codelab.basiclayouts.ui.screens.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.codelab.basiclayouts.R
import com.codelab.basiclayouts.model.Profile
import com.codelab.basiclayouts.model.SelectedSex
import com.codelab.basiclayouts.ui.components.DefaultButton
import com.codelab.basiclayouts.ui.components.Header
import com.codelab.basiclayouts.ui.components.MyTextField
import com.codelab.basiclayouts.ui.components.ProfileAvatar
import com.codelab.basiclayouts.ui.components.SexOptions
import com.codelab.basiclayouts.ui.components.SexOptionsTextField
import com.codelab.basiclayouts.ui.components.TextButton
import com.codelab.basiclayouts.ui.viewmodel.shared.ProfileViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    ProfileContent(
        state = state,
        onChangeUsername = viewModel::onChangeUsername,
        onChangeRealName = viewModel::onChangeRealName,
        onChangeDescription = viewModel::onChangeDescription,
        onChangeEmail = viewModel::onChangeEmail,
        onChangePhone = viewModel::onChangePhone,
        onSaveUserInfo = viewModel::onSaveUserInfo,
    )
}

@Composable
private fun ProfileContent(
    state: Profile,
    onChangeUsername: (String) -> Unit,
    onChangeRealName: (String) -> Unit,
    onChangeDescription: (String) -> Unit,
    onChangeEmail: (String) -> Unit,
    onChangePhone: (String) -> Unit,
    onSaveUserInfo: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(
            title = stringResource(R.string.account),
            subtitle = stringResource(R.string.account_subtitle)
        )
        Spacer(modifier = Modifier.height(32.dp))

        ProfileAvatar(
            painter = rememberAsyncImagePainter(model = state.profilePictureLink),
            size = 128
        )
        Spacer(modifier = Modifier.height(24.dp))

        TextButton(text = stringResource(R.string.change_profile_picture)) {}
        Spacer(modifier = Modifier.height(32.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1F)) {
                MyTextField(
                    labelVal = "Username",
                    icon = R.drawable.share_lockperson,
                    onTextChange = onChangeUsername
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box(modifier = Modifier.weight(1F)) {
                MyTextField(
                    labelVal = "Real_name",
                    icon = R.drawable.share_lockperson,
                    onTextChange = onChangeRealName
                )
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.weight(1F)) {
                SexOptions(
                    selectedSex = SelectedSex.selectedSex,
                    onSexSelected = { sex ->
                        SelectedSex.selectedSex.value = sex
                    }
                )
                SexOptionsTextField(
                    labelVal = "Sex",
                    icon = R.drawable.share_lockperson,
                )
            }
        }
        MyTextField(
            labelVal = "Description",
            icon = R.drawable.share_lockperson,
            onTextChange = onChangeDescription
        )
        MyTextField(
            labelVal = "Email",
            icon = R.drawable.share_at_symbol,
            onTextChange = onChangeEmail
        )
        MyTextField(
            labelVal = "Phone",
            icon = R.drawable.share_lockphone,
            onTextChange = onChangePhone
        )

        Spacer(modifier = Modifier.weight(1F))
        DefaultButton(
            buttonText = "Save",
            onClick = onSaveUserInfo
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    ProfileScreen()
}