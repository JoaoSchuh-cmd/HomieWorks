package br.com.pucpr.homieworks.templates.util

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GenericButton(
    text: String,
    icon: (@Composable (() -> Unit))? = null,
    containerColor: Color,
    textColor: Color = Color.White,
    modifier: Modifier = Modifier.wrapContentWidth()
) {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            contentColor = textColor,
            containerColor = containerColor
        ),
        modifier = modifier
            .height(50.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        if (icon != null) {
            Spacer(modifier = Modifier.width(8.dp))
            icon()
        }
    }
}