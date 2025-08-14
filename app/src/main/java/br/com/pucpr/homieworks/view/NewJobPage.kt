package br.com.pucpr.homieworks.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.pucpr.homieworks.Screen
import br.com.pucpr.homieworks.model.Job
import br.com.pucpr.homieworks.view.util.CardHeader
import br.com.pucpr.homieworks.view.util.GenericButton
import br.com.pucpr.homieworks.view.util.GenericPage
import br.com.pucpr.homieworks.view.util.InputText
import br.com.pucpr.homieworks.view.util.SessionHeader
import br.com.pucpr.homieworks.ui.theme.darkCean
import br.com.pucpr.homieworks.ui.theme.lightGreen
import br.com.pucpr.homieworks.ui.theme.lightRed
import br.com.pucpr.homieworks.ui.theme.mediumCean
import br.com.pucpr.homieworks.ui.theme.superLightCean
import br.com.pucpr.homieworks.ui.theme.yellow
import br.com.pucpr.homieworks.viewmodel.NewJobViewModel

@Composable
fun NewJobPage(
    viewModel: NewJobViewModel,
    navController: NavController
) {
    var option by remember { mutableStateOf("newjob") }

    var jobTitle by remember { mutableStateOf("") }
    var jobHelpedits by remember { mutableIntStateOf(0) }
    var jobDate by remember { mutableStateOf("") }
    var jobDescription by remember { mutableStateOf("") }

    GenericPage(
        { NewJobHeader(
            onProfileIconClick = {
                navController.navigate(Screen.NewJob.route) {
                    popUpTo(Screen.NewJob.route) { inclusive = true }
                }
            },
            onMenuOptionSelected = { selected ->
                option = selected
                when(option) {
                    "feed" -> { navController.navigate(Screen.Feed.route) }
                    "myjobs" -> { navController.navigate(Screen.MyJobs.route) }
                    "acceptedjobs" -> { navController.navigate(Screen.Accepted.route) }
                }
            }
        ) },
        {
            NewJobContent(
                jobTitle = jobTitle,
                onJobTitleChange = { jobTitle = it },
                jobHelpedits = jobHelpedits.toString(),
                onJobHelpedits = { jobHelpedits = it.toInt() },
                jobDate = jobDate,
                onJobDateChange = { jobDate = it },
                jobDescription = jobDescription,
                onJobDescription = { jobDescription = it }
            )
        },
        { NewJobFooter() }
    )
}

@Composable
fun NewJobHeader(
    onProfileIconClick: () -> Unit,
    onMenuOptionSelected: (String) -> Unit
) {
    SessionHeader(
        text = "Novo trabalho",
        onProfileIconClick = onProfileIconClick,
        onMenuIconClick = onMenuOptionSelected
    )
}

@Composable
fun NewJobContent(
    jobTitle: String,
    onJobTitleChange: (String) -> Unit,
    jobHelpedits: String,
    onJobHelpedits: (String) -> Unit,
    jobDate: String,
    onJobDateChange: (String) -> Unit,
    jobDescription: String,
    onJobDescription: (String) -> Unit
) {
    val job = Job(
        title = "Título do trabalho",
        userName= "João Schuh",
        userAddress = "Rua fictícia, 295, M.C.R - PR",
        data = "",
        description = ""
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
            .background(color = mediumCean, shape = RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        CardHeader(job = job, withHelpeditsAndData = false)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = superLightCean, shape = RoundedCornerShape(16.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            NewJobForm(
                jobTitle,
                onJobTitleChange,
                jobHelpedits,
                onJobHelpedits,
                jobDate,
                onJobDateChange,
                jobDescription,
                onJobDescription
            )
        }

        GenericButton(
            text =  "Para mais detalhes",
            textColor = Color.White,
            containerColor = darkCean,
            modifier = Modifier.fillMaxWidth(),
            icon = {
                Icon(
                    imageVector = Icons.Default.Whatsapp,
                    tint = Color.White,
                    contentDescription = "Ícone do whatsapp"
                )
            }
        )
    }
}

@Composable
fun NewJobForm(
    jobTitle: String,
    onJobTitleChange: (String) -> Unit,
    jobHelpedits: String,
    onJobHelpedits: (String) -> Unit,
    jobDate: String,
    onJobDateChange: (String) -> Unit,
    jobDescription: String,
    onJobDescription: (String) -> Unit
) {
    val backgroundColor = darkCean
    val fontColor = Color.White

    InputText(
        label = "Título do trabalho",
        value = jobTitle,
        onValueChange = onJobTitleChange,
        isSecret = false,
        backGroundColor = backgroundColor,
        fontColor = fontColor
    )
    InputText(
        label = "Valor(Helpedits)",
        value = jobHelpedits,
        onValueChange = onJobTitleChange,
        isSecret = false,
        backGroundColor = backgroundColor,
        fontColor = fontColor,
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Handshake,
                tint = yellow,
                contentDescription = "Ícone de aperto de mãos"
            )
        }
    )
    InputText(
        label = "Data",
        value = jobDate,
        onValueChange = onJobDateChange,
        isDate = true,
        backGroundColor = backgroundColor,
        fontColor = fontColor,
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.CalendarMonth,
                tint = fontColor,
                contentDescription = "Ícone de calendário"
            )
        }
    )
    InputText(
        label = "Descrição do trabalho",
        value = jobDescription,
        onValueChange = onJobDescription,
        modifier = Modifier.fillMaxHeight(),
        backGroundColor = backgroundColor,
        fontColor = fontColor
    )
}

@Composable
fun NewJobFooter() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        GenericButton(
            text = "Descartar",
            textColor = Color.White,
            containerColor = lightRed,
            modifier = Modifier.weight(1f),
            icon = {
                Icon(
                    imageVector = Icons.Default.Delete,
                    tint = Color.White,
                    contentDescription = "Ícone de lixeira"
                )
            }
        )
        GenericButton(
            text = "Criar",
            textColor = Color.White,
            containerColor = lightGreen,
            modifier = Modifier.weight(1f),
            icon = {
                Icon(
                    imageVector = Icons.Default.Save,
                    tint = Color.White,
                    contentDescription = "Ícone de disquete"
                )
            }
        )
    }
}