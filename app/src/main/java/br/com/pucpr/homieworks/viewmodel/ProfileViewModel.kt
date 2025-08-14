package br.com.pucpr.homieworks.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import br.com.pucpr.homieworks.model.User

class ProfileViewModel : ViewModel() {
    var loading by mutableStateOf(false)
    var profileError by mutableStateOf<String?>(null)
    var profileSuccess by mutableStateOf(false)
    var user by mutableStateOf(User())

    fun updateName(name: String) { user = user.copy(name = name) }
    fun updatePhone(phone: String) { user = user.copy(phoneNumber = phone) }
    fun updateEmail(email: String) { user = user.copy(email = email) }
    fun updatePassword(password: String) { user = user.copy(userPassword = password) }
}