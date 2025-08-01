package br.com.pucpr.homieworks.templates

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.pucpr.homieworks.data.Job
import br.com.pucpr.homieworks.templates.util.Card
import br.com.pucpr.homieworks.templates.util.InfiniteAutoScrollList
import br.com.pucpr.homieworks.templates.util.SearchBar
import br.com.pucpr.homieworks.templates.util.SessionHeader
import br.com.pucpr.homieworks.ui.theme.lightRed
import br.com.pucpr.homieworks.ui.theme.mediumCean

@Composable
fun MyJobsPage() {
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
            MyJobsHeader()
            MyJobsContent()
            MyJobsFooter()
        }
    }
}

@Composable
fun MyJobsHeader() {
    SessionHeader("Meus anúncios")
}

@Composable
fun MyJobsContent() {
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
            Card(
                job = job,
                backgroundColor = lightRed
            )
        },
    )
}

@Composable
fun MyJobsFooter() {
    SearchBar()
}