package br.com.pucpr.homieworks.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AcceptedJobsViewModel: ViewModel() {
    var loading by mutableStateOf(false)
    var acceptedJobsError by mutableStateOf<String?>(null)
    var acceptedJobsSuccess by mutableStateOf(false)
}