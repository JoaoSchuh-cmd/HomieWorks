package br.com.pucpr.homieworks.navigation

sealed class Screen(
    val route: String
) {
    data object Login : Screen("login")
    data object SignUp : Screen("signup")
    data object Recovery : Screen("recovery")
    data object Feed : Screen("feed")
    data object MyJobs : Screen("myjobs")
    data object JobDetails : Screen("jobdetails/{jobId}") {
        fun createRoute(jobId: Long) = "jobdetails/$jobId"
    }
    data object NewJob : Screen("newjob/{jobId}") {
        fun createRoute(jobId: Long) = "newjob/$jobId"
    }
    data object Profile : Screen("profile")
    data object Accepted : Screen("accepted")
    data object  AcceptedDetails : Screen("accepteddetails")
    data object FeedGraph : Screen("feed_graph")
    data object MyJobsGraph : Screen("myjobs_graph")
}