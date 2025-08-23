package br.com.pucpr.homieworks.view.util

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Notes
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.pucpr.homieworks.model.Job
import br.com.pucpr.homieworks.model.User
import br.com.pucpr.homieworks.ui.theme.darkCean
import br.com.pucpr.homieworks.ui.theme.superLightCean
import br.com.pucpr.homieworks.ui.theme.yellow
import br.com.pucpr.homieworks.util.SessionManager
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Card(
    user: User? = SessionManager.sessionUser!!,
    job: Job? = null,
    backgroundColor: Color = darkCean,
    withHelpeditsAndData: Boolean = true,
    onCardClik: () -> Unit,
) {
    val fontColor = Color.White

    androidx.compose.material3.Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .shadow(elevation = 16.dp, shape = RoundedCornerShape(10.dp)),
        elevation = CardDefaults.cardElevation(24.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(
            width = 2.dp,
            color = Color.White,
        ),
        colors = CardDefaults.cardColors(containerColor = backgroundColor, contentColor = fontColor),
        onClick = { onCardClik() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp)
                .background(color = backgroundColor)
                .align(Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            CardHeader(withHelpeditsAndData, user!!, job)
            CardJobTitle(job!!)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CardHeader(withHelpeditsAndData: Boolean = true, user: User? = SessionManager.sessionUser!!, job: Job? = null) {
    val fontColor = superLightCean
    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.weight(1.8f)
        ) {
            Row {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Ícone de conta pessoal",
                    tint = fontColor,
                    modifier = Modifier.size(46.dp)
                )
                Column {
                    Text(
                        text = user!!.name,
                        style = MaterialTheme.typography.titleMedium,
                        color = fontColor
                    )
                    Text(
                        text = "Rua ${user.addressStreet}, ${user.addressNum}, ${user.addressCity} - ${user.addressState}",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White
                    )
                }
            }
        }
        if (withHelpeditsAndData) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.End),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Text(
                        text = job?.helpedits.toString(),
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
                    text = if (job != null) "Data: ${job.serviceDatetime.format(dateTimeFormatter)}" else "Data: __/__/____",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun CardJobTitle(job: Job) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.TopStart),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Notes,
                tint = superLightCean,
                contentDescription = "Ícone de título",
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = "Trabalho:",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.labelSmall,
                color = superLightCean
            )
        }

        Text(
            text = job.title,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}