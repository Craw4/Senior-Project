package edu.hanover.hc24_luuk_crawford_senior_project.services

import android.content.ContentValues
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import edu.hanover.hc24_luuk_crawford_senior_project.data.Customization
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuData.Companion.setItemTypeToCustomization

/**
 * Downloads items and toppings from database.
 * Stores locally to menu.
 */
fun downloadMenuFirebase() {
    downloadToppings()
    downloadItems()
}
private fun downloadToppings() {
    val db = FirebaseFirestore.getInstance()
    val docRef = db.collection("customization")
    docRef.get()
        .addOnSuccessListener { document ->
            if (document != null) {
                for (itemType in document) {
                    val customizationData = itemType.data
                    val sides = (customizationData["sides"] as? MutableList<String>) ?: mutableListOf()
                    val toppings = (customizationData["toppings"] as? MutableList<String>) ?: mutableListOf()
                    val glutenFree = (customizationData["glutenFree"] as? MutableList<String>) ?: mutableListOf()
                    val sauces = (customizationData["sauces"] as? MutableList<String>) ?: mutableListOf()
                    setItemTypeToCustomization(itemType.id, Customization(sides, toppings, sauces, glutenFree))
                }
            } else {
                Log.d(ContentValues.TAG, "No such document")
            }
        }
        .addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "get failed with ", exception)
        }
}

private fun downloadItems() {
    val db = FirebaseFirestore.getInstance()
    val docRef = db.collection("menuContent")
    docRef.get()
        .addOnSuccessListener { document ->
            if (document != null) {
                addItemsToMenuFrom(document)
            } else {
                Log.d(ContentValues.TAG, "No such document")
            }
        }
        .addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "get failed with ", exception)
        }
}
