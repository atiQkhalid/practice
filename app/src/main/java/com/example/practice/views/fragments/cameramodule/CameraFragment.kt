package com.example.practice.views.fragments.cameramodule

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.PermissionChecker
import androidx.core.content.PermissionChecker.checkSelfPermission
import com.example.practice.R
import com.example.practice.base.BaseFragment
import com.example.practice.databinding.FragmentCameraBinding
import com.example.practice.extenssions.gone
import com.example.practice.extenssions.showToastMsg
import com.example.practice.extenssions.visible
import com.example.practice.adapter.cameramodule.CameraAdapter


class CameraFragment : BaseFragment(), View.OnClickListener {

    private lateinit var binding: FragmentCameraBinding
    private lateinit var cameraAdapter: CameraAdapter
    var uri: ArrayList<Uri> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvTakePicture.setOnClickListener(this)
        binding.tvSelectPictureFromGallery.setOnClickListener(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("data scheme", data?.scheme.toString())

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            binding.ivClickedPicture.visible()
            binding.ivClickedPicture.setImageBitmap(imageBitmap)
        } else if (resultCode == RESULT_OK && requestCode == SELECT_ITEM) {
            if (data?.data == null) {
                uri.clear()
                binding.ivClickedPicture.gone()
                binding.rvClickedPicture.visible()
                cameraAdapter = CameraAdapter(uri)
                binding.rvClickedPicture.adapter = cameraAdapter
                cameraAdapter.notifyDataSetChanged()
                val count: Int = data?.clipData!!.itemCount
                for (i in 0 until count) {
                    uri.add(data.clipData!!.getItemAt(i).uri)
                }
            } else{
                binding.ivClickedPicture.visible()
                binding.rvClickedPicture.gone()
                binding.ivClickedPicture.setImageURI(data.data as Uri)
            }
        }
    }

    // open Gallery
    private fun openGallery() {
        if (checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PermissionChecker.PERMISSION_GRANTED){
            val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            requestPermissions(permissions, PERMISSION_CODE)
        }
        else {
            chooseImageGallery()
        }
    }

    private fun chooseImageGallery(){
        val intent = Intent()
        intent.type = "image/* video/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(intent, "Select item from gallery"),
            SELECT_ITEM
        )
    }

    //  open camera
    private fun openCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
            showToastMsg(e.toString())
        }
    }

//    request permission
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    chooseImageGallery()
                }else{
                    showToastMsg("Permission Denied")
                }
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_takePicture -> openCamera()
            R.id.tv_SelectPictureFromGallery -> openGallery()
        }
    }

    companion object{
        private const val REQUEST_IMAGE_CAPTURE = 1001
        private const val SELECT_ITEM = 1002
        private const val PERMISSION_CODE = 1003
    }
}