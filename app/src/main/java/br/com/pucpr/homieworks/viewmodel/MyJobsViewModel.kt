package br.com.pucpr.homieworks.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MyJobsViewModel: ViewModel() {
    var loading by mutableStateOf(false)
        private set
    var myJobsSuccess by mutableStateOf(null)
        private set
    var myJobsOnError by mutableStateOf(false)
        private set
}