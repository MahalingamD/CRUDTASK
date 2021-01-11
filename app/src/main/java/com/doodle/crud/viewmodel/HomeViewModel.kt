package com.doodle.crud.viewmodel

import android.util.TypedValue
import android.view.View
import androidx.lifecycle.ViewModel
import com.doodle.crud.R
import com.doodle.crud.view.HomeActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.inflate_add_user.*
import kotlinx.android.synthetic.main.inflate_add_user.view.*

class HomeViewModel : ViewModel() {

    //lateinit var aBottomSheet:BottomSheetDialog

    lateinit var mBottomSheetBehavior:BottomSheetBehavior<View>

    fun onfabclick(aView: View) {

        val aActivity = aView.context as HomeActivity

    }


}