package br.com.pucpr.homieworks.templates

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import br.com.pucpr.homieworks.data.Job
import br.com.pucpr.homieworks.templates.util.Card
import br.com.pucpr.homieworks.templates.util.GenericPage
import br.com.pucpr.homieworks.templates.util.SessionHeader
import br.com.pucpr.homieworks.templates.util.InfiniteAutoScrollList
import br.com.pucpr.homieworks.templates.util.SearchBar

@Composable
fun FeedPage() {
    GenericPage(
        { FeedHeader() },
        { FeedContent() },
        { FeedFooter() }
    )
}

@Composable
fun FeedHeader() {
    SessionHeader("Feed")
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