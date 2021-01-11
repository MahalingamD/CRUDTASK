package com.doodle.crud.utils

import android.app.Activity
import android.content.Context
import android.util.Patterns
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.doodle.crud.utils.Constants.Companion.PASSWORD_PATTERN
import java.util.regex.Matcher
import java.util.regex.Pattern


fun Context.toast(aMsg: String) {
    Toast.makeText(this, aMsg, Toast.LENGTH_SHORT).show()
}


fun isValidPassword(password: String): Boolean {
    val pattern: Pattern
    val matcher: Matcher
    pattern = Pattern.compile(PASSWORD_PATTERN)
    matcher = pattern.matcher(password)
    return matcher.matches()
}

fun isValidEmail(email: String): Boolean {

    val pattern = Patterns.EMAIL_ADDRESS
    return pattern.matcher(email).matches()

}


//hide keyboard
fun hideKeyboard(ctx: Context) {
    val inputManager = ctx.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    val v = (ctx as Activity).currentFocus ?: return

    inputManager.hideSoftInputFromWindow(v.windowToken, 0)
}