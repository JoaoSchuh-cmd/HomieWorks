package br.com.pucpr.homieworks.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class NewJobViewModel: ViewModel() {
    var loading by mutableStateOf(false)
        private set
    var newJobError by mutableStateOf<String?>(null)
        private set
    var newJobSuccess by mutableStateOf(false)
        private set
}