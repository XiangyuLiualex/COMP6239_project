package code.ui.screens.shared

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.codelab.basiclayouts.R
import code.model.Profile
import code.model.SelectedSex
import code.ui.components.Header
import code.ui.components.MyTextField
import code.ui.components.ProfileAvatar
import code.ui.components.SexOptions
import code.ui.components.SexOptionsTextField
import code.ui.components.TextButton
import code.ui.viewmodel.shared.ProfileViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    ProfileContent(
        viewModel,
        navController = navController,
        state = state,
        onChangeUsername = viewModel::onChangeUsername,
        onChangeRealName = viewModel::onChangeRealName,
        onChangeDescription = viewModel::onChangeDescription,
        onChangeEmail = viewModel::onChangeEmail,
        onChangePhone = viewModel::onChangePhone,
        onSaveUserInfo = viewModel::onSaveUserInfo,
    )
}

@SuppressLint("UnrememberedMutableState")
@Composable
private fun ProfileContent(
    viewModel: ProfileViewModel,
    navController: NavHostController,
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
                    selectedSex = mutableStateOf(SelectedSex.selectedSex),
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
//        DefaultButton(
//            buttonText = "Save",
////            onClick = onSaveUserInfo
//            onClick = viewModel.trySave()
//        )
        Button(
            onClick = { viewModel.trySave()
                viewModel.saveProfile()
                      },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text(text = "Save")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    val navController = rememberNavController()
    ProfileScreen(navController = navController)
}