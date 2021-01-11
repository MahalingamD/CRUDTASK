package com.doodle.crud.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserList")
class UserList {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    var userid: String = ""
    var username: String = ""
    var mobilenumber: String = ""
    var Active: String = ""
    var Deleted: String = ""
}