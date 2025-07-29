package br.com.pucpr.homieworks.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.pucpr.homieworks.data.Job
import br.com.pucpr.homieworks.templates.util.Card
import br.com.pucpr.homieworks.templates.util.SessionHeader
import br.com.pucpr.homieworks.templates.util.InfiniteAutoScrollList
import br.com.pucpr.homieworks.templates.util.SearchBar
import br.com.pucpr.homieworks.ui.theme.mediumCean
import br.com.pucpr.homieworks.ui.theme.yeallow

@Composable
fun FeedPage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = mediumCean)
            .padding(horizontal = 16.dp, vertical = 30.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            FeedHeader()
            FeedContent()
            FeedFooter()
        }
    }
}

@Composable
fun FeedHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SessionHeader("Feed")
        Row(
            modifier = Modifier
                .wrapContentWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Column {
                Text("Helpedits:", style = MaterialTheme.typography.titleMedium, color = Color.Black, fontWeight = FontWeight.Bold)
                Text("200", fontSize = 20.sp, color = yeallow, fontWeight = FontWeight.Bold)
            }
            Icon(
                imageVector = Icons.Rounded.AccountCircle,
                contentDescription = "Ícone de pessoa",
                tint = Color.White,
                modifier = Modifier
                    .size(50.dp)
            )
        }
    }
}

@Composable
fun FeedContent() {
    val jobs = remember {
        List(100) { i ->
            Job(
                userName = "Teste",
                userAddress = "Teste de endereço",
                description = "Cortar a grama",
                data = "30/07/2025 13:30"
            )
        }
    }

    InfiniteAutoScrollList(
        items = jobs,
        scrollDelayMs = 3000L,
        itemContent = { job ->
            Card(job)
        }
    )

}

@Composable
fun FeedFooter() {
    SearchBar()
}