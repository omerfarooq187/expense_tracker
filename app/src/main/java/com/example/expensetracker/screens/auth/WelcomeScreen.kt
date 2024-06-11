package com.example.expensetracker.screens.auth

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.expensetracker.LoginScreenRoute
import com.example.expensetracker.MainScreenRoute
import com.example.expensetracker.R
import com.example.expensetracker.SignupScreenRoute
import com.example.expensetracker.utils.CommonProgressBar
import com.example.expensetracker.viewmodel.AuthViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException

@Composable
fun WelcomeScreen(viewModel: AuthViewModel, navController: NavHostController) {
    if (viewModel.currentUser != null) {
        navController.navigate(MainScreenRoute) {
            popUpTo(0)
        }
    }
    val authState = viewModel.authResult.collectAsState()
    val launcherActivity = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {  result->
        val data = result.data ?: return@rememberLauncherForActivityResult
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account = task.getResult(ApiException::class.java)
            account?.idToken?.let {  id->
                viewModel.signInWithGoogle(id)
            }
        } catch (e:Exception) {
            e.printStackTrace()
        }
    }
    if (authState.value?.isSuccess == true) {
        navController.navigate(MainScreenRoute) {
            popUpTo(0)
        }
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Text(
                buildAnnotatedString {
                    Text(
                        text = "Welcome to",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 30.sp
                    )
                    append("\n")
                    Text(
                        text = "ExpenseX",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.displayMedium
                    )
                    append("\n")
                    Text(
                        text = "A place where you can track all your expenses and income",
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        fontWeight = FontWeight(500)
                    )
                }
            )
            Text(
                text = "Let's Get Started...",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(bottom = 4.dp)
            )
            OutlinedButton(
                onClick = {
                          val signInIntent = viewModel.googleSignInClient().signInIntent
                    launcherActivity.launch(signInIntent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(horizontal = 8.dp)
                )
                Text(text = "Continue With Google")
            }
            OutlinedButton(
                onClick = {
                          navController.navigate(SignupScreenRoute)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.email),
                    contentDescription = "",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(horizontal = 8.dp)
                )
                Text(text = "Continue With Email")
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
                        },
                    )
                },
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(LoginScreenRoute)
                    }
            )
        }
        if (viewModel.inProcess.value) {
            CommonProgressBar()
        }
    }
}