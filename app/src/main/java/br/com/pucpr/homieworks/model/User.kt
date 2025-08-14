package br.com.pucpr.homieworks.model

data class User(
    var id: Long? = 0,
    var email: String = "",
    var userPassword: String = "",
    var name: String = "",
    var phoneNumber: String = "",
    var addressStreet: String = "",
    var addressNum: String = "",
    var addressState: String = "",
    var addressCity: String = "",
    var addressPostalCode: String = "",
    var helpedits: Int = 0,
    var profileImg: String? = null,
    var worksDone: Int = 0,
    var active: Boolean = true,
)
