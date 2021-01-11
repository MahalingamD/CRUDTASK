package com.doodle.crud.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.doodle.crud.R
import com.doodle.crud.adapter.MyRecyclerViewAdapter
import com.doodle.crud.data.db.AppDatabase
import com.doodle.crud.data.db.dao.UserListDao
import com.doodle.crud.data.db.entities.UserList
import com.doodle.crud.databinding.ActivityHomeBinding
import com.doodle.crud.listener.ClickInterface
import com.doodle.crud.prefrence.TKPrefs
import com.doodle.crud.utils.hideKeyboard
import com.doodle.crud.viewmodel.HomeViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.inflate_add_user.*

class HomeActivity : AppCompatActivity(), ClickInterface {

    val TAG = HomeActivity::class.java.simpleName

    lateinit var mBinding: ActivityHomeBinding

    lateinit var mHomeViewModel: HomeViewModel
    lateinit var mBottomSheetBehavior: BottomSheetBehavior<View>

    lateinit var mRecyclerViewAdapter: MyRecyclerViewAdapter

    val mAppDatabaseDao :UserListDao by lazy {
        AppDatabase.invoke(this).getUserListDao()
    }



    lateinit var mUserList: UserList
    var mPosition = 0
    var mUserId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        mHomeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        mBinding.homepage = mHomeViewModel
        mBinding.homescreen = this


        val aLoginStatus = TKPrefs.getBoolean("Login", false)
        mUserId = TKPrefs.getString("LoginUser", "false") ?: ""

        if (!aLoginStatus) {
            startActivity(Intent(this, LoginActivity::class.java))
            this.finish()
        }
        init()

        val aDB = AppDatabase.invoke(this).getLoginUserDao()
        val aList = aDB.getLoginUserList()

        Log.e("PPPPP", "" + aList.size)

    }

    private fun init() {

        val aDBObject = AppDatabase.invoke(this).getUserListDao()
        // mHomeViewModel.aBottomSheet = aBottomSheet
        val parentView = findViewById<View>(R.id.bottom_sheet)
        mBottomSheetBehavior = BottomSheetBehavior.from(parentView)
        mBottomSheetBehavior.peekHeight = 0
        mBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED


        mHomeViewModel.mBottomSheetBehavior = mBottomSheetBehavior

        mBottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_COLLAPSED ->
                        Log.d(TAG, "State Collapsed");

                    BottomSheetBehavior.STATE_DRAGGING ->
                        Log.d(TAG, "State Dragging");

                    BottomSheetBehavior.STATE_EXPANDED ->
                        Log.d(TAG, "State Expanded");

                    BottomSheetBehavior.STATE_HIDDEN ->
                        Log.d(TAG, "State Hidden");

                    BottomSheetBehavior.STATE_SETTLING ->
                        Log.d(TAG, "State Settling");

                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                        Log.d(TAG, "STATE HALF EXPANDED");
                    }
                }
            }

        })


        mRecyclerViewAdapter = MyRecyclerViewAdapter(this, arrayListOf())
        mBinding.myAdapter = mRecyclerViewAdapter


        val aList = aDBObject.getUserListBasedonUser(mUserId, "0")
        mRecyclerViewAdapter.update(aList as ArrayList<UserList>)


        clickListener(aDBObject)

    }

    private fun clickListener(aDBObject: UserListDao) {


        /* office_add_form.setOnClickListener {
             if (mBottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
                 mBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
             }

             Form_username_EDT.setText("")
             Form_mobile_EDT.setText("")

             form_save_BUT.visibility = View.VISIBLE
             form_edit_BUT.visibility = View.GONE
             form_delete_BUT.visibility = View.GONE
         }*/



       /* form_save_BUT.setOnClickListener {


        }

        form_edit_BUT.setOnClickListener {

        }

        form_delete_BUT.setOnClickListener {

        }*/
    }

    override fun onClickItem(aUserList: UserList, aPosition: Int) {
        Form_username_EDT.setText(aUserList.username)
        Form_mobile_EDT.setText(aUserList.mobilenumber)

        mPosition = aPosition

        mUserList = aUserList
        if (mBottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
            mBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        form_save_BUT.visibility = View.GONE
        form_edit_BUT.visibility = View.VISIBLE
        form_delete_BUT.visibility = View.VISIBLE
    }


    fun onLogoutClick(aView: View) {
        TKPrefs.putBoolean("Login", false)
        startActivity(Intent(this, LoginActivity::class.java))
        this.finish()
    }


    fun onFabClick(aView: View) {
        if (mBottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
            mBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        Form_username_EDT.setText("")
        Form_mobile_EDT.setText("")

        form_save_BUT.visibility = View.VISIBLE
        form_edit_BUT.visibility = View.GONE
        form_delete_BUT.visibility = View.GONE
    }


    fun onSaveClick(aView: View) {
        val aUserList = UserList()
        aUserList.username = Form_username_EDT.text.toString()
        aUserList.mobilenumber = Form_mobile_EDT.text.toString()
        aUserList.userid = "1"
        aUserList.Active = "1"
        aUserList.Deleted = "0"
        mAppDatabaseDao.insertLoginUser(aUserList)

        if (mBottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
            mBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        val aList = mAppDatabaseDao.getUserListBasedonUser(mUserId, "0")
        mRecyclerViewAdapter.update(aList as ArrayList<UserList>)
        hideKeyboard(this)
    }


    fun onEditClick(aView: View) {
        if (mBottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
            mBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

            val aUserList = UserList()
            aUserList.username = Form_username_EDT.text.toString()
            aUserList.mobilenumber = Form_mobile_EDT.text.toString()
            aUserList.userid = "1"
            aUserList.Active = "1"
            aUserList.Deleted = "0"

            val username = Form_username_EDT.text.toString()
            val mobilenumber = Form_mobile_EDT.text.toString()

            val aL = mAppDatabaseDao.updateUserList(username, mobilenumber, mUserList.id, "0")

            val aUpdateList = mAppDatabaseDao.getUserListBasedonUser(mUserId, "0")
            mRecyclerViewAdapter.update(aUpdateList as ArrayList<UserList>)

            hideKeyboard(this)
        }
    }


    fun onDeleteClick(aView: View) {

        if (mBottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
            mBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

            mAppDatabaseDao.deleteUserList(mUserList.id, "1")
            val aUpdateList = mAppDatabaseDao.getUserListBasedonUser(mUserId, "0")
            mRecyclerViewAdapter.update(aUpdateList as ArrayList<UserList>)
            hideKeyboard(this)
        }
    }

}
