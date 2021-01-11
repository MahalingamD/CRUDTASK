package com.doodle.crud.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.doodle.crud.R
import com.doodle.crud.data.db.AppDatabase
import com.doodle.crud.databinding.ActivityLoginBinding
import com.doodle.crud.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    lateinit var mLoginViewModel: LoginViewModel
    lateinit var mBinding: ActivityLoginBinding

    val AppDatabase: AppDatabase by lazy {
        AppDatabase(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        mLoginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        mBinding.login = mLoginViewModel
    }

    override fun onDestroy() {
        super.onDestroy()

        if (mLoginViewModel.mAlertDialog != null) {
            if (mLoginViewModel.mAlertDialog!!.isShowing)
                mLoginViewModel.mAlertDialog!!.dismiss()
        }

    }
}
