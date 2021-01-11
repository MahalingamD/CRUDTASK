package com.doodle.crud.viewmodel

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.doodle.crud.R
import com.doodle.crud.data.db.AppDatabase
import com.doodle.crud.databinding.InflateSignupBinding
import com.doodle.crud.prefrence.TKPrefs
import com.doodle.crud.utils.isValidEmail
import com.doodle.crud.utils.isValidPassword
import com.doodle.crud.utils.toast
import com.doodle.crud.view.HomeActivity


class LoginViewModel : ViewModel() {

    var email = ""
    var password = ""
    var mAlertDialog: AlertDialog? = null

    fun onLoginClick(aView: View) {
        when {
            email.isEmpty() -> aView.context.toast("Please enter your email address")
            password.isEmpty() -> aView.context.toast("Please enter your password")
            !isValidEmail(email) -> aView.context.toast("Invalid Email address")
            !isValidPassword(password) -> aView.context.toast("Invalid Password")
            else -> {

                val aLoginDao = AppDatabase.invoke(aView.context).getLoginUserDao()

                var aLoginuser = aLoginDao.getLoginUser(email)
                if (aLoginuser == null) {
                    aView.context.toast("User not available. Kindly signup")
                } else {
                    TKPrefs.putBoolean("Login", true)
                    TKPrefs.putString("LoginUser", aLoginuser.id.toString())
                    val aIntent = Intent(aView.context, HomeActivity::class.java)
                    aView.context.startActivity(aIntent)
                }
            }
        }
    }


    fun onSignupClick(aView: View) {

        val dialogBuilder = AlertDialog.Builder(aView.context)

        val aContext = aView.context as AppCompatActivity

        val mBinding: InflateSignupBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(aView.context),
                R.layout.inflate_signup,
                null,
                false
            )

        dialogBuilder.setView(mBinding.root)
        val aSignupViewModel = ViewModelProvider(aContext).get(SignupViewModel::class.java)
        mBinding.signup = aSignupViewModel
        mAlertDialog = dialogBuilder.create()
        mAlertDialog!!.show()
    }
}