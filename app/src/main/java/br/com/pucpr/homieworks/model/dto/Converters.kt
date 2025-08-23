package br.com.pucpr.homieworks.model.dto

import android.os.Build
import androidx.annotation.RequiresApi
import br.com.pucpr.homieworks.model.Job
import br.com.pucpr.homieworks.model.User
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun JobDTO.toJob(): Job {
    val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    return Job(
        id = this.id,
        title = this.title,
        description = this.description,
        serviceDatetime = LocalDateTime.parse(this.serviceDatetime, formatter),
        createDate = this.createDate.let { LocalDateTime.parse(it, formatter) },
        helpedits = this.helpedits.toString(),
        finished = this.finished ?: false,
        owner = this.owner?.toUser(),
        worker = this.worker?.toUser()
    )
}

fun SimpleUserDTO.toUser(): User {
    return User(
        id = this.id,
        name = this.name,
        email = this.email
    )
}