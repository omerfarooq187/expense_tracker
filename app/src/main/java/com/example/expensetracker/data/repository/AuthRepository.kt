package com.example.expensetracker.data.repository

import androidx.compose.runtime.mutableStateOf
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import javax.inject.Inject
import kotlin.math.sign

class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val googleSignInClient: GoogleSignInClient
) {
    val inProcess = mutableStateOf(false)
    val signIn = mutableStateOf(false)
    val currentUser = auth.currentUser
    fun getGoogleSignInClient() = googleSignInClient
    fun firebaseAuthWithGoogle(idToken: String) :Result<FirebaseAuth>{
        inProcess.value = true
        return try {
            val credential = GoogleAuthProvider.getCredential(idToken,null)
            auth.signInWithCredential(credential)
            inProcess.value = false
            Result.success(auth)
        } catch (e:Exception) {
            inProcess.value = false
            Result.failure(e)
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