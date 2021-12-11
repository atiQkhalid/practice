package com.example.practice.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.practice.utils.DialogUtils
import com.example.practice.views.activities.MainActivity
import com.kaopiz.kprogresshud.KProgressHUD

abstract class BaseFragment : Fragment() {

    private lateinit var mainActivity: MainActivity
    lateinit var progressDialog: KProgressHUD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        progressDialog = DialogUtils.showProgressDialog(mainActivity, cancelable = false)
    }
}