package br.com.pucpr.homieworks.model

import android.os.Build
import androidx.annotation.RequiresApi
import br.com.pucpr.homieworks.util.SessionManager
import java.time.LocalDateTime

data class Job @RequiresApi(Build.VERSION_CODES.O) constructor(
    val id: Long? = 0L,
    val title: String = "",
    val description: String = "",
    val owner: User? = SessionManager.sessionUser!!,
    val worker: User? = null,
    val createDate: LocalDateTime = LocalDateTime.now(),
    val helpedits: String = "10",
    val serviceDatetime: LocalDateTime = LocalDateTime.now().plusDays(30),
    val finished: Boolean? = false,
)
