package br.com.pucpr.homieworks.view.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.pucpr.homieworks.ui.theme.darkCean
import br.com.pucpr.homieworks.ui.theme.yellow
import br.com.pucpr.homieworks.util.SessionManager

@Composable
fun SessionHeader(
    text: String,
    isComplete: Boolean = true,
    onProfileIconClick: () -> Unit,
    onMenuIconClick: (String) -> Unit
) {
    val fontColor = Color.White
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .weight(2f),
            verticalAlignment = Alignment.Top
        ) {
            IconButton(
                content = {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Ícone de menu",
                        tint = fontColor,
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false},
                        containerColor = Color.White,
                    ) {
                        DropdownMenuItem(
                            text = { Text(text = "Feed", color = darkCean, style = MaterialTheme.typography.titleSmall) },
                            onClick = {
                                onMenuIconClick("feed")
                                expanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text = "Meus anúncios", color = darkCean, style = MaterialTheme.typography.titleSmall) },
                            onClick = {
                                onMenuIconClick("myjobs")
                                expanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text = "Trabalhos aceitos", color = darkCean, style = MaterialTheme.typography.titleSmall) },
                            onClick = {
                                onMenuIconClick("acceptedjobs")
                                expanded = false
                            }
                        )
                    }
                },
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.CenterVertically),
                onClick = { expanded = !expanded }
            )
            Text(
                text = text,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = fontColor,
                modifier = Modifier.weight(1f)
            )
        }

        if (isComplete) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Text(
                        "Helpedits:",
                        style = MaterialTheme.typography.titleMedium,
                        color = fontColor,
                        fontWeight = FontWeight.Bold
                    )
                    Row {
                        Text(SessionManager.sessionUser!!.helpedits.toString(), fontSize = 20.sp, color = yellow, fontWeight = FontWeight.Bold)
                        Icon(
                            imageVector = Icons.Default.Handshake,
                            tint = yellow,
                            contentDescription = "Ícone de aperto de mãos"
                        )
                    }

                }
                IconButton(
                    content = {
                        Icon(
                            imageVector = Icons.Rounded.AccountCircle,
                            contentDescription = "Ícone de pessoa",
                            tint = Color.White,
                            modifier = Modifier.fillMaxSize()
                        )
                    },
                    onClick = { onProfileIconClick() }
                )
            }
        }
    }
}