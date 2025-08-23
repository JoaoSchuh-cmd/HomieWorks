package br.com.pucpr.homieworks

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import br.com.pucpr.homieworks.navigation.AppNavHost
import br.com.pucpr.homieworks.ui.theme.HomieWorksTheme
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)

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


