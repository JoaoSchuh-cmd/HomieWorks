package br.com.pucpr.homieworks.view

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.pucpr.homieworks.navigation.Screen
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
import br.com.pucpr.homieworks.util.SessionManager
import br.com.pucpr.homieworks.viewmodel.NewJobViewModel
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewJobPage(
    viewModel: NewJobViewModel,
    navController: NavController
) {
    var option by remember { mutableStateOf("newjob") }

    GenericPage(
        { NewJobHeader(
            onProfileIconClick = {
                navController.navigate(Screen.Profile.route) {
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
        { NewJobContent(viewModel) },
        { NewJobFooter(viewModel, navController) }
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewJobContent( viewModel: NewJobViewModel ) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
            .background(color = mediumCean, shape = RoundedCornerShape(16.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        CardHeader(user = SessionManager.sessionUser!!, withHelpeditsAndData = false)

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = superLightCean, shape = RoundedCornerShape(16.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            NewJobForm(viewModel = viewModel)
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewJobForm(viewModel: NewJobViewModel) {
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
    val backgroundColor = darkCean
    val fontColor = Color.White

    InputText(
        label = "Título do trabalho",
        value = viewModel.job.title,
        onValueChange = { viewModel.updateJobTitle(it) },
        isSecret = false,
        backGroundColor = backgroundColor,
        fontColor = fontColor
    )
    InputText(
        label = "Valor(Helpedits)",
        value = viewModel.job.helpedits.toString(),
        onValueChange = { viewModel.updateJobHelpedits(it.toInt()) },
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
        value =  viewModel.job.serviceDatetime.format(dateFormatter),
        onValueChange = {
            viewModel.updateJobServiceData(it)
        },
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
        value = viewModel.job.description,
        onValueChange = { viewModel.updateJobDescription(it) },
        modifier = Modifier.fillMaxHeight(),
        backGroundColor = backgroundColor,
        fontColor = fontColor
    )
}

@Composable
fun NewJobFooter(viewModel: NewJobViewModel, navController: NavController) {
    val loading = viewModel.loading
    val newJobSuccess = viewModel.newJobSuccess
    val newJobError = viewModel.newJobError

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        GenericButton(
            text = "Cancelar",
            textColor = Color.White,
            containerColor = lightRed,
            modifier = Modifier.weight(1f),
            icon = {
                Icon(
                    imageVector = Icons.Default.Cancel,
                    tint = Color.White,
                    contentDescription = "Ícone de cancelamento"
                )
            },
            onClick = { navController.navigate(Screen.Feed.route){
                popUpTo(Screen.NewJob.route) { inclusive = true }
            } }
        )
        GenericButton(
            text = if (loading) "Criando..." else "Criar",
            textColor = Color.White,
            containerColor = lightGreen,
            modifier = Modifier.weight(1f),
            icon = {
                Icon(
                    imageVector = Icons.Default.Save,
                    tint = Color.White,
                    contentDescription = "Ícone de disquete"
                )
            },
            onClick = {
                viewModel.register()
            }
        )

        if (newJobSuccess) {
            navController.navigate(Screen.Feed.route) {
                popUpTo(Screen.NewJob.route) { inclusive = true }
            }
        }
    }

    if (newJobError != null) {
        Text(
            text = "Erro ao cadastrar trabalho: $newJobError",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = lightRed
        )
    }
}