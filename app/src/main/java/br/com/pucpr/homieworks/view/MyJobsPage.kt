package br.com.pucpr.homieworks.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import br.com.pucpr.homieworks.navigation.Screen
import br.com.pucpr.homieworks.view.util.GenericPage
import br.com.pucpr.homieworks.view.util.SearchBar
import br.com.pucpr.homieworks.view.util.SessionHeader
import br.com.pucpr.homieworks.viewmodel.MyJobsViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyJobsPage(
    viewModel: MyJobsViewModel,
    navController: NavController
) {
    var option by remember { mutableStateOf("feed") }

    GenericPage(
        { MyJobsHeader(
//                selectedOption = option,
            onProfileIconClick = {
                navController.navigate(Screen.Profile.route) {
                    popUpTo(Screen.MyJobs.route) { inclusive = true }
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
            MyJobsContent(
                onCardClick = {
                    navController.navigate(Screen.JobDetails.route) {
                        popUpTo(Screen.MyJobs.route) { inclusive = true }
                    }
                },
                onAddJobClick = {
                    navController.navigate(Screen.NewJob.route) {
                        popUpTo(Screen.MyJobs.route) { inclusive = true }
                    }
                },
            ) },
        { MyJobsFooter() }
    )
}

@Composable
fun MyJobsHeader(
//    selectedOption: String,
    onProfileIconClick: () -> Unit,
    onMenuOptionSelected: (String) -> Unit
) {
    SessionHeader(
        text = "Meus anúncios",
//        selectedMenuOption = selectedOption,
        onProfileIconClick = onProfileIconClick,
        onMenuIconClick = onMenuOptionSelected
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyJobsContent(onCardClick: () -> Unit, onAddJobClick: () -> Unit) {
//    val jobs = remember {
//        List(100) { i ->
//            Job(
//                title = "Título do trabalho",
//                description = "Cortar a grama",
//                serviceDatetime = "30/07/2025 13:30"
//            )
//        }
//    }
//
//    InfiniteAutoScrollList(
//        items = jobs,
//        scrollDelayMs = 3000L,
//        itemContent = { job ->
//            Card(job = job, onCardClik = onCardClick)
//        },
//        onAddJobClick = { onAddJobClick() }
//    )
}

@Composable
fun MyJobsFooter() {
    SearchBar()
}