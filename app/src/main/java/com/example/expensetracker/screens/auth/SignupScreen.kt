package com.example.expensetracker.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.expensetracker.MainScreenRoute
import com.example.expensetracker.R
import com.example.expensetracker.utils.CommonProgressBar
import com.example.expensetracker.viewmodel.AuthViewModel

@Composable
fun SignupScreen(viewModel: AuthViewModel, navController: NavHostController) {
    if (viewModel.signIn.value || viewModel.currentUser!=null) {
        navController.navigate(MainScreenRoute) {
            popUpTo(0)
        }
    }
    var email by remember {
        mutableStateOf("")
    }
    var name by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 45.dp, start = 20.dp)
                    .size(30.dp)
            )
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp)
            ) {
                Text(
                    text = "Register",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 30.sp,
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                )
                Text(
                    text = "Create an account to access all features of ExpenseX",
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    fontWeight = FontWeight(500),
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(bottom = 30.dp)
                )
                Text(
                    text = "Email",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight(500),
                    fontSize = 16.sp
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    shape = RoundedCornerShape(15.dp),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Enter Your Email",
                            style = MaterialTheme.typography.bodyLarge,
                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight(500)
                        )
                    },
                    modifier = Modifier
                        .padding(bottom = 18.dp)
                        .fillMaxWidth()
                )

                Text(
                    text = "Your Name",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight(500),
                    fontSize = 16.sp
                )
                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    shape = RoundedCornerShape(15.dp),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Enter Your Name",
                            style = MaterialTheme.typography.bodyLarge,
                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight(500)
                        )
                    },
                    modifier = Modifier
                        .padding(bottom = 18.dp)
                        .fillMaxWidth()
                )

                Text(
                    text = "Password",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight(500),
                    fontSize = 16.sp
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    shape = RoundedCornerShape(15.dp),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Enter Your Password",
                            style = MaterialTheme.typography.bodyLarge,
                            fontFamily = FontFamily(Font(R.font.poppins)),
                            fontWeight = FontWeight(500)
                        )
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(40.dp))
                Button(
                    onClick = {
                              viewModel.signupWithEmailAndPassword(email, password)
                    },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                ) {
                    Text(
                        text = "Register",
                        fontSize = 16.sp
                    )
                }
                Text(
                    buildAnnotatedString {

                        append(
                            buildAnnotatedString {
                                withStyle(style = SpanStyle(
                                    fontFamily = FontFamily(Font(R.font.poppins)),
                                )
                                ) {
                                    append("Already Have an Account? ")
                                }
                            }
                        )
                        append(
                            buildAnnotatedString {
                                withStyle(style = SpanStyle(
                                    color = Color.Blue,
                                    fontFamily = FontFamily(Font(R.font.poppins)),
                                )
                                ) {
                                    append("Login")
                                }
                            }
                        )
                    },
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
        if (viewModel.inProcess.value) {
            CommonProgressBar()
        }
    }
}