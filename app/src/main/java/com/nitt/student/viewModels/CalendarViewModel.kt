package com.nitt.student.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.nitt.api.RetrofitClient
import com.nitt.student.services.StudentApiService
import com.nitt.student.sharedPreferences.getTokenDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CalendarViewModel(): ViewModel() {
    val gson = Gson()
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading
    private val _calendarEvents = MutableStateFlow<Map<String, String>>(emptyMap())
    val calendarEvents: StateFlow<Map<String, String>> get() = _calendarEvents
    private val _errorMessage = MutableStateFlow<String>("")
    val errorMessage: StateFlow<String> get() = _errorMessage
    private val apiService = RetrofitClient.instance.create(StudentApiService::class.java)
    suspend fun getCalendarDetails(context: Context, scope: CoroutineScope) {
        val token = "Bearer ${getTokenDetails(context = context)}"
        scope.launch(Dispatchers.IO) {
            try {
                val response = apiService.fetchData(token)
                withContext(Dispatchers.Main) {
                    _calendarEvents.value = response
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _errorMessage.value = "Error: ${e.message}"
                    _isLoading.value = false
                }
            }
        }
    }
}