package com.doodle.crud.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.doodle.crud.data.db.entities.UserList

@Dao
interface UserListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLoginUser(aUser: UserList): Long

    @Query(" Select * from UserList")
    fun getAllUser(): UserList

    @Query(" Select * from UserList where userid = :aUserId and Deleted =:aDeleted")
    fun getUserListBasedonUser(aUserId: String,aDeleted:String): List<UserList>


    @Query(" UPDATE UserList SET mobilenumber=:aMobilenumber,username = :aUsername where id= :aUserId and Deleted =:aDeleted")
    fun updateUserList(aUsername: String, aMobilenumber: String, aUserId: Long,aDeleted:String) : Int


    @Query(" UPDATE UserList SET Deleted=:aDeleted where id= :aUserId")
    fun deleteUserList(aUserId: Long,aDeleted:String)
}