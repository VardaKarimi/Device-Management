package screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import component.CustomButton
import database.httpClient.Login
import kotlinx.coroutines.runBlocking
import navigations.NavController
import java.util.prefs.Preferences


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LogInScreen(
    navController: NavController,
){

    val userEmail = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    var showErrorDialog by mutableStateOf(false)
    var errorMessage by mutableStateOf("")
    var passwordVisibility by remember { mutableStateOf(false) }

    Surface(modifier = androidx.compose.ui.Modifier.fillMaxWidth().fillMaxHeight()) {
        Column(modifier = androidx.compose.ui.Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            TextField(
                value = userEmail.value,
                onValueChange = { userEmail.value = it },
                modifier = androidx.compose.ui.Modifier.width(300.dp).padding(8.dp).border(width = 1.dp, color = Color.Black),
                label = { Text("Email")},
                placeholder = { Text("Enter Your Email") }
            )

            TextField(
                value = password.value,
                onValueChange = { password.value = it },
                modifier = androidx.compose.ui.Modifier.width(300.dp).padding(8.dp).border(width = 1.dp, color = Color.Black),
                label = { Text("Password")},
                placeholder = { Text(text="Enter Your Password", modifier = Modifier.padding(4.dp)) },
                visualTransformation =  if(passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon ={
                    IconButton(onClick = {passwordVisibility = !passwordVisibility}){
                        Icon(
                            imageVector = if(passwordVisibility) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                            contentDescription = if(passwordVisibility) "Hide Password" else "Show Password"
                        )
                    }
                }

            )
            if (showErrorDialog){
                Text(errorMessage)
            }
            CustomButton(onClick ={
                val email = userEmail.value.trim()
                val pass = password.value.trim()
                if(email.isNotEmpty() && pass.isNotEmpty()){
                    val result = runBlocking { Login(email,pass) }
                    println(result)
                    if(result == email){
                        storeUserEmail(email)
                        navController.navigate(Screen.HomeScreen.name)
                    }
                    else{
                        when (result) {
                            "auth/user-not-found" -> {
                                showErrorDialog = true
                                errorMessage = "User not found"
                            }
                            "auth/wrong-password" -> {
                                showErrorDialog = true
                                errorMessage = "Wrong password"
                            }
                            else -> {
                                showErrorDialog = true
                                errorMessage = "Authentication failed"
                            }
                        }
                        println(result)
                    }
                }

            }, modifier = androidx.compose.ui.Modifier.width(200.dp).height(50.dp).padding(8.dp),
                text = "Log In")


        }

    }

}

private fun storeUserEmail(email: String) {
    val preferences = Preferences.userRoot().node("com.example.myapplication") // Replace with your application package name
    preferences.put("userEmail", email)
    println(email)
    preferences.flush()
}