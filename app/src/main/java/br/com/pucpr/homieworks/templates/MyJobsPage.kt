package br.com.pucpr.homieworks.templates

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import br.com.pucpr.homieworks.data.Job
import br.com.pucpr.homieworks.templates.util.Card
import br.com.pucpr.homieworks.templates.util.GenericPage
import br.com.pucpr.homieworks.templates.util.InfiniteAutoScrollList
import br.com.pucpr.homieworks.templates.util.SearchBar
import br.com.pucpr.homieworks.templates.util.SessionHeader

@Composable
fun MyJobsPage(
    onCardClick: () -> Unit,
    onProfileIconClick: () -> Unit,
    onMenuIconClick: () -> Unit,
    onAddJobClick: () -> Unit
) {
    GenericPage(
        { MyJobsHeader(onProfileIconClick, onMenuIconClick) },
        { MyJobsContent(onCardClick, onAddJobClick) },
        { MyJobsFooter() }
    )
}

@Composable
fun MyJobsHeader(onProfileIconClick: () -> Unit, onMenuIconClick: () -> Unit) {
    SessionHeader("Meus anúncios", onProfileIconClick = onProfileIconClick, onMenuIconClick = onMenuIconClick)
}

@Composable
fun MyJobsContent(onCardClick: () -> Unit, onAddJobClick: () -> Unit) {
    val jobs = remember {
        List(100) { i ->
            Job(
                title = "Título do trabalho",
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
            Card(job = job, onCardClik = onCardClick)
        },
        onAddJobClick = { onAddJobClick() }
    )
}

@Composable
fun MyJobsFooter() {
    SearchBar()
}