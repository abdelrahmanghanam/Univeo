package ghanam.com.univeo.extensions

import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

object GeneralExt {
    fun Activity.toast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun Fragment.toast(msg: String){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun showSnack(msg: String, it: View){
        Snackbar.make(it, msg, Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show()
    }
}