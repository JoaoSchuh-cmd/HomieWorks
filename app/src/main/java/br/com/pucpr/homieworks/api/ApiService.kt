package br.com.pucpr.homieworks.api

import br.com.pucpr.homieworks.model.Job
import br.com.pucpr.homieworks.model.requests.RecoveryRequest
import br.com.pucpr.homieworks.model.User
import br.com.pucpr.homieworks.model.dto.JobDTO
import br.com.pucpr.homieworks.model.requests.JobRequest
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @POST("/api/users")
    suspend fun insertUser(@Body user: User): User

    @GET("/api/users/{id}")
    suspend fun getUserById(@Path("id") id: Long): Response<User>

    @GET("/api/users/phone/{phoneNumber}")
    suspend fun getUserByPhoneNum(@Path("phoneNumber") phoneNumber: String) : Response<User>

    @GET("/api/users/me/{token}")
    suspend fun getLoggedUser(@Path("token") token: String): Response<User>

    @GET("/api/users/email/{email}")
    suspend fun getUserByEmail(@Path("email") email: String): Response<User>

    @DELETE("/api/users/{userId}")
    suspend fun deleteUser(@Path("userId") userId: Long): Response<ResponseBody>

    @POST("/api/jobs/create")
    suspend fun insertJob(@Body job: JobRequest): Response<Job>

    @PUT("/api/jobs/update")
    suspend fun updateJob(@Body job: JobRequest): Response<Job>

    @GET("/api/jobs/myjobs/{id}")
    suspend fun getMyJobs(@Path("id") id: Long): Response<MutableList<JobDTO>>

    @GET("/api/jobs/{id}")
    suspend fun getJobById(@Path("id") id: Long): Response<Job>

    @PATCH("/api/jobs/{id}")
    suspend fun addWorkerToJob(@Body updatedJob: Job): Job

    @GET("/api/jobs")
    suspend fun getJobs(): Response<MutableList<JobDTO>>

    @PUT("/api/users/{id}")
    suspend fun putUserInfo(@Path("id") id: Long, @Body user: User): Response<User>

    @POST("/api/recovery/send-sms")
    suspend fun sendRecoverySMS(@Body request: RecoveryRequest): Response<ResponseBody>

    @POST("/api/recovery/update-user-password/{firebaseUID}")
    suspend fun updateFirebaseUserPassword(@Path("firebaseUID") firebaseUid: String, @Body newPassword: String): Response<ResponseBody>

    @GET("/api/whatsapp/send-message/{phoneNumber}")
    suspend fun sendWppMessageToPhoneNumber(@Path("phoneNumber") phoneNumber: String) : Response<ResponseBody>
}