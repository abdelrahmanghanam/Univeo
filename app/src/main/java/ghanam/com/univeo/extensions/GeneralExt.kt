package ghanam.com.univeo.extensions

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar

object GeneralExt {
    fun Activity.toast(msg: String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun Fragment.toast(msg: String){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun Fragment.showSnack(msg: String, label: String, it: View){
        val snackBar=Snackbar.make(it, label, Snackbar.LENGTH_SHORT)
        snackBar.setAction(msg){

        }
        snackBar.setTextColor(Color.RED)
        snackBar.show()
    }
}