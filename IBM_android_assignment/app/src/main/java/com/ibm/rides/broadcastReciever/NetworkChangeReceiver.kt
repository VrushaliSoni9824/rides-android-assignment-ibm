package com.ibm.rides.broadcastReciever

// NetworkChangeReceiver.kt
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.ibm.rides.R
import com.ibm.rides.utils.NetworkUtils

class NetworkChangeReceiver(private val constraintLayout: ConstraintLayout) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null && !NetworkUtils.isNetworkAvailable(context)) {
            showSnackbar()
        }
    }

    private fun showSnackbar() {
        Snackbar.make(constraintLayout, R.string.no_internet, Snackbar.LENGTH_LONG)
            .setBackgroundTint(0xFFFF0000.toInt())
            .show()
    }
}
