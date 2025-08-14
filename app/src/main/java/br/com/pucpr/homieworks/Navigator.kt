package br.com.pucpr.homieworks

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.pucpr.homieworks.view.NewJobPage
import br.com.pucpr.homieworks.model.Job
import br.com.pucpr.homieworks.view.AcceptedJobsDetailsPage
import br.com.pucpr.homieworks.view.AcceptedJobsPage
import br.com.pucpr.homieworks.view.FeedPage
import br.com.pucpr.homieworks.view.JobDetailsPage
import br.com.pucpr.homieworks.view.LoginPage
import br.com.pucpr.homieworks.view.MyJobsPage
import br.com.pucpr.homieworks.view.ProfilePage
import br.com.pucpr.homieworks.view.SignUpPage
import br.com.pucpr.homieworks.viewmodel.AcceptedJobsDetailsViewModel
import br.com.pucpr.homieworks.viewmodel.AcceptedJobsViewModel
import br.com.pucpr.homieworks.viewmodel.FeedViewModel
import br.com.pucpr.homieworks.viewmodel.JobDetailViewModel
import br.com.pucpr.homieworks.viewmodel.LoginViewModel
import br.com.pucpr.homieworks.viewmodel.MyJobsViewModel
import br.com.pucpr.homieworks.viewmodel.NewJobViewModel
import br.com.pucpr.homieworks.viewmodel.ProfileViewModel
import br.com.pucpr.homieworks.viewmodel.SignUpViewModel

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    val job = Job(
        title = "Título do trabalho",
        userName = "joao_schuh",
        description = "Uma descrição curta do trabalho a ser realizado.",
        userAddress = "Rua fictícia, 999, M.C.R - PR",
        data = "31/07/2052"
    )

    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            val loginViewModel: LoginViewModel = viewModel()
            LoginPage(
                viewModel = loginViewModel,
                navController = navController
            )
        }
//        composable(Screen.Recovery.route) {
//            RecoveryPage(
//                onAlreadyHaveAccount = { navController.navigate(Screen.Login.route) },
//                onSuccssesRecovery = { navController.navigate(Screen.Login.route) }
//            )
//        }
        composable(Screen.SignUp.route) {
            val signUpViewModel: SignUpViewModel = viewModel()
            SignUpPage(
                viewModel = signUpViewModel,
                navController = navController
            )
        }
        composable(Screen.Feed.route) {
            val feedViewModel: FeedViewModel = viewModel()
            FeedPage(
                viewModel = feedViewModel,
                navController = navController
            )
        }
        composable(Screen.MyJobs.route) {
            val myJobsViewModel: MyJobsViewModel = viewModel()
            MyJobsPage(
                viewModel = myJobsViewModel,
                navController = navController
            )
        }
        composable(Screen.NewJob.route) {
            val newJobViewModel: NewJobViewModel = viewModel()
            NewJobPage(
                viewModel = newJobViewModel,
                navController = navController
            )
        }
        composable(Screen.JobDetails.route) {
            val jobDetailViewModel: JobDetailViewModel = viewModel()
            JobDetailsPage(
                job,
                viewModel = jobDetailViewModel,
                navController = navController
            )
        }
        composable(Screen.Accepted.route) {
            val acceptedJobsViewModel: AcceptedJobsViewModel = viewModel()
            AcceptedJobsPage(
                viewModel = acceptedJobsViewModel,
                navController = navController
            )
        }
        composable(Screen.AcceptedDetails.route) {
            val acceptedJobsDetailsViewModel: AcceptedJobsDetailsViewModel = viewModel()
            AcceptedJobsDetailsPage(
                job = job,
                viewModel = acceptedJobsDetailsViewModel,
                navController = navController
            )
        }
        composable(Screen.Profile.route) {
            val profileViewModel: ProfileViewModel = viewModel()
            ProfilePage(
                viewModel = profileViewModel,
                navController = navController
            )
        }
    }
}