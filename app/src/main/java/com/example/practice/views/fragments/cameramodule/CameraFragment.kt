package com.example.practice.views.fragments.cameramodule

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.practice.R
import com.example.practice.base.BaseFragment
import com.example.practice.databinding.FragmentCameraBinding
import com.example.practice.extenssions.gone
import com.example.practice.extenssions.showToastMsg
import com.example.practice.extenssions.visible
import com.example.practice.views.fragments.cameramodule.adapter.CameraAdapter


class CameraFragment : BaseFragment(), View.OnClickListener {

    private lateinit var binding: FragmentCameraBinding
    private lateinit var cameraAdapter: CameraAdapter
    private val REQUEST_IMAGE_CAPTURE = 1
    private val SELECT_PICTURE = 2
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
//        binding.ivClickedPicture.setOnClickListener(this)
        binding.tvSelectPictureFromGallery.setOnClickListener(this)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            binding.ivClickedPicture.visible()
            binding.ivClickedPicture.setImageBitmap(imageBitmap)
        } else if (resultCode == RESULT_OK && requestCode == SELECT_PICTURE) {

            if (data?.clipData?.itemCount != 0) {
                binding.ivClickedPicture.gone()
                val count: Int = data?.clipData!!.itemCount
                for (i in 0 until count) {
                    uri.add(data.clipData!!.getItemAt(i).uri)
                }
                cameraAdapter = CameraAdapter(uri)
                cameraAdapter.notifyDataSetChanged()
                binding.rvClickedPicture.adapter = cameraAdapter
            } else{
                binding.ivClickedPicture.visible()
                binding.ivClickedPicture.setImageURI(data.data as Uri)
            }

        }
    }

    // open Gallery
    private fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE)
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

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_takePicture -> openCamera()
//            R.id.iv_clickedPicture -> showToastMsg(binding.ivClickedPicture.drawable.toString())
            R.id.tv_SelectPictureFromGallery -> openGallery()
        }
    }
}