package br.com.pucpr.homieworks.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.pucpr.homieworks.model.User
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

    fun fazerCadastro(
        user: User?
    ) {
        viewModelScope.launch {
            signUpSuccess = true
            signUpError = null

            try {
                if (user != null) {
                    val authResult = FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(user.email, user.userPassword)
                        .await()

                    val userId = authResult.user?.uid
                        ?: throw Exception("Errro ao obter ID do usuário")

                    val userData = mapOf(
                        "name" to user.name,
                        "phone" to user.phoneNumber,
                        "street" to user.addressStreet,
                        "number" to user.addressNum,
                        "city" to user.addressCity,
                        "state" to user.addressState,
                        "postalCode" to user.addressPostalCode,
                        "email" to user.email
                    )

                    // TODO: "Chamar rota de cadastro de usuário no backend"
                    signUpSuccess = true
                } else {
                    throw Exception("Usuário é nulo")
                }
            } catch (e: Exception) {
                signUpError = e.message ?: "Erro desconhecido"
            } finally {
                loading = false
            }
        }
    }
}