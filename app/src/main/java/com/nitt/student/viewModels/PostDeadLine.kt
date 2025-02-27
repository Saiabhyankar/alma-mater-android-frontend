package com.nitt.student.viewModels

import android.content.Context
import android.util.Log
import com.nitt.student.responses.DeadlineRequest
import com.nitt.student.responses.ZResponse
import com.nitt.student.sharedPreferences.getTokenDetails
import com.nitt.api.RetrofitClient
import com.nitt.student.services.StudentApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostDeadLine {
    private val apiService = RetrofitClient.instance.create(StudentApiService::class.java)
    fun postDeadLine(deadlineRequest: DeadlineRequest, context: Context) {
        val token = "Bearer ${getTokenDetails(context = context)}"
        Log.d("isTokenAdded",token.toString())
        if (token != null) {
            apiService.postDeadline(token, deadlineRequest).enqueue(object : Callback<ZResponse> { // Use the correct Response class
                override fun onResponse(call: Call<ZResponse>, response: Response<ZResponse>) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            Log.d("POST_DEAD", "Deadline: ${responseBody.deadline}, Message: ${responseBody.message}")
                        } else {
                            Log.d("POST_DEAD", "Response body is null")
                        }
                    } else {
                        val errorBody = response.errorBody()?.string() // Extract error message
                        Log.d("POST_DEAD", "Error: $errorBody")
                    }
                }
                override fun onFailure(call: Call<ZResponse>, t: Throwable) {
                    Log.d("POST_DEAD", "Failure: ${t.message}")
                }
            })

        }
    }
}