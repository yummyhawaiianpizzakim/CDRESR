package com.example.myapplication22

import android.widget.CalendarView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication22.ui.theme.MyApplication22Theme


@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "LoginView") {
        composable("LoginView") {
            LoginView( navController)
        }
        composable("MainMenu") {
            MainMenu()
        }
        /*...*/
    }

}

@Composable
fun LoginView(navController: NavController) {
    val usernameSate = remember { mutableStateOf(value = "") }
    val passwordSate = remember { mutableStateOf(value = "") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = usernameSate.value,
            onValueChange = {
                usernameSate.value = it },
            label = { Text(text = "ID") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
            ),
            keyboardActions = KeyboardActions(
                onNext = {}
            ),
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = passwordSate.value,
            onValueChange = {
                passwordSate.value = it
            },
            label = { Text(text = "PW") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onNext = {}
            )
        )
        Button(
            enabled = usernameSate.value.isNotEmpty() && passwordSate.value.isNotEmpty(),
            onClick = {
                navController.navigate("MainMenu")
            }) {

            Text(text = "Login")

        }
    }
}

@Composable
fun MainMenu(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(

            onClick = { /*TODO*/ }) {
            Text(text = "Reserve Lecture Room")
        }
        Button(

            onClick = { /*TODO*/ }) {
            Text(text = "Notice")
        }
    }
}

@Composable
fun ReserveLectureRoom(){
    var reserveDate = remember { mutableStateOf(value = "") }
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
    ) {
        Column(
            verticalArrangement =  Arrangement.Center,
            horizontalAlignment =  Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth(),
        ) {
            AndroidView(
                factory = {CalendarView(it)},
                update = {
                    it.setOnDateChangeListener { calendarView, year, month, day ->
                        reserveDate.value = "$year - ${month +1} - $day"
                    }
                })
            Text(text = reserveDate.value)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplication22Theme {
        val navController = rememberNavController()
        LoginView(navController)
    }
}