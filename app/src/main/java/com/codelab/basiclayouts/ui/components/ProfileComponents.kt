package com.codelab.basiclayouts.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codelab.basiclayouts.ui.theme.BrandColor
import com.codelab.basiclayouts.ui.theme.Primary
import com.codelab.basiclayouts.ui.theme.Tertirary

@Composable
fun DefaultButton(
    buttonText: String,
    onClick: () -> Unit,
) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = BrandColor
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = buttonText,
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.clickable { }
        )
    }
}

@Composable
fun ProfileAvatar(
    painter: Painter,
    size: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painter,
        contentDescription = "Profile Picture",
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .size(size.dp),
    )
}

@Composable
fun TextButton(
    text: String,
    onClick: () -> Unit
) {
    Text(
        text = text,
        color = BrandColor,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        modifier = Modifier.clickable { onClick() }
    )
}

@Composable
fun Header(
    title: String,
    subtitle: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = title,
            fontSize = 39.sp,
            color = Primary,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = subtitle,
            color = Tertirary,
            fontWeight = FontWeight.Normal
        )
    }
}

@Preview(widthDp = 360)
@Composable
fun PreviewHeader() {
    Header(title = "Account", subtitle = "Edit or manage your account")
}