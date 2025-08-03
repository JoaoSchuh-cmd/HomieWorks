package br.com.pucpr.homieworks.templates.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.pucpr.homieworks.ui.theme.mediumCean
import kotlinx.coroutines.delay

@Composable
fun <T> InfiniteAutoScrollList(
    items: List<T>,
    itemContent: @Composable (T) -> Unit,
    scrollDelayMs: Long = 2000L
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

    BoxWithConstraints {
        val height = maxHeight * 0.9f

        LazyColumn(
            state = listState,
            modifier = Modifier
                .height(height)
                .background(color = backgroundColor, shape = RoundedCornerShape(16.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(items) { item ->
                itemContent(item)
            }
        }
    }

}