package br.com.pucpr.homieworks.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
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
import br.com.pucpr.homieworks.templates.util.CardHeader
import br.com.pucpr.homieworks.templates.util.GenericButton
import br.com.pucpr.homieworks.templates.util.GenericPage
import br.com.pucpr.homieworks.templates.util.SessionHeader
import br.com.pucpr.homieworks.ui.theme.lightGreen
import br.com.pucpr.homieworks.ui.theme.lightRed
import br.com.pucpr.homieworks.ui.theme.mediumCean
import br.com.pucpr.homieworks.ui.theme.darkCean
import br.com.pucpr.homieworks.ui.theme.superLightCean

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
    Column(
        modifier = Modifier
            .fillMaxHeight(0.9f)
            .background(color = mediumCean, shape = RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        CardHeader(job = job)

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
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Whatsapp,
                            contentDescription = "Ícone do whatsapp",
                            tint = Color.White,
                            modifier = Modifier.size(36.dp)
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
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