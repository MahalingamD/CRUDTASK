package com.doodle.crud.listener

import com.doodle.crud.data.db.entities.UserList

interface ClickInterface {

    fun onClickItem(aUserList: UserList,aPosition:Int)
}