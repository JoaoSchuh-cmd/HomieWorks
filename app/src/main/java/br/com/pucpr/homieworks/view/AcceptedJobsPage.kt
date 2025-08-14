package br.com.pucpr.homieworks.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import br.com.pucpr.homieworks.Screen
import br.com.pucpr.homieworks.model.Job
import br.com.pucpr.homieworks.view.util.Card
import br.com.pucpr.homieworks.view.util.GenericPage
import br.com.pucpr.homieworks.view.util.InfiniteAutoScrollList
import br.com.pucpr.homieworks.view.util.SearchBar
import br.com.pucpr.homieworks.view.util.SessionHeader
import br.com.pucpr.homieworks.viewmodel.AcceptedJobsViewModel

@Composable
fun AcceptedJobsPage(
    viewModel: AcceptedJobsViewModel,
    navController: NavController
) {
    var option by remember { mutableStateOf("accepted") }

    GenericPage(
        header = { AcceptedJobsHeader(
//                selectedOption = option,
            onProfileIconClick = {
                navController.navigate(Screen.Profile.route) {
                    popUpTo(Screen.Accepted.route) { inclusive = true }
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
        content = {
            AcceptedJobsContent {
                navController.navigate(Screen.AcceptedDetails.route) {
                    popUpTo(Screen.Accepted.route) { inclusive = true }
                }
            }
        },
        footer = { AcceptedJobsFooter() }
    )
}

@Composable
fun AcceptedJobsHeader(
//    selectedOption: String,
    onProfileIconClick: () -> Unit,
    onMenuOptionSelected: (String) -> Unit
) {
    SessionHeader(
        text = "Trabalhos aceitos",
//        selectedMenuOption = selectedOption,
        onProfileIconClick = onProfileIconClick,
        onMenuIconClick = onMenuOptionSelected
    )
}

@Composable
fun AcceptedJobsContent(onCardClick: () -> Unit) {
    val jobs = remember {
        List(100) { i ->
            Job(
                title = "Título do trabalho",
                userName = "Teste",
                userAddress = "Teste de endereço",
                description = "Cortar a grama",
                data = "30/07/2025 13:30"
            )
        }
    }

    InfiniteAutoScrollList(
        items = jobs,
        scrollDelayMs = 3000L,
        itemContent = { job ->
            Card(job = job, onCardClik = onCardClick)
        },
        onAddJobClick = {}
    )
}

@Composable
fun AcceptedJobsFooter() {
    SearchBar()
}
