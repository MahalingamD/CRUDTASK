package com.doodle.crud

import android.app.Application
import android.content.ContextWrapper
import com.doodle.crud.data.db.AppDatabase
import com.doodle.crud.prefrence.TKPrefs


class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        TKPrefs.Builder().setContext(this).setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(this.getString(R.string.app_name)).setUseDefaultSharedPreference(true)
            .build()

        AppDatabase.invoke(this)
    }
}