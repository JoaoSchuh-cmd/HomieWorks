package br.com.pucpr.homieworks.templates.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.pucpr.homieworks.data.Job
import br.com.pucpr.homieworks.ui.theme.darkCean
import br.com.pucpr.homieworks.ui.theme.mediumCean
import br.com.pucpr.homieworks.ui.theme.superLightCean
import br.com.pucpr.homieworks.ui.theme.yellow

@Composable
fun Card(
    job: Job,
    backgroundColor: Color = mediumCean,
    withHelpeditsAndData: Boolean = true
) {
    val fontColor = Color.White

    androidx.compose.material3.Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor, contentColor = fontColor)
    ) {
        CardHeader(withHelpeditsAndData, job)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(color = superLightCean, shape = RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center,
        ) {
            Text(job.description, style = MaterialTheme.typography.titleMedium, color = darkCean)
        }
    }
}

@Composable
fun CardHeader(withHelpeditsAndData: Boolean = true, job: Job) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(1.5f)
        ) {
            Row {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Ícone de conta pessoal",
                    tint = Color.White,
                    modifier = Modifier.size(46.dp)
                )
                Column {
                    Text(
                        text = job.userName,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = job.userAddress,
                        style = MaterialTheme.typography.titleSmall,
                    )
                }
            }
        }
        if (withHelpeditsAndData == true) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.End),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Text(
                        text = "10",
                        style = MaterialTheme.typography.titleLarge,
                        color = yellow,
                        fontWeight = FontWeight.Bold,
                    )
                    Icon(
                        imageVector = Icons.Filled.ThumbUp,
                        contentDescription = "Ícone de positivo",
                        tint = yellow
                    )
                }
                Text(
                    modifier = Modifier
                        .align(Alignment.End),
                    text = "Data: ${job.data}",
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}