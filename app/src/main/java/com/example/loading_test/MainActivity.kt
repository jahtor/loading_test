package com.example.loading_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loading_test.ui.theme.Loading_testTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Loading_testTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    ShowVM()
                }
            }
        }
    }
}

class LoadingViewModel : ViewModel() {
    val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    var loading: LiveData<Boolean> = _loading

    fun getContent(){
        viewModelScope.launch {
            _loading.postValue(loading.value?.equals(true))
            delay(5000L)
            _loading.postValue(loading.value?.equals(false))
        }
    }
}

@Composable
fun ShowVM(viewModel: LoadingViewModel = LoadingViewModel()){
    val loading = viewModel.loading.observeAsState()
    viewModel.getContent()
//    CircularProgressBar(isDisplayed = loading.value!!)
    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = {
            viewModel.getContent()
        }) {
            Text(text = "Change")
        }
        println("Show: ${loading.value}")
        CircularProgressBar(isDisplayed = loading.value!!)
        Text(text = "Some data")
    }
}

@Composable
fun Show(){
//    val vm = LoadingViewModel()
//    vm.getContent()
//    var loading = remember { vm.loading }
    var loading = remember { mutableStateOf(true) }

    Column(modifier = Modifier.fillMaxSize()) {
//        println("Show: $loading")
        Button(onClick = {
            if (loading.value == true) {
                loading.value = false
            } else loading.value = true
        }) {
            Text(text = "Change")
        }
        CircularProgressBar(isDisplayed = loading.value)
    }
}

@Composable
fun CircularProgressBar(isDisplayed: Boolean){
    if (isDisplayed){
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
            horizontalArrangement = Arrangement.Center){
            CircularProgressIndicator(
                color = Color.White
            )
        }
    }
}
