package com.example.expensetracker.di

import android.content.Context
import com.example.expensetracker.R
import com.example.expensetracker.data.firebase.FirebaseService
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun providesFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun providesFirebaseService(firebaseFirestore: FirebaseFirestore): FirebaseService {
        return FirebaseService(firebaseFirestore)
    }

    @Singleton
    @Provides
    fun providesGoogleSignInClient(@ApplicationContext context: Context) : GoogleSignInClient{
        val gso = GoogleSignInOptions.Builder()
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(context,gso)
    }
}