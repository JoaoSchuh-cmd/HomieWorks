package br.com.pucpr.homieworks.templates.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.pucpr.homieworks.ui.theme.darkCean

@Composable
fun GenericPage(
    header: (@Composable () -> Unit),
    content: (@Composable () -> Unit),
    footer: (@Composable () -> Unit)
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = darkCean)
            .padding(horizontal = 16.dp, vertical = 40.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            header()
            content()
            footer()
        }
    }
}

