package br.com.pucpr.homieworks.view.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.pucpr.homieworks.ui.theme.darkCean
import br.com.pucpr.homieworks.ui.theme.mediumCean
import br.com.pucpr.homieworks.ui.theme.superLightCean
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
    val coroutineScope = rememberCoroutineScope()
    var currentIndex by remember{ mutableStateOf(0) }

    LaunchedEffect(items) {
        if (items.isNotEmpty()) {
            while (true) {
                delay(scrollDelayMs)
                currentIndex = (currentIndex + 1) % items.size
                listState.animateScrollToItem(currentIndex)
            }
        }
    }

    Box {
        if (items.isNotEmpty()) {
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxHeight(0.9f)
                    .fillMaxWidth()
                    .background(color = backgroundColor, shape = RoundedCornerShape(16.dp))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(items) { item ->
                    itemContent(item)
                }
            }
        } else {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
                    .background(color = backgroundColor, shape = RoundedCornerShape(16.dp))
                    .padding(16.dp),
                text = "Nenhum trabalho foi cadastrado",
                textAlign = TextAlign.Center,
                color = superLightCean,
            )
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