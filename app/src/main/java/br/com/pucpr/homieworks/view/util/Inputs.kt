package br.com.pucpr.homieworks.view.util

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import java.util.Calendar

@Composable
fun InputText(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    leadingIcon: (@Composable (() -> Unit))? = null,
    trailingIcon: (@Composable (() -> Unit))? = null,
    isSecret: Boolean = false,
    isDate: Boolean = false,
    backGroundColor: Color,
    fontColor: Color = Color.White
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isDate) {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = remember {
                android.app.DatePickerDialog(
                    context,
                    { _, selectedYear, selectedMonth, selectedDay ->
                       onValueChange("%02d/%02d/%04d".format(selectedDay, selectedMonth + 1, selectedYear))
                    },
                    year,
                    month,
                    day
                )
            }

            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(color = backGroundColor, shape = RoundedCornerShape(16.dp))
                    .clickable { datePickerDialog.show() }
                    .padding(16.dp)
            ) {
                Text(
                    text = label,
                    color = fontColor.copy(alpha = 0.7f),
                    style = MaterialTheme.typography.labelMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = value.ifBlank { "Selecionar data" },
                    color = fontColor,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            TextField(
                value = value,
                onValueChange = onValueChange,
                label = { Text(
                    text = label,
                    color = fontColor.copy(alpha = 0.7f),
                    style = MaterialTheme.typography.labelMedium
                ) },
                modifier = modifier.fillMaxWidth(),
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
                trailingIcon = trailingIcon,
                visualTransformation = if (isSecret) PasswordVisualTransformation() else VisualTransformation.None
            )
        }
    }
}
