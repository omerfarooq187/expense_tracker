package com.example.expensetracker.viewmodel

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensetracker.data.repository.AuthRepository
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthRepository, private val googleSignInClient: GoogleSignInClient) :ViewModel() {
    val currentUser = repository.currentUser
    val inProcess = repository.inProcess
    val signIn = repository.signIn
    private val _authResult = MutableStateFlow<Result<FirebaseAuth>?>(null)
    val authResult: StateFlow<Result<FirebaseAuth>?> = _authResult

    fun signInWithGoogle(idToken: String) {
        viewModelScope.launch {
           repository.firebaseAuthWithGoogle(idToken)
        }
    }

    fun getGoogleSignInClient(): Intent {
        return googleSignInClient.signInIntent
    }

    fun signupWithEmailAndPassword(email:String,password:String){
        viewModelScope.launch {
            repository.signupWithEmail(email,password)
        }
    }

    fun login(email:String,password:String){
        viewModelScope.launch {
            repository.login(email,password)
        }
    }
}