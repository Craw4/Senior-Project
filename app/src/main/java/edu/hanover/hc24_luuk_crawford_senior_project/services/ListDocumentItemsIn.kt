package edu.hanover.hc24_luuk_crawford_senior_project.services

import android.content.ContentValues
import android.util.Log
import com.google.firebase.firestore.QuerySnapshot
import edu.hanover.hc24_luuk_crawford_senior_project.data.MenuData.Companion.addMenuItem

/**
 * Creates MenuItems and adds them to menu from QuerySnapshot.
 * @param document QuerySnapshot from firebase
 */
fun addItemsToMenuFrom(document: QuerySnapshot) {
    Log.d(ContentValues.TAG, "TEST $document is food document")
    for (foodOffering in document) {
        val food = foodOffering.data
        Log.d(ContentValues.TAG, "TEST $food is food id${food["id"]}")
        val nextItem = createItemFrom(food)
        addMenuItem(nextItem)
    }
}
