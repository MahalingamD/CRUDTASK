package com.doodle.crud.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.doodle.crud.R
import com.doodle.crud.data.db.entities.UserList
import com.doodle.crud.databinding.ItemRowBinding
import com.doodle.crud.listener.ClickInterface
import com.doodle.crud.view.HomeActivity

class MyRecyclerViewAdapter(var mContext: HomeActivity, var mUserList: ArrayList<UserList>) :
    RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>() {

    val mClickInterface:ClickInterface=mContext

    inner class ViewHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(userList: UserList, position: Int) {
            binding.item = userList

            binding.cardItem.setOnClickListener {
                mClickInterface.onClickItem(userList,position)
            }

            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemRowBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_row,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mUserList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mUserList[position],position)



        // aClickInterface.onClickItem()
    }

    fun update(aUserList: ArrayList<UserList>) {
        mUserList.clear()
        mUserList.addAll(aUserList)
        notifyDataSetChanged()
    }

}