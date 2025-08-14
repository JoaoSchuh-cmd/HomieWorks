package br.com.pucpr.homieworks.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class FeedViewModel: ViewModel() {
    var loading by mutableStateOf(false)
        private set
    var feedError by mutableStateOf<String?>(null)
        private set
    var feedSuccess by mutableStateOf(false)
        private set
}