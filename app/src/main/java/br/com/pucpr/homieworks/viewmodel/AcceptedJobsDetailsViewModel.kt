package br.com.pucpr.homieworks.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AcceptedJobsDetailsViewModel: ViewModel() {
    var loading by mutableStateOf(false)
    var acceptedJobsDetailsError by mutableStateOf<String?>(null)
    var acceptedJobsDetailsSuccess by mutableStateOf(false)
}