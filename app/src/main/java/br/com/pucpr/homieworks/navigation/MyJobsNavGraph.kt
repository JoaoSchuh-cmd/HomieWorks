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
import br.com.pucpr.homieworks.view.JobDetailsPage
import br.com.pucpr.homieworks.view.MyJobsPage
import br.com.pucpr.homieworks.view.NewJobPage
import br.com.pucpr.homieworks.viewmodel.JobDetailViewModel
import br.com.pucpr.homieworks.viewmodel.MyJobsViewModel
import br.com.pucpr.homieworks.viewmodel.NewJobViewModel

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.myjobsNavGraph(navController: NavHostController) {
    navigation(
        startDestination = Screen.MyJobs.route,
        route = Screen.MyJobsGraph.route
    ) {
        composable(Screen.MyJobs.route) { backStackEntry ->
            val viewModel: MyJobsViewModel = hiltViewModel(backStackEntry)
            MyJobsPage(
                viewModel = viewModel,
                navController = navController
            )
        }
        composable(
            route = Screen.NewJob.route,
            arguments = listOf(navArgument("jobId") { type = NavType.LongType })
        ) { backStackEntry ->
            val jobId = backStackEntry.arguments?.getLong("jobId")

            val parentEntry = remember(backStackEntry ) {
                navController.getBackStackEntry(Screen.MyJobs.route)
            }
            val myJobsViewModel: MyJobsViewModel = hiltViewModel(parentEntry)
            val newJobsViewModel: NewJobViewModel = hiltViewModel()

            if (myJobsViewModel.myJobsList == null) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(darkCean)
                        .padding(40.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.align(
                        Alignment.CenterHorizontally))
                    Text(
                        text = "Carregando...",
                        color = Color.White,
                        style = MaterialTheme.typography.displaySmall,
                        fontStyle = FontStyle.Italic
                    )
                }
            } else {
                val job = myJobsViewModel.getJobById(jobId)
                if (job != null) {
                    NewJobPage(
                        viewModel = newJobsViewModel,
                        job = job,
                        navController = navController,
                        screen = Screen.MyJobs.route
                    )
                } else {
                    Text("Job não encontrado.")
                }
            }
        }
    }
}