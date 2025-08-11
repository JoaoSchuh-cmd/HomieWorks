package br.com.pucpr.homieworks.templates

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import br.com.pucpr.homieworks.data.Job
import br.com.pucpr.homieworks.templates.util.Card
import br.com.pucpr.homieworks.templates.util.GenericPage
import br.com.pucpr.homieworks.templates.util.InfiniteAutoScrollList
import br.com.pucpr.homieworks.templates.util.SearchBar
import br.com.pucpr.homieworks.templates.util.SessionHeader

@Composable
fun AcceptedJobsPage(
    onCardClick: () -> Unit,
    onProfileIconClick: () -> Unit,
    onMenuIconClick: (String) -> Unit,
) {
    var option by remember { mutableStateOf("accepted") }

    GenericPage(
        header = { AcceptedJobsHeader(
//                selectedOption = option,
            onProfileIconClick = onProfileIconClick,
            onMenuOptionSelected = { selected ->
                option = selected
                onMenuIconClick(selected)
            }
        ) },
        content = { AcceptedJobsContent(onCardClick) },
        footer = { AcceptedJobsFooter() }
    )
}

@Composable
fun AcceptedJobsHeader(
//    selectedOption: String,
    onProfileIconClick: () -> Unit,
    onMenuOptionSelected: (String) -> Unit
) {
    SessionHeader(
        text = "Trabalhos aceitos",
//        selectedMenuOption = selectedOption,
        onProfileIconClick = onProfileIconClick,
        onMenuIconClick = onMenuOptionSelected
    )
}

@Composable
fun AcceptedJobsContent(onCardClick: () -> Unit) {
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
        onAddJobClick = {}
    )
}

@Composable
fun AcceptedJobsFooter() {
    SearchBar()
}
