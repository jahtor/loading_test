package com.example.loading_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.loading_test.ui.theme.Loading_testTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Loading_testTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Show()
                }
            }
        }
    }
}

@Composable
fun Show(){
    val loading = LoadingViewModel().loading.value
    LoadingViewModel().getContent()
    Box(modifier = Modifier.fillMaxSize()) {
        println("Show: $loading")
        CircularProgressBar(isDisplayed = loading)
        Text(text = LoadingViewModel().result.value)
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
