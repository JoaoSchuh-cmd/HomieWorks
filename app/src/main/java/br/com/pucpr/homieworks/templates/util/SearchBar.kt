package br.com.pucpr.homieworks.templates.util

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.pucpr.homieworks.ui.theme.lightCean
import br.com.pucpr.homieworks.ui.theme.mediumCean
import br.com.pucpr.homieworks.ui.theme.mediumRed

@Composable
fun SearchBar() {
    var text by remember { mutableStateOf("") }

    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = text,
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
          focusedContainerColor = lightCean,
          unfocusedContainerColor = lightCean,
          focusedTextColor = mediumCean,
          unfocusedTextColor = mediumCean,
          cursorColor = mediumCean,
          focusedIndicatorColor = Color.Transparent,
          unfocusedIndicatorColor = Color.Transparent
        ),
        label = {Text("Pesquisar telefone, trabalho, nome...", color = mediumCean)},
        onValueChange = {text = it},
        trailingIcon = { Icon(
            imageVector = Icons.Rounded.Search,
            contentDescription = "Ícone de pesquisa",
            tint = mediumRed,
            modifier = Modifier.size(28.dp)
        )}
    )
}