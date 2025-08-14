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
import br.com.pucpr.homieworks.view.util.SessionHeader
import br.com.pucpr.homieworks.view.util.InfiniteAutoScrollList
import br.com.pucpr.homieworks.view.util.SearchBar
import br.com.pucpr.homieworks.viewmodel.FeedViewModel

@Composable
fun FeedPage(
    viewModel: FeedViewModel,
    navController: NavController
) {
    var option by remember { mutableStateOf("feed") }

    GenericPage(
        {
            FeedHeader(
                onProfileIconClick = {
                    navController.navigate(Screen.Profile.route) {
                        popUpTo(Screen.Feed.route) { inclusive = true }
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
            )
        },
        {
            FeedContent(
                onCardClick = {
                    navController.navigate(Screen.JobDetails.route) {
                        popUpTo(Screen.Feed.route) { inclusive = true }
                    }
                },
                onAddJobClick = {
                    navController.navigate(Screen.NewJob.route) {
                        popUpTo(Screen.Feed.route) { inclusive = true }
                    }
                }
            )
        },
        { FeedFooter() }
    )
}

@Composable
fun FeedHeader(
    onProfileIconClick: () -> Unit,
    onMenuOptionSelected: (String) -> Unit
) {
    SessionHeader(
        text = "Feed",
        onProfileIconClick = onProfileIconClick,
        onMenuIconClick = onMenuOptionSelected
    )
}


@Composable
fun FeedContent(onCardClick: () -> Unit, onAddJobClick: () -> Unit) {
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
            Card(job, onCardClik = onCardClick)
        },
        onAddJobClick = { onAddJobClick() }
    )
}

@Composable
fun FeedFooter() {
    SearchBar()
}