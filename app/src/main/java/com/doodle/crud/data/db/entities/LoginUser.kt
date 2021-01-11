package com.doodle.crud.data.db.entities


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LoginUser")
class LoginUser {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var email: String = ""
    var username: String = ""
    var Active: String = ""
}