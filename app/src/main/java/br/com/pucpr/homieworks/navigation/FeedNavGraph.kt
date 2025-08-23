package br.com.pucpr.homieworks.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import br.com.pucpr.homieworks.ui.theme.darkCean
import br.com.pucpr.homieworks.view.FeedPage
import br.com.pucpr.homieworks.view.JobDetailsPage
import br.com.pucpr.homieworks.viewmodel.FeedViewModel
import br.com.pucpr.homieworks.viewmodel.JobDetailViewModel

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.feedNavGraph(navController: NavHostController) {
    navigation(
        startDestination = Screen.Feed.route,
        route = Screen.FeedGraph.route
    ) {
        composable(Screen.Feed.route) { backStackEntry ->
            val feedViewModel: FeedViewModel = hiltViewModel(backStackEntry)
            FeedPage(
                viewModel = feedViewModel,
                navController = navController
            )
        }

        composable(
            route = Screen.JobDetails.route,
            arguments = listOf(navArgument("jobId") { type = NavType.LongType })
        ) { backStackEntry ->
            val jobId = backStackEntry.arguments?.getLong("jobId")

            val parentEntry = remember(backStackEntry ) {
                navController.getBackStackEntry(Screen.Feed.route)
            }
            val feedViewModel: FeedViewModel = hiltViewModel(parentEntry)
            val jobDetailViewModel: JobDetailViewModel = hiltViewModel()

            if (feedViewModel.jobsList == null) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(darkCean)
                        .padding(40.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.align(Alignment.CenterHorizontally))
                    Text(
                        text = "Carregando...",
                        color = Color.White,
                        style = MaterialTheme.typography.displaySmall,
                        fontStyle = FontStyle.Italic
                    )
                }
            } else {
                val job = feedViewModel.getJobById(jobId)
                if (job != null) {
                    JobDetailsPage(
                        viewModel = jobDetailViewModel,
                        job = job,
                        navController = navController
                    )
                } else {
                    Text("Job não encontrado.")
                }
            }
        }
    }
}
