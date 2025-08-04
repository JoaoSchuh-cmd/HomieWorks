package br.com.pucpr.homieworks.templates.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.pucpr.homieworks.ui.theme.yellow

@Composable
fun SessionHeader(
    text: String,
    is_complete: Boolean = true,
) {
    val fontColor = Color.White

    BoxWithConstraints {
        val width = maxWidth * 0.6f
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .width(width),
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                    contentDescription = "Ícone de flecha para trás",
                    tint = fontColor,
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.CenterVertically),
                )
                Text(
                    text = text,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = fontColor,
                    modifier = Modifier.weight(1f)
                )
            }

            if (is_complete) {
                Row(
                    modifier = Modifier
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
                            Text("200", fontSize = 20.sp, color = yellow, fontWeight = FontWeight.Bold)
                            Icon(
                                imageVector = Icons.Default.Handshake,
                                tint = yellow,
                                contentDescription = "Ícone de aperto de mãos"
                            )
                        }

                    }
                    Icon(
                        imageVector = Icons.Rounded.AccountCircle,
                        contentDescription = "Ícone de pessoa",
                        tint = Color.White,
                        modifier = Modifier
                            .size(50.dp)
                    )
                }
            }
        }
    }

}