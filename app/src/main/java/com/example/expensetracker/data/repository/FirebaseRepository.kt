package com.example.expensetracker.data.repository

import com.example.expensetracker.data.firebase.FirebaseService
import com.example.expensetracker.data.model.Transaction
import javax.inject.Inject

class FirebaseRepository @Inject constructor(
    private val firebaseService: FirebaseService
) {

    fun saveTransaction(transaction: Transaction) {
        firebaseService.saveTransaction(transaction)
    }

}