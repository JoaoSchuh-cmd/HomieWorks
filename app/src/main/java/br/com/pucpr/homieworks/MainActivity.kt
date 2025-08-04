package br.com.pucpr.homieworks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import br.com.pucpr.homieJobs.templates.NewJobPage
import br.com.pucpr.homieworks.data.Job
import br.com.pucpr.homieworks.ui.theme.HomieWorksTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HomieWorksTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val job = Job(
                        userName = "joao_schuh",
                        description = "Uma descrição curta do trabalho a ser realizado.",
                        userAddress = "Rua fictícia, 999, M.C.R - PR",
                        data = "31/07/2052"
                    )
                    NewJobPage()
                }
            }
        }
    }
}
