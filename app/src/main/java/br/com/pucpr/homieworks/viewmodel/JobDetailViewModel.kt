package br.com.pucpr.homieworks.viewmodel

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.pucpr.homieworks.api.Retrofit
import br.com.pucpr.homieworks.model.Job
import br.com.pucpr.homieworks.util.SessionManager
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
class JobDetailViewModel : ViewModel() {
    var loading by mutableStateOf(false)
        private set
    var jobDetailError by mutableStateOf<String?>(null)
        private set
    var jobDetailSuccess by mutableStateOf(false)
        private set
    var job by mutableStateOf(Job())
        private set

    fun inflateJob(chargedJob: Job) {
        job = chargedJob
    }

    private fun updateJobWorker() { job = job.copy(worker = SessionManager.sessionUser!!)  }

    fun jobAccepted() {
        viewModelScope.launch {
            loading = true
            jobDetailError = null
            jobDetailSuccess = false

            updateJobWorker()

            try {
                Retrofit.api.addWorkerToJob(job)

                jobDetailSuccess = true
            } catch (e: Exception) {
                throw Exception("Erro ao aceitar trabalho: $e")
            } finally {
                loading = false
            }
        }
    }

    var whatsappUrl by mutableStateOf<String?>(null)
        private set

    fun sendMessage() {
        viewModelScope.launch {
            loading = true
            jobDetailError = null
            jobDetailSuccess = false
            whatsappUrl = null

            try {
                val getOwnerResponse = Retrofit.api.getUserById(job.owner!!.id!!)
                if (getOwnerResponse.isSuccessful) {
                    job = job.copy(owner = getOwnerResponse.body()!!)
                } else throw Exception("Proprietário não encontrado: ${getOwnerResponse.code()}")

                val phoneNumber = "55${job.owner!!.phoneNumber}"
                whatsappUrl = "https://wa.me/$phoneNumber"

            } catch (e: Exception) {
                jobDetailError = e.message ?: "Erro desconhecido ao enviar mensagem"
            } finally {
                loading = false
            }
        }
    }

}