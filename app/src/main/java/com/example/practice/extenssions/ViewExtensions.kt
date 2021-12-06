package com.example.practice.extenssions

import android.view.View
import android.widget.Toast
import com.example.practice.App

/**
 * Extension function to show toast message
 */
fun Any.showToastMsg(message: String) {
    Toast.makeText(com.example.practice.App.getAppContext(), message, Toast.LENGTH_SHORT).show()
}

/**
 * An Extension to make view Gone
 * @return void
 */
fun View.gone() {
    visibility = View.GONE
}

/**
 * An Extension to make view Visible
 * @return void
 */
fun View.visible() {
    visibility = View.VISIBLE
}