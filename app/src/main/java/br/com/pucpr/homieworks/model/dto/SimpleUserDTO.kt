package br.com.pucpr.homieworks.model.dto

import com.twilio.rest.proxy.v1.service.PhoneNumber

data class SimpleUserDTO(
    val id: Long,
    val name: String,
    val email: String,
)
