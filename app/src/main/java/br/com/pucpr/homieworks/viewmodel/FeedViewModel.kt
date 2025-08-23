package br.com.pucpr.homieworks.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.pucpr.homieworks.api.Retrofit
import br.com.pucpr.homieworks.model.Job
import br.com.pucpr.homieworks.model.dto.JobDTO
import br.com.pucpr.homieworks.model.dto.toJob
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class FeedViewModel @Inject constructor() : ViewModel() {
    var loading by mutableStateOf(false)
        private set
    var feedError by mutableStateOf<String?>(null)
        private set
    var feedSuccess by mutableStateOf(false)
        private set

    var jobsList by mutableStateOf<List<Job>?>(null)
        private set

    var selectedJob by mutableStateOf<Job?>(null)
        private set

    fun selectJob(job: Job) {
        Log.e("TESTEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE222222222", "selectJob(): $job")
        selectedJob = job
    }

    fun getJobById(id: Long?): Job? {
        return jobsList?.find { it.id == id }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun loadJobs() {
        if (jobsList != null) return

        viewModelScope.launch {
            loading = true
            feedError = null
            feedSuccess = false

            try {
                val response = Retrofit.api.getJobs()

                if (response.isSuccessful) {
                    val jobDtos = response.body() ?: emptyList()
                    jobsList = jobDtos.map { it.toJob() }
                    Log.e("TESTEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE11111111", "Lista de Jobs: ${jobsList!!.size}")
                    feedSuccess = true
                }
            } catch (e: Exception) {
                throw Exception("Erro desconhecido ao carregar trabalhos: $e")
            } finally {
                loading = false
            }
        }
    }
}