package br.com.pucpr.homieworks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import br.com.pucpr.homieworks.templates.FeedPage
import br.com.pucpr.homieworks.ui.theme.HomieWorksTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HomieWorksTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    FeedPage()
                }
            }
        }
    }
}
