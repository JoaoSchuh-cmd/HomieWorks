package br.com.pucpr.homieworks.viewmodel

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
import br.com.pucpr.homieworks.model.requests.JobRequest
import br.com.pucpr.homieworks.util.SessionManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class NewJobViewModel: ViewModel() {
    var loading by mutableStateOf(false)
        private set
    var newJobError by mutableStateOf<String?>(null)
        private set
    var newJobSuccess by mutableStateOf(false)
        private set
    var job by mutableStateOf(Job())
        private set

    fun updateJobTitle(title: String) { job = job.copy(title = title) }
    fun updateJobDescription(description: String) { job = job.copy(description = description) }
    fun updateJobHelpedits(helpedits: String) { job = job.copy(helpedits = helpedits) }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateJobServiceData(data: String) {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
        try {
            val parsed = LocalDateTime.parse(data, formatter)
            job = job.copy(serviceDatetime = parsed)
        } catch (e: Exception) {
            Log.e("DATE_PARSE_ERROR", "Formato inválido: $data", e)
        }
    }



    @RequiresApi(Build.VERSION_CODES.O)
    fun register() {
        viewModelScope.launch {
            loading = true
            newJobSuccess = false
            newJobError = null

            try {
                if (validarCampos(job)) {
                    try {
                        val formatterOutput = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")

                        job = job.copy(owner = SessionManager.sessionUser!!)

                        val jobRequest = JobRequest(
                            title = job.title,
                            description = job.description,
                            ownerId = job.owner?.id,
                            workerId = job.worker?.id,
                            createDate = LocalDateTime.now().format(formatterOutput),
                            helpedits = job.helpedits.toInt(),
                            serviceDatetime = job.serviceDatetime.format(formatterOutput),
                            finished = job.finished
                        )

                        try {
                            val response = Retrofit.api.insertJob(jobRequest)

                            if (response.isSuccessful) {
                                job = response.body() ?: throw Exception(response.message())
                                newJobSuccess = true
                            } else {
                                throw Exception(response.message())
                            }
                        } catch (e: Exception) {
                            Log.e("TESTE", e.message ?: "Chegou aqui asdadasda")
                            throw Exception(e.message)
                        }




                    } catch (apiException: Exception) {
                        throw Exception(apiException.message)
                    }
                } else {
                    throw Exception("Necessário preencher todos os campos de usuário")
                }
            } catch (e: Exception) {
                newJobError = e.message ?: "Erro desconhecido"
            } finally {
                loading = false
            }
        }
    }

    private fun validarCampos(job: Job) : Boolean {
        return listOf(
            job.title,
            job.description,
        ).none { it.isBlank() }
    }
}