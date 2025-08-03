package br.com.pucpr.homieworks.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Whatsapp
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
import br.com.pucpr.homieworks.templates.util.GenericButton
import br.com.pucpr.homieworks.templates.util.GenericPage
import br.com.pucpr.homieworks.templates.util.SessionHeader
import br.com.pucpr.homieworks.ui.theme.lightGreen
import br.com.pucpr.homieworks.ui.theme.lightRed
import br.com.pucpr.homieworks.ui.theme.mediumCean
import br.com.pucpr.homieworks.ui.theme.darkCean
import br.com.pucpr.homieworks.ui.theme.superLightCean
import br.com.pucpr.homieworks.ui.theme.yellow

@Composable
fun JobDetailsPage(job: Job) {
    GenericPage(
        { JobDetailsHeader() },
        { JobDetailsContent(job) },
        { JobDetailsFooter() }
    )
}

@Composable
fun JobDetailsHeader() {
    SessionHeader("Trabalho")
}

@Composable
fun JobDetailsContent(job: Job) {
    BoxWithConstraints {
        val height = maxHeight * 0.9f

        Column(
            modifier = Modifier
                .height(height)
                .background(color = mediumCean, shape = RoundedCornerShape(16.dp))
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .background(color = superLightCean, shape = RoundedCornerShape(16.dp))
                    .padding(16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = "Título do serviço",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Descrição mais longa do serviço",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    GenericButton(
                        text = "Para mais detalhes",
                        containerColor = darkCean,
                        textColor = Color.White,
                        icon = {Icon(
                            imageVector = Icons.Default.Whatsapp,
                            contentDescription = "Ícone do whatsapp",
                            tint = Color.White,
                            modifier = Modifier.size(36.dp)
                        )},
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun JobDetailsFooter() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        GenericButton(
            "Recusar",
            icon = {Icon(
                imageVector = Icons.Default.Cancel,
                contentDescription = "Ícone de x",
                tint = Color.White
            )},
            containerColor = lightRed
        )
        GenericButton(
            "Aceitar",
            icon = {Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Ícone de check",
                tint = Color.White
            )},
            containerColor = lightGreen
        )
    }
}