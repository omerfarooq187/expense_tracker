package com.example.expensetracker.data.firebase

import android.util.Log
import com.example.expensetracker.data.model.Transaction
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FirebaseService @Inject constructor(private val db:FirebaseFirestore) {

    fun saveTransaction(transaction: Transaction) {
        val transactionMap = hashMapOf(
            "id" to transaction.id,
            "amount" to transaction.amount,
            "category" to transaction.category,
            "description" to transaction.description,
            "date" to transaction.date,
            "type" to transaction.type
        )

        db.collection("transactions")
            .add(transactionMap)
            .addOnSuccessListener { task->
                Log.d("FirebaseService", "Transaction saved with ID: ${task.id}")
            }
            .addOnFailureListener { task->
                Log.d("FirebaseService", "Transaction save failed: ${task.message}")
            }
    }
}