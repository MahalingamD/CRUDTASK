package com.doodle.crud.viewmodel

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.doodle.crud.data.db.AppDatabase
import com.doodle.crud.data.db.entities.LoginUser
import com.doodle.crud.prefrence.TKPrefs
import com.doodle.crud.utils.isValidEmail
import com.doodle.crud.utils.isValidPassword
import com.doodle.crud.utils.toast
import com.doodle.crud.view.HomeActivity
import com.doodle.crud.view.LoginActivity

class SignupViewModel : ViewModel() {

    var email = ""
    var password = ""
    var confirmpassword = ""
    var username = ""


    fun onLoginClick(aView: View) {
        try {
            when {
                username.isEmpty() -> aView.context.toast("Please enter your username")
                email.isEmpty() -> aView.context.toast("Please enter your email address")
                password.isEmpty() -> aView.context.toast("Please enter your password")
                confirmpassword.isEmpty() -> aView.context.toast("Please enter confirm password")
                !isValidEmail(email) -> aView.context.toast("Invalid Email address")
                !isValidPassword(password) -> aView.context.toast("Invalid Password")
                else -> {
                    if (password == confirmpassword) {

                        insertLoginUserDetail(aView)
                        TKPrefs.putBoolean("Login", true)


                        //call next activity
                        val aIntent = Intent(aView.context, HomeActivity::class.java)
                        aView.context.startActivity(aIntent)

                        //Finish previous activity
                        (aView.context as LoginActivity).finish()

                    } else {
                        aView.context.toast("Confirm Password mismatch")
                    }

                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun insertLoginUserDetail(aView: View) {

        try {
            val aLoginUser = LoginUser()
            aLoginUser.email = email
            aLoginUser.username = email
            aLoginUser.Active = "1"
            val aLoginDao = AppDatabase.invoke(aView.context).getLoginUserDao()
            aLoginDao.insertLoginUser(aLoginUser)
            val aLoginuser = aLoginDao.getLoginUser(email)

            TKPrefs.putString("LoginUser", aLoginuser.id.toString())

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}