package br.com.pucpr.homieworks.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.pucpr.homieworks.view.AcceptedJobDetailsPage
import br.com.pucpr.homieworks.view.AcceptedJobsPage
import br.com.pucpr.homieworks.view.NewJobPage
import br.com.pucpr.homieworks.view.ProfilePage
import br.com.pucpr.homieworks.viewmodel.AcceptedJobDetailsViewModel
import br.com.pucpr.homieworks.viewmodel.AcceptedJobsViewModel
import br.com.pucpr.homieworks.viewmodel.NewJobViewModel
import br.com.pucpr.homieworks.viewmodel.ProfileViewModel

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.mainNavGraph(navController: NavHostController) {
    composable(Screen.NewJob.route) {
        val viewModel: NewJobViewModel = viewModel()
        NewJobPage(
            viewModel = viewModel,
            navController = navController,
        )
    }

    composable(Screen.Accepted.route) {
        val viewModel: AcceptedJobsViewModel = viewModel()
        AcceptedJobsPage(viewModel = viewModel, navController = navController)
    }

    composable(Screen.AcceptedDetails.route) {
        val viewModel: AcceptedJobDetailsViewModel = viewModel()
        AcceptedJobDetailsPage(viewModel = viewModel, navController = navController)
    }

    composable(Screen.Profile.route) {
        val viewModel: ProfileViewModel = viewModel()
        ProfilePage(viewModel = viewModel, navController = navController)
    }
}
