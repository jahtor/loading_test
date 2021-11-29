package com.example.loading_test

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadingViewModel: ViewModel() {

    val loading = mutableStateOf(false)
    val result = mutableStateOf("")

    fun getContent(){
        viewModelScope.launch {
            loading.value = true
            println("loading = true")
            delay(3000L)
            result.value = "Data Loaded!"
            loading.value = false
            println("loading = false")
        }
    }
}