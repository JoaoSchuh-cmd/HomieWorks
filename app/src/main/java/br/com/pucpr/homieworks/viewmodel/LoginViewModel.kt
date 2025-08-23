package br.com.pucpr.homieworks.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.pucpr.homieworks.api.Retrofit
import br.com.pucpr.homieworks.util.FirebaseAuthService
import br.com.pucpr.homieworks.util.SessionManager
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    var loading by mutableStateOf(false)
        private set
    var loginError by mutableStateOf<String?>(null)
        private set
    var loginSuccess by mutableStateOf(false)
        private set

    fun fazerLogin(email: String, password: String) {
        viewModelScope.launch {
            loading = true
            loginSuccess = false
            loginError = null

            try {
                val userResponse = Retrofit.api.getUserByEmail(email)
                if (userResponse.isSuccessful) {
                    val token = FirebaseAuthService.login(email, password)
                    val response = Retrofit.api.getLoggedUser(token)

                    if (response.isSuccessful)
                        SessionManager.sessionUser = response.body()
                    else throw Exception("Não foi possível encontrar o usuário logado ${response.body()}")

                    loginSuccess = true
                } else {
                    throw Exception("Usuário não foi encontrado. Verifique o e-mail informado")
                }
            } catch (e: Exception) {
               loginError = e.message ?: "Erro desconhecido"
            } finally {
                loading = false
            }
        }
    }

    fun updateLoginError(msg: String) {
        loginError = msg
    }

}