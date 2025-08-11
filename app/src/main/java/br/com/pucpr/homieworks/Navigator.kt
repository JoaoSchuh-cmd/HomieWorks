package br.com.pucpr.homieworks

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.pucpr.homieworks.templates.NewJobPage
import br.com.pucpr.homieworks.data.Job
import br.com.pucpr.homieworks.templates.AcceptedJobsDetailsPage
import br.com.pucpr.homieworks.templates.AcceptedJobsPage
import br.com.pucpr.homieworks.templates.FeedPage
import br.com.pucpr.homieworks.templates.JobDetailsPage
import br.com.pucpr.homieworks.templates.LoginPage
import br.com.pucpr.homieworks.templates.MyJobsPage
import br.com.pucpr.homieworks.templates.ProfilePage
import br.com.pucpr.homieworks.templates.RecoveryPage
import br.com.pucpr.homieworks.templates.SignUpPage

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    val job = Job(
        title = "Título do trabalho",
        userName = "joao_schuh",
        description = "Uma descrição curta do trabalho a ser realizado.",
        userAddress = "Rua fictícia, 999, M.C.R - PR",
        data = "31/07/2052"
    )

    NavHost(navController = navController, startDestination = Screen.Feed.route) {
        composable(Screen.Login.route) {
            LoginPage(
                onLoginSuccess = { navController.navigate(Screen.Feed.route) },
                onForgotPassword = { navController.navigate(Screen.Recovery.route) },
                onSignUp = { navController.navigate(Screen.SignUp.route) }
            )
        }
        composable(Screen.Feed.route) {
            FeedPage(
                onCardClick = { navController.navigate(Screen.JobDetails.route) },
                onProfileIconClick = { navController.navigate(Screen.Profile.route) },
                onMenuIconClick = { option: String ->
                    when(option) {
                        "feed" -> { navController.navigate(Screen.Feed.route) }
                        "myjobs" -> { navController.navigate(Screen.MyJobs.route) }
                        "acceptedjobs" -> { navController.navigate(Screen.Accepted.route) }
                    }
                },
                onAddJobClick = { navController.navigate(Screen.NewJob.route) }
            )
        }
        composable(Screen.Recovery.route) {
            RecoveryPage(
                onAlreadyHaveAccount = { navController.navigate(Screen.Login.route) },
                onSuccssesRecovery = { navController.navigate(Screen.Login.route) }
            )
        }
        composable(Screen.MyJobs.route) {
            MyJobsPage(
                onCardClick = { navController.navigate(Screen.JobDetails.route) },
                onProfileIconClick = { navController.navigate(Screen.Profile.route) },
                onMenuIconClick = { option: String ->
                    when(option) {
                        "feed" -> { navController.navigate(Screen.Feed.route) }
                        "myjobs" -> { navController.navigate(Screen.MyJobs.route) }
                        "acceptedjobs" -> { navController.navigate(Screen.Accepted.route) }
                    }
                },
                onAddJobClick = { navController.navigate(Screen.NewJob.route) }
            )
        }
        composable(Screen.NewJob.route) {
            NewJobPage(
                onProfileIconClick = { navController.navigate(Screen.Profile.route) },
                onMenuIconClick = { option: String ->
                    when(option) {
                        "feed" -> { navController.navigate(Screen.Feed.route) }
                        "myjobs" -> { navController.navigate(Screen.MyJobs.route) }
                        "acceptedjobs" -> { navController.navigate(Screen.Accepted.route) }
                    }
                },
            )
        }
        composable(Screen.JobDetails.route) {
            JobDetailsPage(
                job,
                onBackClick = { navController.navigate(Screen.Feed.route) }
            )
        }
        composable(Screen.SignUp.route) {
            SignUpPage(
                onAlreadyHaveAccount = { navController.navigate(Screen.Login.route) },
                onCancel = { navController.navigate(Screen.Login.route) },
                onSignUpSuccess = { navController.navigate(Screen.Feed.route) }
            )
        }
        composable(Screen.Profile.route) {
            ProfilePage(
                onProfileIconClick = { navController.navigate(Screen.Profile.route) },
                onMenuIconClick = { option: String ->
                    when(option) {
                        "feed" -> { navController.navigate(Screen.Feed.route) }
                        "myjobs" -> { navController.navigate(Screen.MyJobs.route) }
                        "acceptedjobs" -> { navController.navigate(Screen.Accepted.route) }
                    }
                },
            )
        }
        composable(Screen.Accepted.route) {
            AcceptedJobsPage(
                onCardClick = { navController.navigate(Screen.AcceptedDetails.route) },
                onProfileIconClick = { navController.navigate(Screen.Profile.route) },
                onMenuIconClick = { option: String ->
                    when(option) {
                        "feed" -> { navController.navigate(Screen.Feed.route) }
                        "myjobs" -> { navController.navigate(Screen.MyJobs.route) }
                        "acceptedjobs" -> { navController.navigate(Screen.Accepted.route) }
                    }
                },
            )
        }
        composable(Screen.AcceptedDetails.route) {
            AcceptedJobsDetailsPage(
                job = job,
                onBackClick = { navController.navigate(Screen.Accepted.route) }
            )
        }
    }
}