package br.com.pucpr.homieworks.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.pucpr.homieworks.api.Retrofit
import br.com.pucpr.homieworks.model.User
import br.com.pucpr.homieworks.util.FirebaseAuthService
import br.com.pucpr.homieworks.util.SessionManager
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    var loading by mutableStateOf(false)
    var profileError by mutableStateOf<String?>(null)
    var profileSuccess by mutableStateOf(false)
    var profileRemoveSuccess by mutableStateOf(false)
    var user by mutableStateOf(User())

    fun updateName(name: String) { user = user.copy(name = name) }
    fun updatePhone(phone: String) { user = user.copy(phoneNumber = phone) }
    fun updateEmail(email: String) { user = user.copy(email = email) }
    fun updatePassword(password: String) { user = user.copy(userPassword = password) }

    fun loadProfileInfo() {
        viewModelScope.launch {
            loading = true
            profileSuccess = false
            profileError = null

            user = SessionManager.sessionUser!!

            loading = false
        }
    }

    fun updateUserInfo() {
        viewModelScope.launch {
            loading = true
            profileSuccess = false
            profileError = null

            try {
                val response = Retrofit.api.putUserInfo(user.id!!, user)

                SessionManager.sessionUser = user

                if (response.isSuccessful) {
                    profileSuccess = true
                } else {
                    throw Exception("Erro ao atualizar dados do perfil: ${response.message()}")
                }
            } catch (e: Exception) {
                profileError = e.message ?: "Erro desconhecido ao atualizar perfil"
                throw Exception(e)
            } finally {
                loading = false
            }
        }
    }


    fun removeAccount() {
        viewModelScope.launch {
            loading = true
            profileRemoveSuccess = false
            profileError = null

            try {
                val response = Retrofit.api.deleteUser(user.id!!)
                if (response.isSuccessful) {
                    FirebaseAuthService.remove(onComplete = {
                        success, error ->
                        if (success) {
                            profileRemoveSuccess = true
                        } else {
                            throw Exception("Erro ao remover perfil do firebase: $error")
                        }
                    })
                } else {
                    throw Exception("Erro ao remover perfil: ${response.message()}")
                }
            } catch (e: Exception) {
                profileError = e.message ?: "Erro desconhecido ao remover perfil"
                throw Exception(e)
            } finally {
                loading = false
            }
        }
    }
}