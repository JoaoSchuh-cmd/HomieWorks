package br.com.pucpr.homieworks

sealed class Screen(
    val route: String
) {
    data object Login : Screen("login")
    data object SignUp : Screen("signup")
    data object Recovery : Screen("recovery")
    data object Feed : Screen("feed")
    data object MyJobs : Screen("myjobs")
    data object JobDetails : Screen("jobdetails")
    data object NewJob : Screen("newjob")
    data object Profile : Screen("profile")
    data object Accepted : Screen("accepted")
    data object  AcceptedDetails : Screen("accepteddetails")
}