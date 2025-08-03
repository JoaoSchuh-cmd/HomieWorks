package br.com.pucpr.homieworks.templates.util

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun InputText(
    label: String,
    leadingIcon: (@Composable (() -> Unit))? = null,
    isSecret: Boolean = false,
    backGroundColor: Color,
    fontColor: Color = Color.White,
) {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = text,
            onValueChange = {text = it},
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = backGroundColor,
                unfocusedContainerColor = backGroundColor,
                focusedTextColor = fontColor,
                unfocusedTextColor = fontColor,
                focusedLabelColor = fontColor,
                unfocusedLabelColor = fontColor,
                cursorColor = fontColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            leadingIcon = leadingIcon,
            visualTransformation = if (isSecret) PasswordVisualTransformation() else VisualTransformation.None
        )
    }
}
