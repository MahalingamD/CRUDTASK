package com.doodle.crud.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.doodle.crud.data.db.entities.LoginUser

@Dao
interface LoginUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLoginUser(aUser: LoginUser): Long

    @Query(" Select * from LoginUser where email = :aEmail")
    fun getLoginUser(aEmail: String): LoginUser

    @Query(" Select * from LoginUser ")
    fun getLoginUserList(): List<LoginUser>
}