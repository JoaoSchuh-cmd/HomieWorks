package br.com.pucpr.homieworks.util
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import io.jsonwebtoken.security.Password
import kotlinx.coroutines.tasks.await

object FirebaseAuthService {
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    suspend fun register(
        email: String,
        password: String
    ): String {
        try {
            auth.createUserWithEmailAndPassword(email, password).await()

            val tokenResult = auth.currentUser?.getIdToken(true)?.await()
                ?: throw Exception("Não foi possível obter o token")

            return tokenResult.token ?: throw Exception("Token inválido")
        } catch (e: Exception) {
            throw Exception(tratarErroFirebase(e))
        }
    }

    suspend fun login(
        email: String,
        password: String
    ): String {
        try {
            auth.signInWithEmailAndPassword(email, password).await()

            val tokenResult = auth.currentUser?.getIdToken(true)?.await()
                ?: throw Exception("Não foi possível obter o token")

            return tokenResult.token ?: throw Exception("Token inválido")
        } catch (e: Exception) {
            throw Exception(tratarErroFirebase(e))
        }
    }

    fun logout() { FirebaseAuth.getInstance().signOut() }

    fun remove(onComplete: (Boolean, String?) -> Unit) {
        val user = FirebaseAuth.getInstance().currentUser
        user?.delete()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onComplete(true, null)
                } else {
                    onComplete(false, task.exception?.message)
                }
            }
    }

    private fun tratarErroFirebase(e: Exception): String {
        return when {
            e.message?.contains("email address is already in use") == true ->
                "Este e-mail já está cadastrado."
            e.message?.contains("password is invalid") == true ->
                "Senha incorreta."
            e.message?.contains("no user record") == true ->
                "Usuário não encontrado."
            else -> e.message ?: "Erro desconhecido"
        }
    }
}