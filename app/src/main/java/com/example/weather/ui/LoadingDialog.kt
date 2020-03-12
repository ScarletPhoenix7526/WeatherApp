package com.example.weather.ui

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import androidx.core.view.isVisible
import com.example.weather.R

class LoadingDialog(context: Context) : AlertDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = LayoutInflater.from(context)?.inflate(R.layout.loading_dialog, null)
        setCancelable(false)
        setView(view)
    }
}