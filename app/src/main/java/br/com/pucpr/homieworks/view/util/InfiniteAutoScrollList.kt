package br.com.pucpr.homieworks.view.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.pucpr.homieworks.ui.theme.darkCean
import br.com.pucpr.homieworks.ui.theme.mediumCean
import kotlinx.coroutines.delay

@Composable
fun <T> InfiniteAutoScrollList(
    items: List<T>,
    itemContent: @Composable (T) -> Unit,
    scrollDelayMs: Long = 2000L,
    onAddJobClick: () -> Unit
) {
    val listState = rememberLazyListState()
    val backgroundColor = mediumCean

    LaunchedEffect(items) {
        var currentIndex = 0
        while (true) {
            delay(scrollDelayMs)
            currentIndex = (currentIndex + 1) % items.size
            listState.animateScrollToItem(currentIndex)
        }
    }

    Box {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxHeight(0.9f)
                .background(color = backgroundColor, shape = RoundedCornerShape(16.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items) { item ->
                itemContent(item)
            }
        }

        FloatingActionButton(
            onClick = { onAddJobClick() },
            content = {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Adicionar item",
                    tint = darkCean,
                    modifier = Modifier.size(26.dp),
                )
            },
            containerColor = Color.White,
            contentColor = darkCean,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
        )
    }
}