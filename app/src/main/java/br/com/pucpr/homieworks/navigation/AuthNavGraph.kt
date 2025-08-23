package br.com.pucpr.homieworks.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import br.com.pucpr.homieworks.view.LoginPage
import br.com.pucpr.homieworks.view.SignUpPage
import br.com.pucpr.homieworks.viewmodel.LoginViewModel
import br.com.pucpr.homieworks.viewmodel.SignUpViewModel


fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    composable(Screen.Login.route) {
        val loginViewModel: LoginViewModel = viewModel()
        LoginPage(viewModel = loginViewModel, navController = navController)
    }
    composable(Screen.SignUp.route) {
        val signUpViewModel: SignUpViewModel = viewModel()
        SignUpPage(viewModel = signUpViewModel, navController = navController)
    }
//    composable(Screen.Recovery.route) {
//        val recoveryViewModel: RecoveryViewModel = viewModel()
//        RecoveryPage(viewModel = recoveryViewModel, navController = navController)
//    }
}