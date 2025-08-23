package br.com.pucpr.homieworks.navigation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import br.com.pucpr.homieworks.api.Retrofit
import br.com.pucpr.homieworks.model.Job
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
                navController.getBackStackEntry(Screen.FeedGraph.route)
            }
            val feedViewModel: FeedViewModel = hiltViewModel(parentEntry)
            val jobDetailViewModel: JobDetailViewModel = hiltViewModel()

            if (feedViewModel.jobsList == null) {
                CircularProgressIndicator()
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
