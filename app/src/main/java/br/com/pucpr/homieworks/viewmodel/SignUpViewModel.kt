package br.com.pucpr.homieworks.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.pucpr.homieworks.api.Retrofit
import br.com.pucpr.homieworks.model.User
import br.com.pucpr.homieworks.util.SessionManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SignUpViewModel : ViewModel() {
    var loading by mutableStateOf(false)
        private set
    var signUpError by mutableStateOf<String?>(null)
        private set
    var signUpSuccess by mutableStateOf(false)
        private set
    var user by mutableStateOf(User())
        private set

    fun updateName(name: String) { user = user.copy(name = name) }
    fun updatePhone(phone: String) { user = user.copy(phoneNumber = phone) }
    fun updateEmail(email: String) { user = user.copy(email = email) }
    fun updatePassword(password: String) { user = user.copy(userPassword = password) }
    fun updateAddressStreet(street: String) { user = user.copy(addressStreet = street) }
    fun updateAddressNumber(number: String) { user = user.copy(addressNum = number) }
    fun updateAddressCity(city: String) { user = user.copy(addressCity = city) }
    fun updateAddressState(state: String) { user = user.copy(addressState = state) }
    fun updateAddressPostalCode(postalCode: String) { user = user.copy(addressPostalCode = postalCode) }
    private fun updateFirebaseUid(firebaseUid: String) { user = user.copy(firebaseUid = firebaseUid) }

    fun register() {
        viewModelScope.launch {
            loading = true
            signUpSuccess = false
            signUpError = null

            try {
                if (validarCampos(user)) {
                    val userFirebase = FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(user.email, user.userPassword)
                        .await()

                    val uid = userFirebase.user?.uid
                        ?: throw Exception("Erro ao obter UID do Firebase")

                    updateFirebaseUid(uid)
                    try {
                        Retrofit.api.insertUser(user)
                        SessionManager.sessionUser = user
                    } catch (apiException: Exception) {
                        FirebaseAuth.getInstance().currentUser?.delete()?.await()
                        throw Exception(apiException)
                    }

                    signUpSuccess = true
                } else {
                    throw Exception("Necessário preencher todos os campos de usuário")
                }
            } catch (e: Exception) {
                signUpError = e.message ?: "Erro desconhecido"

            } finally {
                loading = false
            }
        }
    }

    private fun validarCampos(user: User) : Boolean {
        return listOf(
            user.name,
            user.phoneNumber,
            user.email,
            user.userPassword,
            user.addressStreet,
            user.addressNum,
            user.addressCity,
            user.addressState,
            user.addressPostalCode
        ).none { it.isBlank() }
    }
}

