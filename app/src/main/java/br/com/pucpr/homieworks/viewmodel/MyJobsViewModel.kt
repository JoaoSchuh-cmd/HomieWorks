package br.com.pucpr.homieworks.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.pucpr.homieworks.api.Retrofit
import br.com.pucpr.homieworks.model.Job
import br.com.pucpr.homieworks.model.dto.toJob
import br.com.pucpr.homieworks.util.SessionManager
import kotlinx.coroutines.launch

class MyJobsViewModel: ViewModel() {
    var loading by mutableStateOf(false)
        private set
    var myJobsError by mutableStateOf(null)
        private set
    var myJobsSuccess by mutableStateOf(false)
        private set

    var myJobsList by mutableStateOf<List<Job>?>(null)
        private set

    var selectedJob by mutableStateOf<Job?>(null)
        private set

    fun selectJob(job: Job) {
        selectedJob = job
    }

    fun getJobById(id: Long?): Job? {
        return myJobsList?.find { it.id == id }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadJobs() {
        if (myJobsList != null) return

        viewModelScope.launch {
            loading = true
            myJobsError = null
            myJobsSuccess = false

            try {
                val response = Retrofit.api.getMyJobs(SessionManager.sessionUser!!.id!!)

                if (response.isSuccessful) {
                    val jobDtos = response.body() ?: emptyList()
                    myJobsList = jobDtos.map { it.toJob() }
                    myJobsSuccess = true
                }
            } catch (e: Exception) {
                throw Exception("Erro desconhecido ao carregar trabalhos: $e")
            } finally {
                loading = false
            }
        }
    }

}