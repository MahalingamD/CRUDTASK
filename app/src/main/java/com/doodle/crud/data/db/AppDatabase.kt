package com.doodle.crud.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.doodle.crud.data.db.dao.LoginUserDao
import com.doodle.crud.data.db.dao.UserListDao
import com.doodle.crud.data.db.entities.LoginUser
import com.doodle.crud.data.db.entities.UserList


@Database(entities = [LoginUser::class, UserList::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getLoginUserDao(): LoginUserDao
    abstract fun getUserListDao(): UserListDao

    companion object {

        @Volatile //if use volatile immediate to display all the places
        private var instances: AppDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instances
            ?: synchronized(LOCK) {
                instances
                    ?: buildDatabase(
                        context
                    ).also {
                        instances = it
                    }
            }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "MyDatabase.db"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries().build()
    }
}