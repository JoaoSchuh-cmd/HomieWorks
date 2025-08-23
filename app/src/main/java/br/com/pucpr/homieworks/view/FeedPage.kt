package br.com.pucpr.homieworks.view

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import br.com.pucpr.homieworks.navigation.Screen
import br.com.pucpr.homieworks.model.Job
import br.com.pucpr.homieworks.view.util.Card
import br.com.pucpr.homieworks.view.util.GenericPage
import br.com.pucpr.homieworks.view.util.SessionHeader
import br.com.pucpr.homieworks.view.util.InfiniteAutoScrollList
import br.com.pucpr.homieworks.view.util.SearchBar
import br.com.pucpr.homieworks.viewmodel.FeedViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FeedPage(
    viewModel: FeedViewModel,
    navController: NavController
) {
    var option by remember { mutableStateOf("feed") }

    LaunchedEffect(Unit) {
        viewModel.loadJobs()
    }

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
                viewModel = viewModel,
                onCardClick = { selectedJob ->
                    viewModel.selectJob(selectedJob)
                    selectedJob.id?.let { id ->
                        navController.navigate(Screen.JobDetails.createRoute(id))
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


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FeedContent(viewModel: FeedViewModel, onCardClick: (Job) -> Unit, onAddJobClick: () -> Unit) {
    InfiniteAutoScrollList(
        items = viewModel.jobsList ?: emptyList(),
        scrollDelayMs = 3000L,
        itemContent = { job ->
            Card(job = job, onCardClik = { onCardClick(job) })
        },
        onAddJobClick = { onAddJobClick() }
    )
}

@Composable
fun FeedFooter() {
    SearchBar()
}