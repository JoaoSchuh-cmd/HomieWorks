package br.com.pucpr.homieworks.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.pucpr.homieworks.functions.FirebaseAuthService
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
            loginSuccess = true
            loginError = null

            try {
                val token = FirebaseAuthService.login(email, password)
                loginSuccess = true
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