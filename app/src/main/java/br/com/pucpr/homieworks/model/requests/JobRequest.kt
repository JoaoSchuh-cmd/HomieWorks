package br.com.pucpr.homieworks.model.requests

import br.com.pucpr.homieworks.util.SessionManager

data class JobRequest(
    val id: Long? = null,
    val title: String = "",
    val description: String = "",
    val ownerId: Long? = SessionManager.sessionUser!!.id,
    val workerId: Long? = null,
    val createDate: String = "",
    val helpedits: Int = 0,
    val serviceDatetime: String = "",
    val finished: Boolean? = false,
)