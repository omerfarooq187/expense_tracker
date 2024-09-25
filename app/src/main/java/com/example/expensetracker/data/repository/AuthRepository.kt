package com.example.expensetracker.data.repository

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth
) {
    val inProcess = mutableStateOf(false)
    val signIn = mutableStateOf(false)
    val currentUser = auth.currentUser

    fun firebaseAuthWithGoogle(idToken: String) {
        inProcess.value = true
        val credential = GoogleAuthProvider.getCredential(idToken,null)
        auth.signInWithCredential(credential).addOnCompleteListener { task->
            if (task.isSuccessful) {
                inProcess.value = false
                signIn.value = true
                Log.d("User", task.result.user.toString())
            } else {
                inProcess.value = false
                signIn.value = false
                Log.d("Failure", task.exception.toString())
            }
        }
    }

    fun signupWithEmail(email:String, password:String) {
        inProcess.value = true
        auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
            inProcess.value = false
            signIn.value = true
        }.addOnFailureListener {
            inProcess.value = false
            signIn.value = false
            it.printStackTrace()
        }
    }

    fun login(email:String, password:String) {
        inProcess.value = true
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            inProcess.value = false
            signIn.value = true
        }.addOnFailureListener {
            inProcess.value = false
            signIn.value = false
        }
    }
}