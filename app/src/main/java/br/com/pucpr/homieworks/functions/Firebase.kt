package br.com.pucpr.homieworks.functions
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

object FirebaseAuthService {
    private val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    suspend fun cadastro(
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