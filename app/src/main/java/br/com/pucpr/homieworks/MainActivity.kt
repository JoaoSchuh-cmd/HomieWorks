package br.com.pucpr.homieworks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.pucpr.homieJobs.templates.NewJobPage
import br.com.pucpr.homieworks.data.Job
import br.com.pucpr.homieworks.templates.FeedPage
import br.com.pucpr.homieworks.templates.JobDetailsPage
import br.com.pucpr.homieworks.templates.LoginPage
import br.com.pucpr.homieworks.templates.MyJobsPage
import br.com.pucpr.homieworks.templates.ProfilePage
import br.com.pucpr.homieworks.templates.RecoveryPage
import br.com.pucpr.homieworks.templates.SignUpPage
import br.com.pucpr.homieworks.ui.theme.HomieWorksTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HomieWorksTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNavHost()
                }

            }
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = Screen.Recovery.route) {
        composable(Screen.Login.route) {
            LoginPage(
                onLoginSuccess = { navController.navigate(Screen.Feed.route) },
                onForgotPassword = { navController.navigate(Screen.Recovery.route) },
                onSignUp = { navController.navigate(Screen.SignUp.route) }
            )
        }
        composable(Screen.Feed.route) {
            FeedPage()
        }
        composable(Screen.Recovery.route) {
            RecoveryPage(
                onAlreadyHaveAccount = { navController.navigate(Screen.Login.route) },
                onSuccssesRecovery = { navController.navigate(Screen.Login.route) }
            )
        }
        composable(Screen.MyJobs.route) {
            MyJobsPage()
        }
        composable(Screen.NewJob.route) {
            NewJobPage()
        }
        composable(Screen.JobDetails.route) {
            val job = Job(
                userName = "joao_schuh",
                description = "Uma descrição curta do trabalho a ser realizado.",
                userAddress = "Rua fictícia, 999, M.C.R - PR",
                data = "31/07/2052"
            )
            JobDetailsPage(job)
        }
        composable(Screen.SignUp.route) {
            SignUpPage(
                onAlreadyHaveAccount = { navController.navigate(Screen.Login.route) },
                onCancel = { navController.navigate(Screen.Login.route) },
                onSignUpSuccess = { navController.navigate(Screen.Feed.route) }
            )
        }
        composable(Screen.Profile.route) {
            ProfilePage()
        }
    }
}

