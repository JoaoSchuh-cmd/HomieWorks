package br.com.pucpr.homieworks.templates

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import br.com.pucpr.homieworks.data.Job
import br.com.pucpr.homieworks.templates.util.Card
import br.com.pucpr.homieworks.templates.util.GenericPage
import br.com.pucpr.homieworks.templates.util.SessionHeader
import br.com.pucpr.homieworks.templates.util.InfiniteAutoScrollList
import br.com.pucpr.homieworks.templates.util.SearchBar

@Composable
fun FeedPage(
    onCardClick: () -> Unit,
    onProfileIconClick: () -> Unit,
    onMenuIconClick: (String) -> Unit,
    onAddJobClick: () -> Unit
) {
    var option by remember { mutableStateOf("feed") }

    GenericPage(
        {
            FeedHeader(
                onProfileIconClick = onProfileIconClick,
                onMenuOptionSelected = { selected ->
                    option = selected
                    onMenuIconClick(selected)
                }
            )
        },
        { FeedContent(onCardClick, onAddJobClick) },
        { FeedFooter() }
    )
}


@Composable
fun FeedHeader(
    onProfileIconClick: () -> Unit,
    onMenuOptionSelected: (String) -> Unit
) {
    SessionHeader(
        text = "Feed",
        onProfileIconClick = onProfileIconClick,
        onMenuIconClick = onMenuOptionSelected
    )
}


@Composable
fun FeedContent(onCardClick: () -> Unit, onAddJobClick: () -> Unit) {
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
            Card(job, onCardClik = onCardClick)
        },
        onAddJobClick = { onAddJobClick() }
    )
}

@Composable
fun FeedFooter() {
    SearchBar()
}