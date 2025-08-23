package br.com.pucpr.homieworks.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.pucpr.homieworks.api.Retrofit
import br.com.pucpr.homieworks.model.requests.RecoveryRequest
import br.com.pucpr.homieworks.model.User
import kotlinx.coroutines.launch

class RecoveryViewModel: ViewModel() {
    var loading by mutableStateOf(false)
        private set
    var recoveryError by mutableStateOf<String?>(null)
        private set
    var recoverySuccess by mutableStateOf(false)
        private set

    var recoveryPhone by mutableStateOf("")
        private set
    var confirmRecoveryCode by mutableStateOf("")
        private set
    var user by mutableStateOf(User())
        private set
    var newPassword by mutableStateOf("")
        private set
    var confirmPassword by mutableStateOf("")
        private set

    fun updateRecoveryPhoneNum(phoneNumber: String) = run { recoveryPhone = phoneNumber }
    fun updateConfirmeRecoveryCode(recoveryCode: String) = run { confirmRecoveryCode = recoveryCode }
    fun updateUserPassword(password: String) { user = user.copy(userPassword = password) }
    fun updateNewPassword(newPassInput: String) = run { newPassword = newPassInput }
    fun updateConfirmPassword(confirmPassInput: String) = run { confirmPassword = confirmPassInput }

    fun sendRecoverySMS(recoveryPhone: String) {
        viewModelScope.launch {
            loading = true
            recoveryError = null
            recoverySuccess = false

            try {
                val userResponse = Retrofit.api.getUserByPhoneNum(recoveryPhone)
                if (userResponse.isSuccessful) {
                    userResponse.body()?.let {
                        user = it
                    } ?: throw Exception("Usuário não encontrado para o telefone $recoveryPhone")
                } else {
                    throw Exception("Erro ao encontrar usuário: ${userResponse.code()} - ${userResponse.message()}")
                }

                val recoveryResponse = Retrofit.api.sendRecoverySMS(RecoveryRequest("+55${recoveryPhone}"))
                if (!recoveryResponse.isSuccessful) {
                    throw Exception("Erro ao enviar SMS: ${recoveryResponse.code()} - ${recoveryResponse.message()}")
                }
            } catch (e: Exception) {
                recoveryError = e.message ?: "$e"
            } finally {
                loading = false
            }
        }
    }

    fun recoverUserPassword(confirmationPassword: String) {
        viewModelScope.launch {
            loading = true
            recoveryError = null
            recoverySuccess = false

            try {
                validatePasswordConfirmation(confirmationPassword)

                updateUserPassword(confirmationPassword)

                val updateUserResponse = Retrofit.api.putUserInfo(user.id!!, user)
                if (!updateUserResponse.isSuccessful) {
                    throw Exception("Erro ao atualizar senha do usuário: ${updateUserResponse.code()} - ${updateUserResponse.message()}")
                }
                val updateFirebaseUser = Retrofit.api.updateFirebaseUserPassword(firebaseUid = user.firebaseUid, newPassword = confirmationPassword)
                if (!updateFirebaseUser.isSuccessful) {
                    throw Exception("Erro ao atualizar senha do firebase usuário: ${updateFirebaseUser.code()} - ${updateFirebaseUser.message()}")
                }

                recoverySuccess = true

            } catch (e: Exception) {
                recoveryError = e.message ?: "Erro desconhecido ao atualizar senha do usuário"
            } finally {
                loading = false
            }

        }
    }

    private fun validatePasswordConfirmation(confirmationPassword: String) {
        viewModelScope.launch {
            loading = true
            recoveryError = null
            recoverySuccess = false

            try {
                if (confirmationPassword != newPassword) {
                    throw Exception("Senhas não conferem")
                }
            } catch (e: Exception) {
                recoveryError = e.message ?: "Erro desconhecido ao confirmar senha"
            } finally {
                loading = false
            }
        }
    }
}