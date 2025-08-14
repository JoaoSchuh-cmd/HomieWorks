package br.com.pucpr.homieworks.view

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
import androidx.compose.material.icons.automirrored.filled.ArrowLeft
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.pucpr.homieworks.Screen
import br.com.pucpr.homieworks.model.Job
import br.com.pucpr.homieworks.view.util.CardHeader
import br.com.pucpr.homieworks.view.util.GenericButton
import br.com.pucpr.homieworks.view.util.GenericPage
import br.com.pucpr.homieworks.ui.theme.lightGreen
import br.com.pucpr.homieworks.ui.theme.lightRed
import br.com.pucpr.homieworks.ui.theme.mediumCean
import br.com.pucpr.homieworks.ui.theme.darkCean
import br.com.pucpr.homieworks.ui.theme.superLightCean
import br.com.pucpr.homieworks.viewmodel.JobDetailViewModel

@Composable
fun JobDetailsPage(
    job: Job,
    viewModel: JobDetailViewModel,
    navController: NavController
) {
    GenericPage(
        { JobDetailsHeader({
            navController.navigate(Screen.Feed.route){
                popUpTo(Screen.JobDetails.route) { inclusive = true }
            }
        }) },
        { JobDetailsContent(job) },
        { JobDetailsFooter() }
    )
}

@Composable
fun JobDetailsHeader(onBackClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.Top
    ) {
        IconButton(
            content = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowLeft,
                    contentDescription = "Ícone de flecha para trás",
                    tint = Color.White,
                )
            },
            modifier = Modifier
                .size(30.dp)
                .align(Alignment.CenterVertically),
            onClick = { onBackClick() }
        )
        Text(
            text = "Trabalho: detalhes",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.weight(1f)
        )
    }

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
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        GenericButton(
            text = "Recusar",
            icon = {Icon(
                imageVector = Icons.Default.Cancel,
                contentDescription = "Ícone de x",
                tint = Color.White
            )},
            containerColor = lightRed,
            modifier = Modifier.weight(1f)
        )
//        IconButton(
//            onClick = { onBackToFeedClick() },
//            content = {
//                Icon(
//                    imageVector = Icons.Default.ArrowDropDownCircle,
//                    tint = Color.Black,
//                    contentDescription = "Ícone de seta para baixo dentor de um círculo",
//                    modifier = Modifier.size(80.dp)
//                )
//            },
//            colors = IconButtonDefaults.iconButtonColors(
//                containerColor = superLightCean,
//            ),
//        )
        GenericButton(
            text = "Aceitar",
            icon = {Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Ícone de check",
                tint = Color.White
            )},
            containerColor = lightGreen,
            modifier = Modifier.weight(1f)
        )
    }
}