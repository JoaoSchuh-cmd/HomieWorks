package br.com.pucpr.homieworks.model.dto

data class JobDTO(
    val id: Long?,
    val title: String,
    val description: String,
    val serviceDatetime: String,
    val createDate: String?,
    val helpedits: Int?,
    val finished: Boolean?,
    val owner: SimpleUserDTO?,
    val worker: SimpleUserDTO?
)