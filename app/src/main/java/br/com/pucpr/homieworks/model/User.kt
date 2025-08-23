package br.com.pucpr.homieworks.model

data class User(
    val id: Long? = 0L,
    val email: String = "",
    val userPassword: String = "",
    val name: String = "",
    val phoneNumber: String = "",
    val addressStreet: String = "",
    val addressNum: String = "",
    val addressState: String = "",
    val addressCity: String = "",
    val addressPostalCode: String = "",
    val helpedits: Int = 100,
    val firebaseUid: String = "",
    val worksDone: Int = 0,
    val active: Boolean = true,
)
