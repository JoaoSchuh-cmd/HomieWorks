package br.com.pucpr.homieworks.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.com.pucpr.homieworks.model.Job

class AcceptedJobDetailsViewModel: ViewModel() {
    var loading by mutableStateOf(false)
        private set
    var acceptedJobDetailsError by mutableStateOf<String?>(null)
        private set
    var acceptedJobDetailsSuccess by mutableStateOf(false)
        private set
    var job by mutableStateOf(Job())
        private set

    fun inflateJob(jobCharged: Job) { job = jobCharged }
}